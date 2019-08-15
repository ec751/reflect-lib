package com.reflect.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * Created by jrsen on 16-4-29.
 */
final class StaticMethodImpl<T> implements StaticMethod<T>
{

    private final java.lang.reflect.Method method;

    StaticMethodImpl(java.lang.reflect.Method method)
    {
        this.method = method;
        AccessUtils.fixAccessible(method);
    }


    @SuppressWarnings("unchecked")
    @Override
    public T invokeUnsafe(Object... args) throws Exception
    {
        return (T)method.invoke(null, args);
    }

    @Override
    public T invoke(Object... args)
    {
        try
        {
            return invokeUnsafe(args);
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
        return method.getAnnotations();
    }

    @Override
    public int getModifiers()
    {
        return method.getModifiers();
    }

    @Override
    public boolean isVarArgs()
    {
        return method.isVarArgs();
    }

    @Override
    public boolean isBridge()
    {
        return method.isBridge();
    }

    @Override
    public boolean isSynthetic()
    {
        return method.isSynthetic();
    }

    @Override
    public String getName()
    {
        return method.getName();
    }

    @Override
    public Class<?> getDeclaringClass()
    {
        return method.getDeclaringClass();
    }

    @Override
    public Class<?>[] getExceptionTypes()
    {
        return method.getExceptionTypes();
    }

    @Override
    public Class<?>[] getParameterTypes()
    {
        return method.getParameterTypes();
    }

    @Override
    public Class<?> getReturnType()
    {
        return method.getReturnType();
    }

    @Override
    public String toGenericString()
    {
        return method.toGenericString();
    }

    @Override
    public TypeVariable<Method>[] getTypeParameters()
    {
        return method.getTypeParameters();
    }

    @Override
    public Type[] getGenericParameterTypes()
    {
        return method.getGenericParameterTypes();
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationType)
    {
        return method.isAnnotationPresent(annotationType);
    }

    @Override
    public Type[] getGenericExceptionTypes()
    {
        return method.getGenericExceptionTypes();
    }

    @Override
    public Type getGenericReturnType()
    {
        return method.getGenericReturnType();
    }

    @Override
    public Annotation[] getDeclaredAnnotations()
    {
        return method.getDeclaredAnnotations();
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationType)
    {
        return method.getAnnotation(annotationType);
    }

    @Override
    public Annotation[][] getParameterAnnotations()
    {
        return method.getParameterAnnotations();
    }

    @Override
    public Object getDefaultValue()
    {
        return method.getDefaultValue();
    }
}
