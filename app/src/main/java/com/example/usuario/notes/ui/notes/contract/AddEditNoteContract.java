package com.example.usuario.notes.ui.notes.contract;

import com.example.usuario.notes.ui.base.BasePresenter;

import java.io.Serializable;

/**
 * Created by usuario on 12/14/17.
 */

public interface AddEditNoteContract {
    interface View {
        void setPresenter(AddEditNoteContract.Presenter presenter);
        void setTitleEmptyError();
        void setAlreadyExistsError();
        void onSuccess();
    }
    interface Presenter extends BasePresenter, Serializable {
        void onAddNote(String title, String description, boolean checked);
    }

}
