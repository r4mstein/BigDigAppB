package ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.models;

import java.io.Serializable;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
@SuppressWarnings("unused")
public final class PictureData implements Serializable {
    private long mId;
    private String mLink;
    private long mTime;
    private int mStatus;

    public long getId() {
        return mId;
    }

    public PictureData setId(long id) {
        mId = id;
        return this;
    }

    public String getLink() {
        return mLink;
    }

    public PictureData setLink(String link) {
        mLink = link;
        return this;
    }

    public long getTime() {
        return mTime;
    }

    public PictureData setTime(long time) {
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
