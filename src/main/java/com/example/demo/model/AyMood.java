package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 微信说说实体
 */
@Entity
@Table(name = "ay_mood")
public class AyMood implements Serializable {
    @Id
    private String id;
    private String content;
    private String userId;
    private Integer praiseNum;
    private Date publishTime;

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getId() {

        return id;
    }

    public String getContent() {
        return content;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public Date getPublishTime() {
        return publishTime;
    }
}
