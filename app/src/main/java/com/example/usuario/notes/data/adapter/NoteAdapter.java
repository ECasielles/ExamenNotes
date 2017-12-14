package com.example.usuario.notes.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.usuario.notes.R;
import com.example.usuario.notes.data.db.model.Note;
import com.example.usuario.notes.data.db.repository.NoteRepository;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Adapter de elementos de la lista de notas.
 * Los guarda como ArrayList.
 * @author Enrique Casielles
 */
public class NoteAdapter extends ArrayAdapter<Note> {

    public NoteAdapter(@NonNull Context context) {
        super(context, R.layout.item_note, new ArrayList<Note>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rootView = convertView;
        NoteHolder holder;

        if(rootView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            rootView = inflater.inflate(R.layout.item_note, null);
            holder = new NoteHolder();

            holder.txvTitle = rootView.findViewById(R.id.txvTitle);
            holder.txvDescription = rootView.findViewById(R.id.txvDescription);

            rootView.setTag(holder);
        } else
            holder = (NoteHolder) rootView.getTag();

        holder.txvTitle.setText(getItem(position).getTitle());
        holder.txvDescription.setText(getItem(position).getDescription());

        return rootView;
    }

    @Override
    public void sort(@NonNull Comparator<? super Note> comparator) {
        super.sort(comparator);
    }

    private class NoteHolder {
        TextView txvTitle, txvDescription;
    }

}
