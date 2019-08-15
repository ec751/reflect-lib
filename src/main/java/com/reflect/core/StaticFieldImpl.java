package com.reflect.core;


import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Created by jrsen on 16-4-29.
 */
class StaticFieldImpl<T> implements StaticField<T>
{

    private final java.lang.reflect.Field field;

    StaticFieldImpl(java.lang.reflect.Field field)
    {
        this.field = field;
        AccessUtils.fixAccessible(field);
    }


    @SuppressWarnings("unchecked")
    @Override
    public T getUnsafe() throws Exception
    {
        return (T)field.get(null);
    }

    @Override
    public T get()
    {
        try
        {
            return getUnsafe();
        }
        catch(Exception ignore)
        {
            //ignore.printStackTrace();
            return null;
        }
    }

    @Override
    public void setUnsafe(T value) throws Exception
    {
        field.set(null, value);
    }

    @Override
    public void set(T value)
    {
        try
        {
            setUnsafe(value);
        }
        catch(Exception ignore)
        {
            //ignore.printStackTrace();
        }
    }

    @Override
    public int getModifiers()
    {
        return field.getModifiers();
    }

    @Override
    public boolean isEnumConstant()
    {
        return field.isEnumConstant();
    }

    @Override
    public boolean isSynthetic()
    {
        return field.isSynthetic();
    }

    @Override
    public String getName()
    {
        return field.getName();
    }

    @Override
    public Class<?> getDeclaringClass()
    {
        return field.getDeclaringClass();
    }

    @Override
    public Class<?> getType()
    {
        return field.getType();
    }

    @Override
    public Type getGenericType()
    {
        return field.getGenericType();
    }

    @Override
    public Annotation[] getDeclaredAnnotations()
    {
        return field.getDeclaredAnnotations();
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationType)
    {
        return field.getAnnotation(annotationType);
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationType)
    {
        return field.isAnnotationPresent(annotationType);
    }
}
