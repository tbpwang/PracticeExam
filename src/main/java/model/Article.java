package model;

import java.io.Serializable;

/**
 * Administrator
 * Created by tbpwang
 * 2016/7/11.
 */
public class Article implements Serializable {
    private int id;
    private String title;
    private String abstracts;
    private String content;
    private String createDate;

    public Article() {
    }

    public Article(int id, String title, String abstracts, String content, String createDate) {
        this.id = id;
        this.title = title;
        this.abstracts = abstracts;
        this.content = content;
        this.createDate = createDate;
    }

    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + "'" +
                ", abstract= '" + abstracts + "'" +
                ", content='" + content + "'" +
                ", create date='" + createDate + "'" +
                "}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
