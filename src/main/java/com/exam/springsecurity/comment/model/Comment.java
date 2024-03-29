package com.exam.springsecurity.comment.model;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @SequenceGenerator(name = "comment_sequence", sequenceName = "comment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_sequence")

    private Integer id;
    private Integer author;
    private String content;
    private Date date;
    private Integer particle;
    private Integer pcomment;
    private String username;
    private boolean deleted;
    
    public Comment() {
    }

    public Comment(Integer author,
                   String content,
                   Date date,
                   Integer particle,
                   Integer pcomment, String username, boolean deleted) {

        this.author = author;
        this.content = content;
        this.date = date;
        this.particle = particle;
        this.pcomment = pcomment;
        this.username = username;
        this.deleted = deleted;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getParticle() {
        return particle;
    }

    public void setParticle(Integer particle) {
        this.particle = particle;
    }

    public Integer getPcomment() {
        return pcomment;
    }

    public void setPcomment(Integer pcomment) {
        this.pcomment = pcomment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}