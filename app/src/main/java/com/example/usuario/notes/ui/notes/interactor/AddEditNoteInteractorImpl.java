package com.example.usuario.notes.ui.notes.interactor;


import com.example.usuario.notes.data.db.model.Note;
import com.example.usuario.notes.data.db.repository.NoteRepository;

/**
 * Created by usuario on 12/14/17.
 */

public class AddEditNoteInteractorImpl implements AddEditNoteInteractor {

    OnAddEditInteractorListener listener;

    public AddEditNoteInteractorImpl(OnAddEditInteractorListener listener) {
        this.listener = listener;
    }

    @Override
    public void validateNote(String title, String description, boolean checked) {
        if(validateTitle(title)) {
            NoteRepository.getInstance().add(new Note(title, description, checked));
            listener.onSuccess();
        }
    }

    @Override
    public boolean validateTitle(String title) {
        if(title.isEmpty()) {
            listener.onTitleEmptyError();
            return false;
        }
        if(NoteRepository.getInstance().exists(title)) {
            listener.onNoteAlreadyExists();
            return false;
        }
        return true;
    }

}
