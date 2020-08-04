package com.lz.applet.entity;

import java.io.Serializable;

/**
 * lecturer
 * @author 
 */
public class Lecturer implements Serializable {
    private Integer id;

    /**
     * 讲师名称
     */
    private String lecturerName;

    /**
     * 讲师海报图片
     */
    private String img;

    /**
     * 讲师介绍讲师简介
     */
    private String lecturerIntroduce;

    /**
     * 讲师从业时间
     */
    private String workingYears;

    /**
     * 讲师职务
     */
    private String lecturerPost;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLecturerIntroduce() {
        return lecturerIntroduce;
    }

    public void setLecturerIntroduce(String lecturerIntroduce) {
        this.lecturerIntroduce = lecturerIntroduce;
    }

    public String getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(String workingYears) {
        this.workingYears = workingYears;
    }

    public String getLecturerPost() {
        return lecturerPost;
    }

    public void setLecturerPost(String lecturerPost) {
        this.lecturerPost = lecturerPost;
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
        Lecturer other = (Lecturer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLecturerName() == null ? other.getLecturerName() == null : this.getLecturerName().equals(other.getLecturerName()))
            && (this.getImg() == null ? other.getImg() == null : this.getImg().equals(other.getImg()))
            && (this.getLecturerIntroduce() == null ? other.getLecturerIntroduce() == null : this.getLecturerIntroduce().equals(other.getLecturerIntroduce()))
            && (this.getWorkingYears() == null ? other.getWorkingYears() == null : this.getWorkingYears().equals(other.getWorkingYears()))
            && (this.getLecturerPost() == null ? other.getLecturerPost() == null : this.getLecturerPost().equals(other.getLecturerPost()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLecturerName() == null) ? 0 : getLecturerName().hashCode());
        result = prime * result + ((getImg() == null) ? 0 : getImg().hashCode());
        result = prime * result + ((getLecturerIntroduce() == null) ? 0 : getLecturerIntroduce().hashCode());
        result = prime * result + ((getWorkingYears() == null) ? 0 : getWorkingYears().hashCode());
        result = prime * result + ((getLecturerPost() == null) ? 0 : getLecturerPost().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", lecturerName=").append(lecturerName);
        sb.append(", img=").append(img);
        sb.append(", lecturerIntroduce=").append(lecturerIntroduce);
        sb.append(", workingYears=").append(workingYears);
        sb.append(", lecturerPost=").append(lecturerPost);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}