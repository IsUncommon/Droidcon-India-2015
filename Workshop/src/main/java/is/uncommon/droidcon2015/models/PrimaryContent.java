package is.uncommon.droidcon2015.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

public class PrimaryContent implements Parcelable {

    public String sectionName;
    public String summary;
    @DrawableRes public int image;
    @ColorRes public int color;

    @Override
    public String toString() {
        return "PrimaryContent{" +
                "sectionName='" + sectionName + '\'' +
                ", summary='" + summary + '\'' +
                ", image=" + image +
                ", color=" + color +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sectionName);
        dest.writeString(this.summary);
        dest.writeInt(this.image);
        dest.writeInt(this.color);
    }

    public PrimaryContent() {
    }

    protected PrimaryContent(Parcel in) {
        this.sectionName = in.readString();
        this.summary = in.readString();
        this.image = in.readInt();
        this.color = in.readInt();
    }

    public static final Creator<PrimaryContent> CREATOR = new Creator<PrimaryContent>() {
        public PrimaryContent createFromParcel(Parcel source) {
            return new PrimaryContent(source);
        }

        public PrimaryContent[] newArray(int size) {
            return new PrimaryContent[size];
        }
    };
}
