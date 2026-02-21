package module6_4.Model;

import java.util.ArrayList;
import java.util.List;

public class Notebook {
    private List<Note> notes = new ArrayList<>();

    public void addNote(Note note) {
        notes.add(note);
    }

    public List<Note> getNotes() {
        return notes;
    }
}
