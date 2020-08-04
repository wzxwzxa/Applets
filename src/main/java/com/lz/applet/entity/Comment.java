package com.lz.applet.entity;

import java.io.Serializable;
import java.util.List;

/**
 * comment
 * @author 
 */
public class Comment implements Serializable {
    /**
     * 评论表id
     */
    private Integer cId;

    /**
     * 评论者id
     */
    private String cUserid;

    /**
     * 评论者名称
     */
    private String cName;

    /**
     * 内容id(在哪个内容下评论的)
     */
    private Integer cContentid;

    /**
     * 插入时间
     */
    private String cCreatetime;

    /**
     * 评论的内容
     */
    private String cContent;

    /**
     * 给谁（该内容id的作者）留言
     */
    private String cOtherid;

    /**
     * 0-未读，1-已读
     */
    private Integer cState;

    private List<Reply> replyList;

    private static final long serialVersionUID = 1L;

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcUserid() {
        return cUserid;
    }

    public void setcUserid(String cUserid) {
        this.cUserid = cUserid;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Integer getcContentid() {
        return cContentid;
    }

    public void setcContentid(Integer cContentid) {
        this.cContentid = cContentid;
    }

    public String getcCreatetime() {
        return cCreatetime;
    }

    public void setcCreatetime(String cCreatetime) {
        this.cCreatetime = cCreatetime;
    }

    public String getcContent() {
        return cContent;
    }

    public void setcContent(String cContent) {
        this.cContent = cContent;
    }

    public String getcOtherid() {
        return cOtherid;
    }

    public void setcOtherid(String cOtherid) {
        this.cOtherid = cOtherid;
    }

    public Integer getcState() {
        return cState;
    }

    public void setcState(Integer cState) {
        this.cState = cState;
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
        Comment other = (Comment) that;
        return (this.getcId() == null ? other.getcId() == null : this.getcId().equals(other.getcId()))
            && (this.getcUserid() == null ? other.getcUserid() == null : this.getcUserid().equals(other.getcUserid()))
            && (this.getcName() == null ? other.getcName() == null : this.getcName().equals(other.getcName()))
            && (this.getcContentid() == null ? other.getcContentid() == null : this.getcContentid().equals(other.getcContentid()))
            && (this.getcCreatetime() == null ? other.getcCreatetime() == null : this.getcCreatetime().equals(other.getcCreatetime()))
            && (this.getcContent() == null ? other.getcContent() == null : this.getcContent().equals(other.getcContent()))
            && (this.getcOtherid() == null ? other.getcOtherid() == null : this.getcOtherid().equals(other.getcOtherid()))
            && (this.getcState() == null ? other.getcState() == null : this.getcState().equals(other.getcState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getcId() == null) ? 0 : getcId().hashCode());
        result = prime * result + ((getcUserid() == null) ? 0 : getcUserid().hashCode());
        result = prime * result + ((getcName() == null) ? 0 : getcName().hashCode());
        result = prime * result + ((getcContentid() == null) ? 0 : getcContentid().hashCode());
        result = prime * result + ((getcCreatetime() == null) ? 0 : getcCreatetime().hashCode());
        result = prime * result + ((getcContent() == null) ? 0 : getcContent().hashCode());
        result = prime * result + ((getcOtherid() == null) ? 0 : getcOtherid().hashCode());
        result = prime * result + ((getcState() == null) ? 0 : getcState().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cId=").append(cId);
        sb.append(", cUserid=").append(cUserid);
        sb.append(", cName=").append(cName);
        sb.append(", cContentid=").append(cContentid);
        sb.append(", cCreatetime=").append(cCreatetime);
        sb.append(", cContent=").append(cContent);
        sb.append(", cOtherid=").append(cOtherid);
        sb.append(", cState=").append(cState);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}