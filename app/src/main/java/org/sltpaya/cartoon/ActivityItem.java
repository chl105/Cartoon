package org.sltpaya.cartoon;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: SLTPAYA
 * Date: 2017/3/9
 */
public class ActivityItem implements Parcelable{

    private String activityTitle;//需要显示的Activity标题
    private String callFeature;//上级调用者的特征
    private String type;//调用者所属的类别

   public static final Creator<ActivityItem> CREATOR = new Creator<ActivityItem>(){
       @Override
       public ActivityItem createFromParcel(Parcel source) {
           ActivityItem item = new ActivityItem(null, null);
           item.activityTitle = source.readString();
           item.callFeature = source.readString();
           item.type = source.readString();
           return item;
       }

       @Override
       public ActivityItem[] newArray(int size) {
           return new ActivityItem[size];
       }
   };

    public ActivityItem(String activityTitle, String callFeature) {
        this.activityTitle = activityTitle;
        this.callFeature = callFeature;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public String getCallFeature() {
        return callFeature;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(activityTitle);
        dest.writeString(callFeature);
        dest.writeString(type);
    }

}
