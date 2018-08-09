package com.example.djalma.reflexionapp;

/**
 * Created by dpassos.
 */
public class InjectedExample {

    @ReflectionInjector.ReflexionInjectParams(factory = PersonGroupFactory.class)
    PersonGroup annotedPersonGroup;


    PersonGroup personGroup;

}
