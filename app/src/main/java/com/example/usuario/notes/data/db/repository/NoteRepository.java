package com.example.usuario.notes.data.db.repository;

import com.example.usuario.notes.data.db.model.Note;
import com.example.usuario.notes.ui.notes.interactor.AddEditNoteInteractor;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Repositorio de objetos Note que representa la base de datos.
 * @author Enrique Casielles
 */
public class NoteRepository extends ArrayList<Note> {

    private static NoteRepository noteRepository;

    static {
        noteRepository = new NoteRepository();
    }

    private NoteRepository() {
        init();
    }

    private void init() {
        add(new Note("Comer com papá", "En el Mesón Ibérico", true));
        add(new Note("Animar a Jaime", "Malditos cortes de luz", false));
        add(new Note("Aprobar el curso", "Aunque sea en junio", true));
        add(new Note("Llamar al cole", "Para quejarme de los cortes", true));
        add(new Note("Hacer la compra", "Jamón, queso, aceite, tomate...", false));
        add(new Note("Comer com papá", "En el Mesón Ibérico", true));
        add(new Note("Animar a Jaime", "Malditos cortes de luz", false));
        add(new Note("Aprobar el curso", "Aunque sea en junio", true));
        add(new Note("Llamar al cole", "Para quejarme de los cortes", true));
        add(new Note("Hacer la compra", "Jamón, queso, aceite, tomate...", false));
        add(new Note("Acabar ya el ejercicio", "Son las 10:19", true));
    }

    public static NoteRepository getInstance(){
        if(noteRepository == null)
            noteRepository = new NoteRepository();
        return noteRepository;
    }


    public boolean exists(String title) {
        boolean result = true;
        for (Note note: noteRepository)
            if(note.getTitle().equals(title)) {
                result = false;
                break;
            }
        return result;
    }


    public void delete(Note note) {
        Iterator<Note> iterator = noteRepository.iterator();
        while (iterator.hasNext())
            if(iterator.next().equals(note)) {
                iterator.remove();
                break;
            }
    }
}
