package com.example.usuario.notes.ui.notes.interactor;

import com.example.usuario.notes.data.db.model.Note;
import com.example.usuario.notes.data.db.repository.NoteRepository;
import com.example.usuario.notes.ui.notes.presenter.ListNotePresenter;

import java.util.List;

/**
 * Interactor del ListNoteFragment
 */
public interface ListNoteInteractor {

    void deleteNote(Note note, OnOperationFinisedListener listener);
    void orderById(OnOperationFinisedListener listener);
    void orderByTitle(OnOperationFinisedListener listener);
    void hideInactives(OnOperationFinisedListener listener);

    interface OnOperationFinisedListener {
        void onDeleteNote(List<Note> notes, String title);
    }

}
