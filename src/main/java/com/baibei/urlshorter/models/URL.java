package com.baibei.urlshorter.models;

import jakarta.persistence.*;

@Entity
@Table(name = "urls")
public class URL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 2048)
    private String originalUrl;

    @Column(unique = true)
    private String shortId;

    public URL() {}

    public URL(String originalUrl, String shortId) {
        this.originalUrl = originalUrl;
        this.shortId = shortId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortId() {
        return shortId;
    }

    public void setShortId(String shortId) {
        this.shortId = shortId;
    }
}
