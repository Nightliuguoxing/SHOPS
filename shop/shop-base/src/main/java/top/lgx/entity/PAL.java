package top.lgx.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-13 09:50
 * @Description:
 */
@Entity
@Table(name = "tb_pl")
public class PAL implements Serializable {

    private static final long serialVersionUID = -5000294897770517525L;

    @Id
    private String problemId;

    @Id
    private String labelId;

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }
}
