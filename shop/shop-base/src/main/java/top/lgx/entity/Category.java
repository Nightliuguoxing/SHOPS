package top.lgx.entity;

import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-18 21:17
 * @Description:
 */
@Entity
@Table(name = "tb_category")
@ApiModel(value = "Category", description = "分类实体类")
public class Category {

    @Id
    private String id;

    private String name;

    private Integer goodsNum;

    private String isShow;

    private String isMenu;

    private Integer sort;

    private String parentId;

    private String templateId;

    @Transient
    private List<Category> children;
}
