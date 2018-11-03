package ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.models;

import java.io.Serializable;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
@SuppressWarnings("unused")
public final class PictureData implements Serializable {
    private String mLink;
    private String mTime;
    private int mStatus;

    public String getLink() {
        return mLink;
    }

    public PictureData setLink(String link) {
        mLink = link;
        return this;
    }

    public String getTime() {
        return mTime;
    }

    public PictureData setTime(String time) {
        mTime = time;
        return this;
    }

    public int getStatus() {
        return mStatus;
    }

    public PictureData setStatus(int status) {
        mStatus = status;
        return this;
    }

    @Override
    public String toString() {
        return "PictureData{" +
                "mLink='" + mLink + '\'' +
                ", mTime='" + mTime + '\'' +
                ", mStatus=" + mStatus +
                '}';
    }
}
