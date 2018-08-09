package com.example.djalma.reflexionapp;

import java.util.ArrayList;

/**
 * Created by dpassos.
 */
@ReflectionInjector.ReflexionInjectClass
public class PersonGroup {

    private ArrayList<Person> annotedPersonList;

    private ArrayList<Person> defaultPersonList;

    private Person person;


    public ArrayList<Person> getAnnotedPersonList() {
        return annotedPersonList;
    }

    public ArrayList<Person> getDefaultPersonList() {
        return defaultPersonList;
    }

    public Person getPerson() {
        return person;
    }
}
