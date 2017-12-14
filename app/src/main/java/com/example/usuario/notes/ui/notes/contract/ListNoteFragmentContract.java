package com.example.usuario.notes.ui.notes.contract;

import com.example.usuario.notes.data.db.model.Note;
import com.example.usuario.notes.ui.base.BasePresenter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by usuario on 12/14/17.
 */

public interface ListNoteFragmentContract {

    interface View {
        void setPresenter(ListNoteFragmentContract.Presenter presenter);
        void onListLoaded(List<Note> notes);
        void showOnDeleteMessage(String title);
    }
    interface Presenter extends BasePresenter, Serializable {
        void deleteNote(Note note);
    }

}
