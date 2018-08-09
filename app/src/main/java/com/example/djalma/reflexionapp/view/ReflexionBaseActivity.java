package com.example.djalma.reflexionapp.view;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.djalma.reflexionapp.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import static java.lang.annotation.ElementType.FIELD;

abstract public class ReflexionBaseActivity extends AppCompatActivity {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(value=FIELD)
    public @interface ReflexionInject{
        int resourceId();
        String onClickName() default "";
    }

    /**
     * Auto Initialize your fields
     * in this example is needed that field name equals xml attribute name
     * @param layoutResID
     */
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        injectViews();
    }

    int getResourceId(Field field) throws NoSuchFieldException, IllegalAccessException{
        ReflexionInject inject = field.getAnnotation(ReflexionInject.class);
        if(inject!= null){
            return inject.resourceId();
        }else{
            return R.id.class.getDeclaredField(field.getName()).getInt(this);
        }
    }

    void injectViews() {
        for(Field field : getClass ().getDeclaredFields()) {
            boolean isView = View.class.isAssignableFrom(field.getType());
            if(isView) {
                try {
                    int id = getResourceId(field);
                    View v = findViewById(id);
                    field.set(this, v);
                    injectOnClickListener(this, v, field);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * This method injects dynamically onclick listeners
     * @param instance
     * @param v
     * @param field
     */
    void injectOnClickListener(final Object instance, View v, final Field field) {
        final ReflexionInject inject = field.getAnnotation(ReflexionInject.class);
        if(inject!= null){
            final String methodName = inject.onClickName();
            if(methodName.trim().isEmpty())return;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Method method = instance.getClass().getDeclaredMethod(methodName, View.class);
                        method.setAccessible(true);
                        method.invoke(instance, v);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    void inject() {
        for(Field field : getClass ().getDeclaredFields()) {
            boolean isView = View.class.isAssignableFrom(field.getType());
            if(!isView) {
                try {
                    field.set(this, field.getType().newInstance());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}