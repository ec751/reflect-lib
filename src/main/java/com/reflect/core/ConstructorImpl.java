package com.reflect.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * Created by jrsen on 16-4-29.
 */
final class ConstructorImpl<T> implements Constructor<T>
{

    private final java.lang.reflect.Constructor constructor;

    ConstructorImpl(java.lang.reflect.Constructor constructor)
    {
        this.constructor = constructor;
        AccessUtils.fixAccessible(constructor);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T newInstanceUnSafe(Object... args) throws Exception
    {
        return (T)constructor.newInstance(args);
    }

    @Override
    public T newInstance(Object... args)
    {
        try
        {
            return newInstanceUnSafe();
        }
        catch(Exception ignore)
        {
            //ignore.printStackTrace();
            return null;
        }
    }

    @Override
    public Annotation[] getAnnotations()
    {
        return constructor.getAnnotations();
    }

    @Override
    public int getModifiers()
    {
        return constructor.getModifiers();
    }

    @Override
    public boolean isVarArgs()
    {
        return constructor.isVarArgs();
    }

    @Override
    public boolean isSynthetic()
    {
        return constructor.isSynthetic();
    }

    @Override
    public String getName()
    {
        return constructor.getName();
    }

    @Override
    public Class getDeclaringClass()
    {
        return constructor.getDeclaringClass();
    }

    @Override
    public Class<?>[] getExceptionTypes()
    {
        return constructor.getExceptionTypes();
    }

    @Override
    public Class<?>[] getParameterTypes()
    {
        return constructor.getParameterTypes();
    }

    @Override
    public TypeVariable<java.lang.reflect.Constructor>[] getTypeParameters()
    {
        return constructor.getTypeParameters();
    }

    @Override
    public String toGenericString()
    {
        return constructor.toGenericString();
    }

    @Override
    public Type[] getGenericParameterTypes()
    {
        return constructor.getGenericParameterTypes();
    }

    @Override
    public Type[] getGenericExceptionTypes()
    {
        return constructor.getGenericExceptionTypes();
    }

    @Override
    public Annotation[] getDeclaredAnnotations()
    {
        return constructor.getDeclaredAnnotations();
    }

    @Override
    public Annotation[][] getParameterAnnotations()
    {
        return constructor.getParameterAnnotations();
    }

    @Override
    public boolean isAccessible()
    {
        return constructor.isAccessible();
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationType)
    {
        return constructor.isAnnotationPresent(annotationType);
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationType)
    {
        return (A)constructor.getAnnotation(annotationType);
    }
}
