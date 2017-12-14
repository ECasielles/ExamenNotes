package com.example.usuario.notes.ui.notes.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;

import com.example.usuario.notes.R;
import com.example.usuario.notes.ui.notes.contract.AddEditNoteContract;

/**
 * Created by usuario on 12/14/17.
 */

public class AddEditNoteFragment extends Fragment implements AddEditNoteContract.View {

    public static final String TAG = "AddEditNoteFragment";
    private OnAddEditFinishedListener callback;
    private AddEditNoteContract.Presenter presenter;
    private EditText edtTitle, edtDescription;
    private Switch swtActive;
    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private ViewGroup parent;


    //INTERFAZ COMUNICACION CON ACTIVITY
    public interface OnAddEditFinishedListener {
        void loadList();
    }
    //CONSTRUCTOR
    public static AddEditNoteFragment newInstance(Bundle arguments) {
        AddEditNoteFragment addEditNoteFragment = new AddEditNoteFragment();
        if(arguments != null)
            addEditNoteFragment.setArguments(arguments);
        return addEditNoteFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (OnAddEditFinishedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity + " must implement OnAddEditFinishedListener");
        }
    }
    //CICLO DE VIDA
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_edit_note, container, false);
        edtTitle = rootView.findViewById(R.id.edtTitle);
        edtDescription = rootView.findViewById(R.id.edtDescription);
        toolbar = rootView.findViewById(R.id.tlbAddEdit);
        swtActive = rootView.findViewById(R.id.swtActive);
        floatingActionButton = rootView.findViewById(R.id.fabAddEdit);
        parent = (ViewGroup) rootView;
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onAddNote(edtTitle.getText().toString(), edtDescription.getText().toString(), swtActive.isChecked());
            }
        });

    }

    //COMUNICACION CON PRESENTER
    @Override
    public void setPresenter(AddEditNoteContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setTitleEmptyError() {
        Snackbar.make(parent, getResources().getString(R.string.error_title_empty), Snackbar.LENGTH_SHORT);
    }
    @Override
    public void setAlreadyExistsError() {
        Snackbar.make(parent, getResources().getString(R.string.error_title_exists), Snackbar.LENGTH_SHORT);
    }

    @Override
    public void onSuccess() {
        callback.loadList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
