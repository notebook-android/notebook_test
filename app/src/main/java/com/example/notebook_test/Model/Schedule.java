package com.example.notebook_test.Model;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class Schedule extends LitePalSupport {

    private int id;         //primarykey autoincrement 主键 自增

    private String title;   //标题

    private String content; //内容

    private Date createTime;    //创建时间

    private Date startTime;     //开始时间

    private Date finishTime;    //完成时间

    private boolean allDay;     //是否全天

    private int repetition;     //重复类型 0:永不 1:每天 2:每周 3:每两周 4:每月 5:每年

    private int type;           //任务类型 0:工作 1:生活 2:运动 3:出行

    public Schedule(String title, String content, Date createTime, Date startTime, Date finishTime, boolean allDay, int repetition, int type) {
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.allDay = allDay;
        this.repetition = repetition;
        this.type = type;
    }

    public Schedule(){}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public boolean isAllDay() {
        return allDay;
    }

    public int getRepetition() {
        return repetition;
    }

    public int getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public void setType(int type) {
        this.type = type;
    }
}
