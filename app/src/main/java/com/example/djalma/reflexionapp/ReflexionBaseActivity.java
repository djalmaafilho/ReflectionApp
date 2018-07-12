package com.example.djalma.reflexionapp;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.lang.reflect.Field;

public class ReflexionBaseActivity extends AppCompatActivity {

    /**
     * Auto Initialize your fields
     * in this example is needed that field name equals xml attribute name
     * @param layoutResID
     */
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        for(Field field : getClass ().getDeclaredFields()) {
            boolean isSubclass = View.class.isAssignableFrom(field.getType());
            if(isSubclass) {
                try {
                    View v = findViewById(R.id.class.getDeclaredField(field.getName()).getInt(this));
                    field.set(this, v);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}