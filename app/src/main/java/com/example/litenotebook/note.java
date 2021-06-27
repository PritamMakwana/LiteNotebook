package com.example.litenotebook;

public class note {
    private String _id;
    private String Title;
    private String Decript;

    public note(String _id, String title, String decript) {
        this._id = _id;
        Title = title;
        Decript = decript;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDecript() {
        return Decript;
    }

    public void setDecript(String decript) {
        Decript = decript;
    }
}
