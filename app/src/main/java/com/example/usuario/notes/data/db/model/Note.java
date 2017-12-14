package com.example.usuario.notes.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Clase modelo para los objetos Nota
 * @author Enrique Casielles
 */
public class Note implements Parcelable {

    public static final String TAG = "Note";
    String title, description;
    boolean active;

    public static Comparator<Note> TITLE_COMPARATOR = new Comparator<Note>() {
        @Override
        public int compare(Note note, Note t1) {
            return note.getTitle().compareToIgnoreCase(t1.getTitle());
        }
    };



    public Note(String title, String description, boolean active) {
        this.title = title;
        this.description = description;
        this.active = active;
    }

    protected Note(Parcel in) {
        title = in.readString();
        description = in.readString();
        active = in.readByte() != 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeByte((byte) (active ? 1 : 0));
    }
}
