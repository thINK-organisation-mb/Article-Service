package com.thINK_organisation_mb.article_service.article_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "article")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article{
    @Id
    @GeneratedValue
    private UUID aid;

    @Column(name = "articlename", nullable = false, length = 255)
    private String articleName;

    @Column(name = "uid", nullable = false)
    private UUID uid;

    @Column(name = "author_email", nullable = false)
    private String authorEmail;

    @Column(name = "preview")
    private String preview;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "rt_estimate", nullable = false)
    private int rt_estimate;

    @Column(name = "date", nullable = false)
    private Date date;

    public UUID getAid() {
        return aid;
    }

    public void setAid(UUID aid) {
        this.aid = aid;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public int getRt_estimate() {
        return rt_estimate;
    }

    public void setRt_estimate(int rt_estimate) {
        this.rt_estimate = rt_estimate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }
}
