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
    private Integer parent_article;
    private Integer parent_comment;

    public Comment() {
    }

    public Comment(Integer author,
                   String content,
                   String date,
                   Integer parent_article,
                   Integer parent_comment) {

        this.author = author;
        this.content = content;
        this.date = date;
        this.parent_article = parent_article;
        this.parent_comment = parent_comment;
    }


    public Comment(Integer id,
                   Integer author,
                   String content,
                   String date,
                   Integer parent_article,
                   Integer parent_comment) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.date = date;
        this.parent_article = parent_article;
        this.parent_comment = parent_comment;
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

    public Integer getParent_article() {
        return parent_article;
    }

    public void setParent_article(Integer parent_article) {
        this.parent_article = parent_article;
    }

    public Integer getParent_comment() {
        return parent_comment;
    }

    public void setParent_comment(Integer parent_comment) {
        this.parent_comment = parent_comment;
    }
}