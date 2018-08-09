package com.example.djalma.reflexionapp;

import java.util.ArrayList;

/**
 * Created by dpassos.
 */
public class ArrayListFactory implements ReflectionInjector.Factory<ArrayList<Person>>{
    @Override
    public ArrayList<Person> build() {
        return new ArrayList<>();
    }
}
