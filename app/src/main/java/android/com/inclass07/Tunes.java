package android.com.inclass07;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by murali101002 on 10/3/2016.
 */
public class Tunes implements Parcelable, Comparable<Tunes> {
    String title, summary,relDate, smallImgUrl, largeImgUrl;

    public Tunes() {

    }



    protected Tunes(Parcel in) {
        title = in.readString();
        summary = in.readString();
        relDate = in.readString();
        smallImgUrl = in.readString();
        largeImgUrl = in.readString();
    }

    public static final Creator<Tunes> CREATOR = new Creator<Tunes>() {
        @Override
        public Tunes createFromParcel(Parcel in) {
            return new Tunes(in);
        }

        @Override
        public Tunes[] newArray(int size) {
            return new Tunes[size];
        }
    };

    public String getLargeImgUrl() {
        return largeImgUrl;
    }

    public void setLargeImgUrl(String largeImgUrl) {
        this.largeImgUrl = largeImgUrl;
    }

    public String getRelDate() {
        return relDate;
    }

    public void setRelDate(String relDate) {
        this.relDate = relDate;
    }

    public String getSmallImgUrl() {
        return smallImgUrl;
    }

    public void setSmallImgUrl(String smallImgUrl) {
        this.smallImgUrl = smallImgUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(summary);
        dest.writeString(relDate);
        dest.writeString(smallImgUrl);
        dest.writeString(largeImgUrl);
    }

    @Override
    public int compareTo(Tunes another) {
        Date thisDate = new Date(this.getRelDate());
        Date anotherDate = new Date(another.getRelDate());
        if(thisDate.compareTo(anotherDate)>0) return 1;
        else if(thisDate.compareTo(anotherDate)<0) return -1;
        return 0;
    }
}
