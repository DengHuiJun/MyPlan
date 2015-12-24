package com.zero.myplan.core.model;

/**
 * Created by zero on 15-12-18.
 */
public class PlanM {

    // 阅读
    public static final int TYPE_READ = 0;

    // 旅游
    public static final int TYPE_TRAVEL = 1;

    private int id;
    private long createdTime;
    private long lastUpateTime;
    private long doneTime;
    private int type;
    private String content;
    private boolean hasDone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getLastUpateTime() {
        return lastUpateTime;
    }

    public void setLastUpateTime(long lastUpateTime) {
        this.lastUpateTime = lastUpateTime;
    }

    public long getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(long doneTime) {
        this.doneTime = doneTime;
    }

    public String getType() {
        switch (type) {
            case TYPE_READ:
                return "阅读";
            case TYPE_TRAVEL:
                return "旅游";
        }
        return "";
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isHasDone() {
        return hasDone;
    }

    public void setHasDone(int isDone) {
        if (isDone == 0) {
            this.hasDone = false;
        } else {
            this.hasDone = true;
        }
    }

}
