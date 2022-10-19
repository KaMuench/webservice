package webservice.entities;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Notes {
    private Map<Long, Note> mNotes = new HashMap<>();

    public long createNote(String title, String content, String authorFirstname, String authorLastName) {
        Note newNote =  new Note(title, content, authorFirstname, authorLastName);
        mNotes.put(newNote.getId(), newNote);
        return newNote.getId();
    }

    public Note readNote(long id) {
        return mNotes.get(id);
    }

    public long updateNote(long id,String title,String content, String authorFirstName, String authorLastName ) {
        Note note = mNotes.get(id);
        if(note != null) {
            return mNotes.get(id).update(title,content,authorFirstName,authorLastName);
        } else {
            return 0;
        }
    }

    public boolean deleteNote(long id) {
        Note note=mNotes.remove(id);
        //note is null if there was no note with the id
        return (note!=null);
    }

    public Collection<Note> readAll() {
        return mNotes.values();
    }
}
