package com.example.djalma.reflexionapp;

/**
 * Created by dpassos.
 */
public class PersonGroupFactory implements ReflectionInjector.Factory<PersonGroup> {
    @Override
    public PersonGroup build() {
        return new PersonGroup();
    }
}
