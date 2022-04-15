package top.lgx.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-15 09:15
 * @Description:
 */
public class JwtUtil {

    private  String key = "LUCIFER-LGX-BF-SHOP";

    private Long ttl = 604800L;

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-15 09:20
     * @Params: id
     * @Params: subject
     * @Params: roles
     * @Return: java.lang.String
     * @Description: 生成JWT
     */
    public String createJwt(String id, String subject, String roles) {
        Long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //生成签名密钥
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS512, key)
                .claim("roles", roles);
        if (ttl > 0) {
            builder.setExpiration(new Date(nowMillis + ttl));
        }
        return builder.compact();
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-15 09:21
     * @Params: jwt
     * @Return: io.jsonwebtoken.Claims
     * @Description: 解析JWT
     */
    public Claims parseJwt(String jwt) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt)
                .getBody();
    }

    // 获取token中的id
    public String getId(String token) {
        return parseJwt(token).getId();
    }

}
