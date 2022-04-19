package top.lgx.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.lgx.entity.Article;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022/4/19 9:12
 * @Description:
 */
@Repository
public interface ArticleDao extends BaseDao<Article, String>{

    @Modifying
    @Query("UPDATE Article set state = '1' WHERE id = :id")
    public void examine(String id);

    @Modifying
    @Query(value = "UPDATE Article SET  thumbup = thumbup + 1 WHERE id = :id")
    public int updateThumbup(String id);


}
