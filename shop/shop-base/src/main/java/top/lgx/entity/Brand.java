package top.lgx.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-18 20:25
 * @Description:
 */
@Entity
@Table(name = "tb_brand")
@ApiModel(value = "Brand", description = "品牌实体类")
public class Brand {

    @Id
    @ApiModelProperty(value = "品牌ID")
    private String id;

    @ApiModelProperty(value = "品牌名称")
    private String name;

    @ApiModelProperty(value = "品牌LOGO")
    private String image;

    @ApiModelProperty(value = "品牌首字母")
    private String letter;

    @ApiModelProperty(value = "品牌排序")
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
