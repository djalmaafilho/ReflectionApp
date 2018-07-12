package com.example.djalma.reflexionapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * This activity is initialized using reflextion to
 * inject views on instance attributes.
 * The attributes are initialized finding xml node defineds in content view
 * by name. {@link ReflexionBaseActivity} defines the attribute search and initialization
 */
public class ReflexionInitializedActivity extends ReflexionBaseActivity {

    FloatingActionButton fab;
    Toolbar toolbar;
    PojoExample pojo;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialized_with_only_reflexion);
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        inject();
        pojo.pojoName = "Pojo example message";
        message.setText(pojo.pojoName);
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
