package com.J621.vo;

import java.math.BigDecimal;

public class J621Image extends J621ImageKey {
    private String url;

    private String filePath;

    private String imageType;

    private String score;

    private String keyses;

    private Integer imageCount;

    private String createDay;

    private String userId;

    private BigDecimal salt;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType == null ? null : imageType.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public String getKeyses() {
        return keyses;
    }

    public void setKeyses(String keyses) {
        this.keyses = keyses == null ? null : keyses.trim();
    }

    public Integer getImageCount() {
        return imageCount;
    }

    public void setImageCount(Integer imageCount) {
        this.imageCount = imageCount;
    }

    public String getCreateDay() {
        return createDay;
    }

    public void setCreateDay(String createDay) {
        this.createDay = createDay == null ? null : createDay.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public BigDecimal getSalt() {
        return salt;
    }

    public void setSalt(BigDecimal salt) {
        this.salt = salt;
    }
}