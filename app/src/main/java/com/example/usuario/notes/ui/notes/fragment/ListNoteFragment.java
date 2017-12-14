package com.example.usuario.notes.ui.notes.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.usuario.notes.R;
import com.example.usuario.notes.data.adapter.NoteAdapter;
import com.example.usuario.notes.data.db.model.Note;
import com.example.usuario.notes.data.db.repository.NoteRepository;
import com.example.usuario.notes.ui.notes.contract.ListNoteFragmentContract;
import com.example.usuario.notes.ui.utils.CommonDialog;

/**
 * Fragment que muestra la lista de notas
 * @author Enrique Casielles
 */
public class ListNoteFragment extends ListFragment implements ListNoteFragmentContract.View {

    public static final String TAG = "ListNoteFragment";
    Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    NoteAdapter adapter;
    ListNoteFragmentContract.Presenter presenter;
    OnAddNoteListener callback;
    private ViewGroup parent;

    //INTERFAZ COMUNICACION CON ACTIVITY
    public interface OnAddNoteListener {
        void addNewNote(Bundle bundle);
    }

    //CONSTRUCTORES
    public static ListNoteFragment newInstance(Bundle arguments) {
        ListNoteFragment listNoteFragment = new ListNoteFragment();
        if(arguments != null)
            listNoteFragment.setArguments(arguments);
        return listNoteFragment;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (OnAddNoteListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity + " must implement OnAddNoteListener interface");
        }
    }

    //INICIO
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_note, container, false);
        toolbar = rootView.findViewById(R.id.tlbList);
        floatingActionButton = rootView.findViewById(R.id.fabList);
        adapter = new NoteAdapter(getActivity());
        parent = (ViewGroup) rootView;
        return rootView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerForContextMenu(view);
        getListView().setAdapter(adapter);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.addNewNote(null);
            }
        });

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Parcelable parcel = (Parcelable) adapterView.getItemAtPosition(i);
                Bundle bundle = new Bundle();
                bundle.putParcelable(Note.TAG, parcel);
                return false;
            }
        });
    }

    //MENU SUPERIOR
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_toolbar_list_fragment, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort_by_id:
                break;
            case R.id.action_sort_by_title:
                break;
            case R.id.action_show_hide_inactive:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //MENU CONTEXTUAL
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = new MenuInflater(getContext());
        inflater.inflate(R.menu.menu_context_list_fragment, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete_item:
                AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Note note = NoteRepository.getInstance().get(adapterContextMenuInfo.position);
                Bundle bundle = new Bundle();
                bundle.putString(CommonDialog.TITLE, getResources().getString(R.string.menu_context_delete_item_title));
                bundle.putString(CommonDialog.CONTENT, getResources().getString(R.string.menu_context_delete_item_content) + ": " + note.getTitle());
                bundle.putParcelable(Note.TAG, note);
                Dialog dialog = CommonDialog.dialogDeleteItem(bundle, getContext(), presenter);
                dialog.show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void showOnDeleteMessage() {
        Snackbar.make(parent, getResources().getString(R.string.note_deleted), Snackbar.LENGTH_SHORT);
    }

    //GUARDAR EL ESTADO
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    //COMUNICACION CON PRESENTER
    @Override
    public void setPresenter(ListNoteFragmentContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onListLoaded() {
        adapter.clear();
        adapter = new NoteAdapter(getContext());
    }

    //CICLO DE VIDA
    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter = null;
        presenter.onDestroy();
    }
}
