package com.exam.springsecurity.article.model;


import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @SequenceGenerator(name = "article_sequence", sequenceName = "article_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_sequence")

    private Integer id;
    private String author;
    private String category;
    private String content;
    private String description;
    private String source_id;
    private String source_name;
    private String publishedat;
    private String title;
    private String url;
    private String urltoimage;


    public Article() {
    }

    public Article(String author,
                   String category,
                   String content,
                   String description,
                   String source_id,
                   String source_name,
                   String publishedat,
                   String title,
                   String url,
                   String urltoimage) {

        this.author = author;
        this.category = category;
        this.content = content;
        this.description = description;
        this.source_id = source_id;
        this.source_name = source_name;
        this.publishedat = publishedat;
        this.title = title;
        this.url = url;
        this.urltoimage = urltoimage;
    }

    public Article(Integer id,
                   String author,
                   String category,
                   String content,
                   String description,
                   String source_id,
                   String source_name,
                   String publishedat,
                   String title,
                   String url,
                   String urltoimage) {

        this.id = id;
        this.author = author;
        this.category = category;
        this.content = content;
        this.description = description;
        this.source_id = source_id;
        this.source_name = source_name;
        this.publishedat = publishedat;
        this.title = title;
        this.url = url;
        this.urltoimage = urltoimage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public String getPublishedat() {
        return publishedat;
    }

    public void setPublishedat(String publishedat) {
        this.publishedat = publishedat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrltoimage() {
        return urltoimage;
    }

    public void setUrltoimage(String urltoimage) {
        this.urltoimage = urltoimage;
    }
}
