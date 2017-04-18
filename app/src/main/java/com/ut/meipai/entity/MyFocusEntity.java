package com.ut.meipai.entity;

/**
 * Created by 任和 on 2017/04/17 16:42
 * Function:  我的关注对应数据的实体类
 * Desc:
 */
public class MyFocusEntity {
    private String userName;
    private String userAvatar;
    private String createDate;
    private String videoId;
    private String videoLink;
    private int    likeCount;

    public MyFocusEntity(String userName, String userAvatar, String createDate, String videoId,
                         String videoLink, int likeCount) {
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.createDate = createDate;
        this.videoId = videoId;
        this.videoLink = videoLink;
        this.likeCount = likeCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
