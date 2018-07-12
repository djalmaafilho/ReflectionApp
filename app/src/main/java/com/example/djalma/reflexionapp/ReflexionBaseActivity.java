package com.example.djalma.reflexionapp;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import static java.lang.annotation.ElementType.FIELD;

abstract public class ReflexionBaseActivity extends AppCompatActivity {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(value=FIELD)
    public @interface ReflexionInject{
        int resourceId();
    }

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
                    int id = geResorcetId(field);
                    View v = findViewById(id);
                    field.set(this, v);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    int geResorcetId(Field field) throws NoSuchFieldException, IllegalAccessException{
        ReflexionInject inject = field.getAnnotation(ReflexionInject.class);
        if(inject!= null){
            return inject.resourceId();
        }else{
            return R.id.class.getDeclaredField(field.getName()).getInt(this);
        }
    }
}