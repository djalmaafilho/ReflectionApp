package com.example.djalma.reflexionapp;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * Created by dpassos.
 */
public class ReflectionInjector{

    interface Factory<T>{
        T build();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(value=TYPE)
    @interface ReflexionInjectClass{}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(value=FIELD)
    public @interface ReflexionInjectParams{
        boolean avoid() default false;
        Class<? extends Factory> factory();
    }

    public static void injectRecursive(final Object instance) {
        for(Field field : instance.getClass().getDeclaredFields()) {
            try {
                if(field.getType().isPrimitive()) {
                    continue;
                }
                field.setAccessible(true);
                Object value = null;
                if(field.isAnnotationPresent(ReflexionInjectParams.class)) {
                    final ReflexionInjectParams anotedParam = field.getAnnotation(ReflexionInjectParams.class);
                    if(anotedParam.avoid()) continue;
                    if(anotedParam.factory()!= null) {
                        value = anotedParam.factory().newInstance().build();
                    }
                }
                if(value == null){
                    value = field.getType().newInstance();
                }
                field.setAccessible(true);
                field.set(instance, value);
                if(value.getClass().isAnnotationPresent(ReflexionInjectClass.class)){
                    injectRecursive(value);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}