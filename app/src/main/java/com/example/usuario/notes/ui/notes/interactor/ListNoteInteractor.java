package com.example.usuario.notes.ui.notes.interactor;

import com.example.usuario.notes.data.db.model.Note;

/**
 * Interactor del ListNoteFragment
 */
public interface ListNoteInteractor {

    void onDeleteNote(Note note);

    interface OnOperationFinisedListener {
        void onDeleteNote();
    }

}
