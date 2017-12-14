package com.example.usuario.notes.ui.notes.presenter;

import com.example.usuario.notes.data.db.model.Note;
import com.example.usuario.notes.ui.notes.contract.ListNoteFragmentContract;
import com.example.usuario.notes.ui.notes.interactor.ListNoteInteractor;
import com.example.usuario.notes.ui.notes.interactor.ListNoteInteractorImpl;

import java.util.List;

/**
 * Presenter de ListNoteFragment
 * @author Enrique Casielles
 */
public class ListNotePresenter implements ListNoteFragmentContract.Presenter, ListNoteInteractor.OnOperationFinisedListener {

    ListNoteFragmentContract.View view;
    ListNoteInteractor listener;

    public ListNotePresenter(ListNoteFragmentContract.View view) {
        this.view = view;
        this.listener = new ListNoteInteractorImpl();
    }

    //COMUNICACION CON INTERACTOR
    @Override
    public void deleteNote(Note note) {
        listener.deleteNote(note, this);
    }

    //COMUNICACION CON LA VISTA
    @Override
    public void onDeleteNote(List<Note> notes, String title) {
        view.showOnDeleteMessage(title);
        view.onListLoaded(notes);
    }

    //CICLO DE VIDA DE PRESENTER
    @Override
    public void onDestroy() {
        view = null;
        listener = null;
    }


}
