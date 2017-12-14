package com.example.usuario.notes.ui.notes.contract;

import com.example.usuario.notes.data.db.model.Note;
import com.example.usuario.notes.ui.base.BasePresenter;

import java.io.Serializable;

/**
 * Created by usuario on 12/14/17.
 */

public interface ListNoteFragmentContract {

    interface View {
        void setPresenter(ListNoteFragmentContract.Presenter presenter);
        void onListLoaded();
        void showOnDeleteMessage();
    }
    interface Presenter extends BasePresenter, Serializable {
        void onDeleteNote(Note note);
    }

}
