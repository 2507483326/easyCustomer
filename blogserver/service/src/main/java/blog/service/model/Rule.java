package blog.service.model;

import java.util.Date;

/**
 * Created by Epat on 2017/3/12.
 */
public class Rule {

    private Integer id;

    private String path;

    private String rule;

    private Integer sort;

    private Integer createId;

    private Date createTime;

    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", rule='" + rule + '\'' +
                ", sort=" + sort +
                ", createId=" + createId +
                ", createTime=" + createTime +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
