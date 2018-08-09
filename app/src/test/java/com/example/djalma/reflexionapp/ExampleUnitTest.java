package com.example.djalma.reflexionapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void testReflectionInjection() {

        InjectedExample injectedExample = new InjectedExample();
        ReflectionInjector.injectRecursive(injectedExample);
        assertNotNull(injectedExample);
        assertNotNull(injectedExample.personGroup);
        assertNotNull(injectedExample.personGroup.getAnnotedPersonList());
        assertNotNull(injectedExample.personGroup.getDefaultPersonList());
        assertNotNull(injectedExample.personGroup.getPerson());
        assertNotNull(injectedExample.personGroup.getPerson().name);
    }
}