package com.reflect.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * Created by jrsen on 16-4-29.
 */
public final class Constructor<T> {

    private final java.lang.reflect.Constructor constructor;

    public Constructor(java.lang.reflect.Constructor constructor) {
        this.constructor = constructor;
    }

    @SuppressWarnings("unchecked")
    public T newInstanceUnSafe(Object... args) throws Exception {
        if (!constructor.isAccessible())
            constructor.setAccessible(true);
        return (T) constructor.newInstance(args);
    }

    public T newInstance(Object... args) {
        try {
            return newInstanceUnSafe(args);
        } catch (Throwable ignore) {
            return null;
        }
    }

    public Annotation[] getAnnotations()
    {
        return constructor.getAnnotations();
    }

    public int getModifiers()
    {
        return constructor.getModifiers();
    }

    public boolean isVarArgs()
    {
        return constructor.isVarArgs();
    }

    public boolean isSynthetic()
    {
        return constructor.isSynthetic();
    }

    public String getName()
    {
        return constructor.getName();
    }

    public Class getDeclaringClass()
    {
        return constructor.getDeclaringClass();
    }

    public Class<?>[] getExceptionTypes()
    {
        return constructor.getExceptionTypes();
    }

    public Class<?>[] getParameterTypes()
    {
        return constructor.getParameterTypes();
    }

    public TypeVariable<java.lang.reflect.Constructor>[] getTypeParameters()
    {
        return constructor.getTypeParameters();
    }

    public String toGenericString()
    {
        return constructor.toGenericString();
    }

    public Type[] getGenericParameterTypes()
    {
        return constructor.getGenericParameterTypes();
    }

    public Type[] getGenericExceptionTypes()
    {
        return constructor.getGenericExceptionTypes();
    }

    public Annotation[] getDeclaredAnnotations()
    {
        return constructor.getDeclaredAnnotations();
    }

    public Annotation[][] getParameterAnnotations()
    {
        return constructor.getParameterAnnotations();
    }

    public boolean isAccessible()
    {
        return constructor.isAccessible();
    }

    public boolean isAnnotationPresent(Class<? extends Annotation> annotationType)
    {
        return constructor.isAnnotationPresent(annotationType);
    }

    public <T extends Annotation> T getAnnotation(Class<T> annotationType)
    {
        return (T)constructor.getAnnotation(annotationType);
    }
}
