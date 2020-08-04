package com.lz.applet.entity;

import java.io.Serializable;

/**
 * reply
 * @author 
 */
public class Reply implements Serializable {
    /**
     * 回复表id
     */
    private Integer rId;

    /**
     * 回复的用户账号
     */
    private String rUserid;

    /**
     * 回复名称
     */
    private String rName;

    /**
     * 插入时间
     */
    private String rCreatime;

    /**
     * 回复的内容
     */
    private String rContent;

    /**
     * 给谁回复
     */
    private String rOtherid;

    /**
     * 在哪个留言下的回复
     */
    private String rWords;

    /**
     * 那篇分享下的回复
     */
    private Integer rContentid;

    /**
     * 0-未读，1-已读
     */
    private Integer rState;

    /**
     * 被回复人姓名
     */
    private String rRespondent;

    /**
     * 回复人头像
     */
    private String rAvatarUrl;

    private static final long serialVersionUID = 1L;

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getrUserid() {
        return rUserid;
    }

    public void setrUserid(String rUserid) {
        this.rUserid = rUserid;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrCreatime() {
        return rCreatime;
    }

    public void setrCreatime(String rCreatime) {
        this.rCreatime = rCreatime;
    }

    public String getrContent() {
        return rContent;
    }

    public void setrContent(String rContent) {
        this.rContent = rContent;
    }

    public String getrOtherid() {
        return rOtherid;
    }

    public void setrOtherid(String rOtherid) {
        this.rOtherid = rOtherid;
    }

    public String getrWords() {
        return rWords;
    }

    public void setrWords(String rWords) {
        this.rWords = rWords;
    }

    public Integer getrContentid() {
        return rContentid;
    }

    public void setrContentid(Integer rContentid) {
        this.rContentid = rContentid;
    }

    public Integer getrState() {
        return rState;
    }

    public void setrState(Integer rState) {
        this.rState = rState;
    }

    public String getrRespondent() {
        return rRespondent;
    }

    public void setrRespondent(String rRespondent) {
        this.rRespondent = rRespondent;
    }

    public String getrAvatarUrl() {
        return rAvatarUrl;
    }

    public void setrAvatarUrl(String rAvatarUrl) {
        this.rAvatarUrl = rAvatarUrl;
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
        Reply other = (Reply) that;
        return (this.getrId() == null ? other.getrId() == null : this.getrId().equals(other.getrId()))
            && (this.getrUserid() == null ? other.getrUserid() == null : this.getrUserid().equals(other.getrUserid()))
            && (this.getrName() == null ? other.getrName() == null : this.getrName().equals(other.getrName()))
            && (this.getrCreatime() == null ? other.getrCreatime() == null : this.getrCreatime().equals(other.getrCreatime()))
            && (this.getrContent() == null ? other.getrContent() == null : this.getrContent().equals(other.getrContent()))
            && (this.getrOtherid() == null ? other.getrOtherid() == null : this.getrOtherid().equals(other.getrOtherid()))
            && (this.getrWords() == null ? other.getrWords() == null : this.getrWords().equals(other.getrWords()))
            && (this.getrContentid() == null ? other.getrContentid() == null : this.getrContentid().equals(other.getrContentid()))
            && (this.getrState() == null ? other.getrState() == null : this.getrState().equals(other.getrState()))
            && (this.getrRespondent() == null ? other.getrRespondent() == null : this.getrRespondent().equals(other.getrRespondent()))
            && (this.getrAvatarUrl() == null ? other.getrAvatarUrl() == null : this.getrAvatarUrl().equals(other.getrAvatarUrl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getrId() == null) ? 0 : getrId().hashCode());
        result = prime * result + ((getrUserid() == null) ? 0 : getrUserid().hashCode());
        result = prime * result + ((getrName() == null) ? 0 : getrName().hashCode());
        result = prime * result + ((getrCreatime() == null) ? 0 : getrCreatime().hashCode());
        result = prime * result + ((getrContent() == null) ? 0 : getrContent().hashCode());
        result = prime * result + ((getrOtherid() == null) ? 0 : getrOtherid().hashCode());
        result = prime * result + ((getrWords() == null) ? 0 : getrWords().hashCode());
        result = prime * result + ((getrContentid() == null) ? 0 : getrContentid().hashCode());
        result = prime * result + ((getrState() == null) ? 0 : getrState().hashCode());
        result = prime * result + ((getrRespondent() == null) ? 0 : getrRespondent().hashCode());
        result = prime * result + ((getrAvatarUrl() == null) ? 0 : getrAvatarUrl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rId=").append(rId);
        sb.append(", rUserid=").append(rUserid);
        sb.append(", rName=").append(rName);
        sb.append(", rCreatime=").append(rCreatime);
        sb.append(", rContent=").append(rContent);
        sb.append(", rOtherid=").append(rOtherid);
        sb.append(", rWords=").append(rWords);
        sb.append(", rContentid=").append(rContentid);
        sb.append(", rState=").append(rState);
        sb.append(", rRespondent=").append(rRespondent);
        sb.append(", rAvatarUrl=").append(rAvatarUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}