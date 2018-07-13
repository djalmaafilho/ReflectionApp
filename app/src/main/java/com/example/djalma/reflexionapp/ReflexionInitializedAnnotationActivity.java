package com.example.djalma.reflexionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * This activity is initialized using reflextion to
 * inject views on instance attributes.
 * The attributes are initialized finding xml node defineds in content view
 * by name. {@link ReflexionBaseActivity} defines the attribute search and initialization
 * Annotations are used to permits define the resource names. If you don't want use annotations
 * use @{@link ReflexionInitializedActivity} example
 */
public class ReflexionInitializedAnnotationActivity extends ReflexionBaseActivity {

    @ReflexionInject(resourceId = R.id.fab_custom_id)
    FloatingActionButton fab;

    @ReflexionInject(resourceId = R.id.toolbar_custom_id)
    public Toolbar toolbar;

    @ReflexionInject(resourceId = R.id.button_custom_id, onClickName = "buttonClick")
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialized_with_annotation_reflexion);
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void buttonClick(View v) {
        startActivity(new Intent(ReflexionInitializedAnnotationActivity.this, ReflexionInitializedActivity.class));
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
