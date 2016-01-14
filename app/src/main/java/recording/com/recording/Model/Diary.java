package recording.com.recording.Model;

import cn.bmob.v3.BmobObject;

/**
 * 日记
 * Created by hong on 2016/1/14.
 */
public class Diary extends BmobObject{
    private String userid;
    private long date;
    private String title;
    private String content;
    private String remark;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
