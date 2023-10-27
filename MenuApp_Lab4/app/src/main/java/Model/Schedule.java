package Model;

import android.graphics.Color;

import androidx.annotation.NonNull;

public class Schedule {
    private int id;
    private String description;
    private int txtColor;
    private int bgColor;

    public Schedule(int id, String description, int txtColor, int bgColor) {
        this.id = id;
        this.description = description;
        this.txtColor = txtColor;
        this.bgColor = bgColor;
    }

    public Schedule(int id, String description) {
        this.id = id;
        this.description = description;
        this.txtColor = Color.BLACK;
        this.bgColor = Color.WHITE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTxtColor() {
        return txtColor;
    }

    public void setTxtColor(int txtColor) {
        this.txtColor = txtColor;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    @NonNull
    @Override
    public String toString() {
        return description;
    }
}
