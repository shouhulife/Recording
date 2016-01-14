package recording.com.recording.Model;

import cn.bmob.v3.BmobObject;

/**
 * Created by hong on 2016/1/13.
 */
public class StartPage extends BmobObject {
    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getInspirational() {
        return Inspirational;
    }

    public void setInspirational(String inspirational) {
        Inspirational = inspirational;
    }

    private String Inspirational;
    private String ImgUrl;
}
