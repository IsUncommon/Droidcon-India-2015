package is.uncommon.droidcon2015.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PrimaryContent implements Parcelable {

    public String sectionName;
    public String summary;

    @Override
    public String toString() {
        return "PrimaryContent{" +
                "sectionName='" + sectionName + '\'' +
                ", summary='" + summary + '\'' +
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
    }

    public PrimaryContent() {
    }

    protected PrimaryContent(Parcel in) {
        this.sectionName = in.readString();
        this.summary = in.readString();
    }

    public static final Parcelable.Creator<PrimaryContent> CREATOR = new Parcelable.Creator<PrimaryContent>() {
        public PrimaryContent createFromParcel(Parcel source) {
            return new PrimaryContent(source);
        }

        public PrimaryContent[] newArray(int size) {
            return new PrimaryContent[size];
        }
    };
}
