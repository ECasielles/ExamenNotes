package com.example.usuario.notes.ui.notes.presenter;

import com.example.usuario.notes.ui.notes.contract.AddEditNoteContract;
import com.example.usuario.notes.ui.notes.interactor.AddEditNoteInteractor;
import com.example.usuario.notes.ui.notes.interactor.AddEditNoteInteractorImpl;

/**
 * Presenter de AddEditNoteFragment
 * @author Enrique Casielles
 */

public class AddEditNotePresenter implements AddEditNoteContract.Presenter,
        AddEditNoteInteractor.OnAddEditInteractorListener {

    private AddEditNoteContract.View view;
    private AddEditNoteInteractor listener;

    public AddEditNotePresenter(AddEditNoteContract.View view) {
        this.view = view;
        this.listener = new AddEditNoteInteractorImpl(this);
    }

    @Override
    public void onAddNote(String title, String description, boolean checked) {
        listener.validateNote(title, description, checked);
    }
    @Override
    public void onTitleEmptyError() {
        view.setTitleEmptyError();
    }
    @Override
    public void onNoteAlreadyExists() {
        view.setAlreadyExistsError();
    }
    @Override
    public void onSuccess() {
        view.onSuccess();
    }

    @Override
    public void onDestroy() {
        listener = null;
    }

}
