package top.lgx.test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-15 09:05
 * @Description:
 */
public class Jwt {

    public static void main(String[] args) {
        Jwt jwt = new Jwt();
        String token = jwt.createToken();
        System.out.println(token);
        System.out.println(jwt.parseToken(token));
    }

    private String createToken(){
        JwtBuilder builder = Jwts.builder()
                .setId("1")
                .setSubject("lgx")
                .setIssuedAt(new java.util.Date())
                .signWith(SignatureAlgorithm.HS512, "1234567890");
        return builder.compact();
    }

    private String parseToken(String token){
        Claims claims = Jwts.parser().setSigningKey("1234567890").parseClaimsJws(token).getBody();
        return "ID:" + claims.getId() + "; SUBJECT:" + claims.getSubject() + "; ISSUED_AT:" + claims.getIssuedAt();
    }
}
