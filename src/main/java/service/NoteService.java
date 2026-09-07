package service;

import com.example.todolist.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class NoteService {

    private final Map<Long, Note> notes = new HashMap<>();

    public List<Note> listAll() {
        return new ArrayList<>(notes.values());
    }

    public Note add(Note note) {
        long id;

        do {
            id = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        } while (notes.containsKey(id));

        note.setId(id);
        notes.put(id, note);

        return note;
    }

    public void deleteById(long id) {
        if (!notes.containsKey(id)) {
            throw new NoSuchElementException("Note with id " + id + " not found");
        }

        notes.remove(id);
    }

    public void update(Note note) {
        if (!notes.containsKey(note.getId())) {
            throw new NoSuchElementException("Note with id " + note.getId() + " not found");
        }

        Note existingNote = notes.get(note.getId());
        existingNote.setTitle(note.getTitle());
        existingNote.setContent(note.getContent());
    }

    public Note getById(long id) {
        Note note = notes.get(id);

        if (note == null) {
            throw new NoSuchElementException("Note with id " + id + " not found");
        }

        return note;
    }
}
