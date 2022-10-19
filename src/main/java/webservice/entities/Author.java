package webservice.entities;

public class Author {
    private static long sNextId=1;
    private long mId;
    private String mFirstName;
    private String mLastName;

    public Author(String mFirstName, String mLastName) {
        mId = sNextId;
        sNextId++;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public long getmId() {
        return mId;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }
}
