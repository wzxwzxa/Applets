package com.lz.applet.entity;

import java.io.Serializable;
import java.util.List;

/**
 * article
 * @author 
 */
public class Article implements Serializable {

    private Integer id;

    /**
     * 文章关联的栏目ID
     */
    private Integer columnId;

    /**
     * 文章对应的讲师id
     */
    private String lecturerId;

    /**
     * 讲师名称
     */
    private String lecturerName;

    /**
     * 文章对应的图片或是视频（0表示无图、1表示1图、3表示是三图、4表示是视频类型）
     */
    private Integer imgType;

    /**
     * 文章主题
     */
    private String title;

    /**
     * 文章简介
     */
    private String briefIntroduction;

    /**
     * 文章创建时间
     */
    private String creationTime;

    /**
     * 视频路径或图片路径数组
     */
    private String imgs;

    /**
     * 文章是否属于推荐（0表示推荐1表示不推荐）
     */
    private Integer recommend;

    /**
     * 头像
     */
    private String headPortrait;

    /**
     * 发布者
     */
    private String publisher;

    /**
     * 文章主体内容
     */
    private String content;

    /**
     * 对应的评论内容
     */
    private List<Comment> commentList;

    private static final long serialVersionUID = 1L;

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public Integer getImgType() {
        return imgType;
    }

    public void setImgType(Integer imgType) {
        this.imgType = imgType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Article other = (Article) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getColumnId() == null ? other.getColumnId() == null : this.getColumnId().equals(other.getColumnId()))
            && (this.getLecturerId() == null ? other.getLecturerId() == null : this.getLecturerId().equals(other.getLecturerId()))
            && (this.getLecturerName() == null ? other.getLecturerName() == null : this.getLecturerName().equals(other.getLecturerName()))
            && (this.getImgType() == null ? other.getImgType() == null : this.getImgType().equals(other.getImgType()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getBriefIntroduction() == null ? other.getBriefIntroduction() == null : this.getBriefIntroduction().equals(other.getBriefIntroduction()))
            && (this.getCreationTime() == null ? other.getCreationTime() == null : this.getCreationTime().equals(other.getCreationTime()))
            && (this.getImgs() == null ? other.getImgs() == null : this.getImgs().equals(other.getImgs()))
            && (this.getRecommend() == null ? other.getRecommend() == null : this.getRecommend().equals(other.getRecommend()))
            && (this.getHeadPortrait() == null ? other.getHeadPortrait() == null : this.getHeadPortrait().equals(other.getHeadPortrait()))
            && (this.getPublisher() == null ? other.getPublisher() == null : this.getPublisher().equals(other.getPublisher()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getColumnId() == null) ? 0 : getColumnId().hashCode());
        result = prime * result + ((getLecturerId() == null) ? 0 : getLecturerId().hashCode());
        result = prime * result + ((getLecturerName() == null) ? 0 : getLecturerName().hashCode());
        result = prime * result + ((getImgType() == null) ? 0 : getImgType().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getBriefIntroduction() == null) ? 0 : getBriefIntroduction().hashCode());
        result = prime * result + ((getCreationTime() == null) ? 0 : getCreationTime().hashCode());
        result = prime * result + ((getImgs() == null) ? 0 : getImgs().hashCode());
        result = prime * result + ((getRecommend() == null) ? 0 : getRecommend().hashCode());
        result = prime * result + ((getHeadPortrait() == null) ? 0 : getHeadPortrait().hashCode());
        result = prime * result + ((getPublisher() == null) ? 0 : getPublisher().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", columnId=").append(columnId);
        sb.append(", lecturerId=").append(lecturerId);
        sb.append(", lecturerName=").append(lecturerName);
        sb.append(", imgType=").append(imgType);
        sb.append(", title=").append(title);
        sb.append(", briefIntroduction=").append(briefIntroduction);
        sb.append(", creationTime=").append(creationTime);
        sb.append(", imgs=").append(imgs);
        sb.append(", recommend=").append(recommend);
        sb.append(", headPortrait=").append(headPortrait);
        sb.append(", publisher=").append(publisher);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}