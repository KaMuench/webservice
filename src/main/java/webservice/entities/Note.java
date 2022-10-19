package webservice.entities;

public class Note {
    private static long sNextId=1;
    private long mId;
    private String mTitle;
    private String mContent;
    private Author mAuthor;

    public Note(String title, String content, String authorFirstName, String authorLastName) {
        mId=sNextId;
        sNextId++;
        this.mTitle = title;
        this.mContent = content;
        this.mAuthor = new Author(authorFirstName, authorLastName);
    }

    public long getId() {
        return mId;
    }

    public long update(String title, String content, String authorFirstName, String authorLastName) {
        mTitle=title;
        mContent=content;
        mAuthor.setmFirstName(authorFirstName);
        mAuthor.setmLastName(authorLastName);
        return getId();
    }

    @Override
    public String toString() {
        return String.format("ID:%10d%nTitle:%10s%nContent:%10s%nAuthor%s%n",mId,mTitle,mContent,mAuthor);
    }
}
