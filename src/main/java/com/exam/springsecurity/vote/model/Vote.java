package com.exam.springsecurity.vote.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vote")

public class Vote {

    @Id
    @SequenceGenerator(name = "vote_sequence", sequenceName = "vote_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_sequence")
    private Integer id;
    private Integer articleid;
    private Integer commentid;
    private Date date;
    private Integer author;
    private Integer vote;

    public Vote() {
    }


    public Vote(Integer id,
                Integer articleid,
                Integer commentid,
                Date date,
                Integer author,
                Integer vote) {
        this.id = id;
        this.articleid = articleid;
        this.commentid = commentid;
        this.date = date;
        this.author = author;
        this.vote = vote;

    }

    public Vote(
            Integer articleid,
            Integer commentid,
            Date date,
            Integer author,
            Integer vote) {
        this.articleid = articleid;
        this.commentid = commentid;
        this.date = date;
        this.author = author;
        this.vote = vote;

    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }
}