package com.example.usuario.notes.ui.notes.interactor;

/**
 * Created by usuario on 12/14/17.
 */

public interface AddEditNoteInteractor {

    void validateNote(String title, String description, boolean checked);
    boolean validateTitle(String title);

    interface OnAddEditInteractorListener {
        void onTitleEmptyError();
        void onNoteAlreadyExists();
        void onSuccess();
    }

}
