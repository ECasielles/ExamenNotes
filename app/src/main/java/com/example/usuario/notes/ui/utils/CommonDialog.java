package com.example.usuario.notes.ui.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.usuario.notes.R;
import com.example.usuario.notes.data.db.model.Note;
import com.example.usuario.notes.ui.notes.contract.ListNoteFragmentContract;

/**
 * Clase que guarda cuadros de diálogo genéricos para las vistas.
 * @author Enrique Casielles
 */
public class CommonDialog {

    public static final String TITLE = "TITLE";
    public static final String CONTENT = "CONTENT";

    public static Dialog dialogDeleteItem(final Bundle bundle, Context context, final ListNoteFragmentContract.Presenter presenter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(bundle.getString(TITLE)).
                setMessage(bundle.getString(CONTENT)).
                setPositiveButton(context.getResources().getString(R.string.menu_context_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        presenter.deleteNote((Note) bundle.getParcelable(Note.TAG));
                    }
                }).
                setNegativeButton(context.getResources().getString(R.string.menu_context_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        return builder.create();
    }

}
