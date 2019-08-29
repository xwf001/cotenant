package com.youyu.cotenant.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CotenantGroupUser implements Serializable {
    private Long id;

    private Long cotenantGroupId;

    private Long cotenantUserId;

    private Integer role;

    private Integer status;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCotenantGroupId() {
        return cotenantGroupId;
    }

    public void setCotenantGroupId(Long cotenantGroupId) {
        this.cotenantGroupId = cotenantGroupId;
    }

    public Long getCotenantUserId() {
        return cotenantUserId;
    }

    public void setCotenantUserId(Long cotenantUserId) {
        this.cotenantUserId = cotenantUserId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}