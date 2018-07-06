package cn.momosv.websocket.model;

import java.util.Date;

public class TbFriendPO {
    private String id;

    private String userId;

    private String friId;

    private Byte concern;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getFriId() {
        return friId;
    }

    public void setFriId(String friId) {
        this.friId = friId == null ? null : friId.trim();
    }

    public Byte getConcern() {
        return concern;
    }

    public void setConcern(Byte concern) {
        this.concern = concern;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}