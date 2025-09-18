package com.example.wattbook.Dto;

public class SeguidorDTO {
    private Long id;
    private Long userId;
    private Long seguidorId;

    public SeguidorDTO() {
    }

    public SeguidorDTO(Long userId, Long seguidorId) {
        this.userId = userId;
        this.seguidorId = seguidorId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSeguidorId() {
        return seguidorId;
    }

    public void setSeguidorId(Long seguidorId) {
        this.seguidorId = seguidorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
