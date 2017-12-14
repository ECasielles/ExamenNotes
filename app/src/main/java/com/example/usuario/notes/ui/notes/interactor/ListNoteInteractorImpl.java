package com.example.usuario.notes.ui.notes.interactor;


import com.example.usuario.notes.data.db.model.Note;
import com.example.usuario.notes.data.db.repository.NoteRepository;
import com.example.usuario.notes.ui.notes.presenter.ListNotePresenter;

/**
 * Interactor de ListNoteFragment. Hereda de ListNoteInteractor.
 * @author Enrique Casielles
 */
public class ListNoteInteractorImpl implements ListNoteInteractor {

    public ListNoteInteractorImpl() {
    }

    @Override
    public void deleteNote(Note note, OnOperationFinisedListener listener) {
        NoteRepository.getInstance().delete(note);
        listener.onDeleteNote(NoteRepository.getInstance(), note.getTitle());
    }

    @Override
    public void orderById(OnOperationFinisedListener listener) {

    }
    @Override
    public void orderByTitle(OnOperationFinisedListener listener) {

    }
    @Override
    public void hideInactives(OnOperationFinisedListener listener) {

    }

}
