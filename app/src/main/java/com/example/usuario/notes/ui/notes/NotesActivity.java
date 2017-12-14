package com.example.usuario.notes.ui.notes;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.usuario.notes.R;
import com.example.usuario.notes.ui.notes.fragment.AddEditNoteFragment;
import com.example.usuario.notes.ui.notes.fragment.ListNoteFragment;
import com.example.usuario.notes.ui.notes.presenter.AddEditNotePresenter;
import com.example.usuario.notes.ui.notes.presenter.ListNotePresenter;

/**
 * Clase que muestra una lista de notas y permite editarla
 * @author Enrique Casielles
 */
public class NotesActivity extends AppCompatActivity implements ListNoteFragment.OnAddNoteListener,
        AddEditNoteFragment.OnAddEditFinishedListener {

    ListNoteFragment listNoteFragment;
    ListNotePresenter listNotePresenter;
    AddEditNoteFragment addEditNoteFragment;
    AddEditNotePresenter addEditNotePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        listNoteFragment = (ListNoteFragment) getSupportFragmentManager().findFragmentByTag(ListNoteFragment.TAG);
        if (listNoteFragment == null) {
            listNoteFragment = ListNoteFragment.newInstance(null);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(android.R.id.content, listNoteFragment, ListNoteFragment.TAG).commit();
        }
        listNotePresenter = new ListNotePresenter(listNoteFragment);
        listNoteFragment.setPresenter(listNotePresenter);
    }

    @Override
    public void addNewNote(Bundle argument) {
        addEditNoteFragment = (AddEditNoteFragment) getSupportFragmentManager().findFragmentByTag(AddEditNoteFragment.TAG);
        if (addEditNoteFragment == null) {
            if(argument == null)
                addEditNoteFragment = AddEditNoteFragment.newInstance(null);
            else
                addEditNoteFragment = AddEditNoteFragment.newInstance(argument);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.addToBackStack(AddEditNoteFragment.TAG);
            transaction.replace(android.R.id.content, addEditNoteFragment, AddEditNoteFragment.TAG).commit();
        }
        addEditNotePresenter = new AddEditNotePresenter(addEditNoteFragment);
        addEditNoteFragment.setPresenter(addEditNotePresenter);
    }

    @Override
    public void loadList() {
        getSupportFragmentManager().popBackStack();
    }
}
