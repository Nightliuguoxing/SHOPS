package top.lgx.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import top.lgx.entity.Spit;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-13 17:05
 * @Description:
 */
@Repository
public interface SpitDao extends MongoRepository<Spit, String>  {


    public Page<Spit> findByParentId(String parentid, Pageable pageable);
}
