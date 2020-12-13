package com.example.cineaficion;


public class RSSItem {

    public String title;
    public String link;
    public String description;
    public String pubdate;
    public String guid;
    //public String enclosure;
    public String url;

    public RSSItem(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public RSSItem(String title, String link, String description, String pubdate, String guid, String url) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubdate = pubdate;
        this.guid = guid;
        //this.enclosure = enclosure;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "RSSItem{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", pubdate='" + pubdate + '\'' +
                ", guid='" + guid + '\'' +

                ", url='" + url + '\'' +
                '}';
    }
}
