package com.catalyseit;

import java.util.List;

public class EstResponse {

    private String id_token;
    private Long id;
    private String name;
    private Long pages;
    private List<EstRecipient> recipients;
    private String status;

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }

    public String getIdToken() {
        return id_token;
    }

    public void setIdToken(String id_token) {
        this.id_token = id_token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public List<EstRecipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<EstRecipient> recipients) {
        this.recipients = recipients;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
