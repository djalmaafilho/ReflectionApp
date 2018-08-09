package com.example.djalma.reflexionapp.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.djalma.reflexionapp.InjectedExample;
import com.example.djalma.reflexionapp.Person;
import com.example.djalma.reflexionapp.PersonGroupFactory;
import com.example.djalma.reflexionapp.PojoExample;
import com.example.djalma.reflexionapp.R;
import com.example.djalma.reflexionapp.ReflectionInjector;
import com.google.gson.Gson;

/**
 * This activity is initialized using reflextion to
 * inject instance attributes.
 * in you want see View how to inject views @{@link ReflexionBaseActivity}
 */
public class ReflexionInjectorActivity extends AppCompatActivity {

    private InjectedExample injectedExample;

    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_injector_example);
        ReflectionInjector.injectRecursive(this);

        TextView textView = findViewById(R.id.textView);

        person.name = "Name";
        person.age = 10;

        String personAsJson = new Gson().toJson(person);
        String injectedExampleAsString = new Gson().toJson(injectedExample);

        textView.setText(String.format("Person: %s \n\n\nInjected: %s", personAsJson, injectedExampleAsString));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
