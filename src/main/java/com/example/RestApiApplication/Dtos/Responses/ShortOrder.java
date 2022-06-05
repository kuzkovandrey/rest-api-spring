package com.example.RestApiApplication.Dtos.Responses;

import java.util.Date;

public class ShortOrder {
    private Long id;

    private String clientName;

    private Date createdAt;

    public ShortOrder() {}

    public ShortOrder(Long id, String clientName, Date createdAt) {
        this.id = id;
        this.clientName = clientName;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
