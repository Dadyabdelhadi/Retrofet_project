package com.example.projectseval;

public class Project {
    private String id;
    private String title;
    int noteJ1;
    int noteJ2;
    int noteJ3;
    int noteJ4;
    double total;

    //---------------------------------

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNoteJ1() {
        return noteJ1;
    }

    public void setNoteJ1(int noteJ1) {
        this.noteJ1 = noteJ1;
    }

    public int getNoteJ2() {
        return noteJ2;
    }

    public void setNoteJ2(int noteJ2) {
        this.noteJ2 = noteJ2;
    }

    public int getNoteJ3() {
        return noteJ3;
    }

    public void setNoteJ3(int noteJ3) {
        this.noteJ3 = noteJ3;
    }

    public int getNoteJ4() {
        return noteJ4;
    }

    public void setNoteJ4(int noteJ4) {
        this.noteJ4 = noteJ4;
    }

    public double getTotal() {
        double t=0;
        if(noteJ1!=-1)t+=noteJ1;
        if(noteJ2!=-1)t+=noteJ2;
        if(noteJ3!=-1)t+=noteJ3;
        if(noteJ4!=-1)t+=noteJ4;
        total=t;
        return total;
    }




}
