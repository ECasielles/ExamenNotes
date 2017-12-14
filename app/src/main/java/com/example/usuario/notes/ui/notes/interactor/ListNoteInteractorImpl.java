package com.example.usuario.notes.ui.notes.interactor;


import com.example.usuario.notes.data.db.model.Note;
import com.example.usuario.notes.data.db.repository.NoteRepository;

/**
 * Interactor de ListNoteFragment. Hereda de ListNoteInteractor.
 * @author Enrique Casielles
 */
public class ListNoteInteractorImpl implements ListNoteInteractor {

    OnOperationFinisedListener listener;

    public ListNoteInteractorImpl(OnOperationFinisedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onDeleteNote(Note note) {
        NoteRepository.getInstance().delete(note);
        listener.onDeleteNote();
    }

}
