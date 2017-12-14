package com.example.usuario.notes.ui.notes.presenter;

import com.example.usuario.notes.data.db.model.Note;
import com.example.usuario.notes.ui.notes.contract.ListNoteFragmentContract;
import com.example.usuario.notes.ui.notes.interactor.ListNoteInteractor;
import com.example.usuario.notes.ui.notes.interactor.ListNoteInteractorImpl;

/**
 * Presenter de ListNoteFragment
 * @author Enrique Casielles
 */
public class ListNotePresenter implements ListNoteFragmentContract.Presenter, ListNoteInteractor.OnOperationFinisedListener {

    ListNoteFragmentContract.View view;
    ListNoteInteractor listener;

    public ListNotePresenter(ListNoteFragmentContract.View view) {
        this.view = view;
        this.listener = new ListNoteInteractorImpl(this);
    }

    //COMUNICACION CON LA VISTA

    //CICLO DE VIDA DE PRESENTER
    @Override
    public void onDestroy() {
        view = null;
        listener = null;
    }

    @Override
    public void onDeleteNote(Note note) {
        listener.onDeleteNote(note);
    }

    @Override
    public void onDeleteNote() {
        view.showOnDeleteMessage();
        view.onListLoaded();
    }
}
