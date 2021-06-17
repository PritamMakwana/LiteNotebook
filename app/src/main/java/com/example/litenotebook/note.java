package com.example.litenotebook;

public class note {
    private String Title;
    private String Decript;


    public note(String title, String decript) {
        Title = title;
        Decript = decript;
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
