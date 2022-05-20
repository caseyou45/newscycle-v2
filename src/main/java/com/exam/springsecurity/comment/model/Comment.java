package com.exam.springsecurity.comment.model;

import javax.persistence.*;


@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @SequenceGenerator(name = "comment_sequence", sequenceName = "comment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_sequence")

    private Integer id;
    private Integer author;
    private String content;
    private String date;
    private Integer particle;
    private Integer pcomment;
    private String username;

    public Comment() {
    }

    public Comment(Integer author,
                   String content,
                   String date,
                   Integer particle,
                   Integer pcomment, String username) {

        this.author = author;
        this.content = content;
        this.date = date;
        this.particle = particle;
        this.pcomment = pcomment;
        this.username = username;
    }


    public Comment(Integer id,
                   Integer author,
                   String content,
                   String date,
                   Integer particle,
                   Integer pcomment,
                   String username) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.date = date;
        this.particle = particle;
        this.pcomment = pcomment;
        this.username = username;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
}