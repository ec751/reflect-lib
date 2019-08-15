package com.reflect.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public final class NullImpl implements InvocationHandler
{

    private final String detailMessage;

    private NullImpl(String detailMessage)
    {
        this.detailMessage = detailMessage;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
    {
        throw new RuntimeException(detailMessage);
    }

    @SuppressWarnings("unchecked")
    public static <T> Constructor<T> newConstructorNullImpl(String detailMessage)
    {
        return (Constructor<T>)Proxy.newProxyInstance(Constructor.class.getClassLoader(), new Class[]{Constructor.class}, new NullImpl(detailMessage));
    }

    @SuppressWarnings("unchecked")
    public static <T> Field<T> newFieldNullImpl(String detailMessage)
    {
        return (Field<T>)Proxy.newProxyInstance(Field.class.getClassLoader(), new Class[]{Field.class}, new NullImpl(detailMessage));
    }

    @SuppressWarnings("unchecked")
    public static <T> StaticField<T> newStaticFieldNullImpl(String detailMessage)
    {
        return (StaticField<T>)Proxy.newProxyInstance(StaticField.class.getClassLoader(), new Class[]{StaticField.class}, new NullImpl(detailMessage));
    }

    @SuppressWarnings("unchecked")
    public static <T> com.reflect.core.Method<T> newMethodNullImpl(String detailMessage)
    {
        return (com.reflect.core.Method<T>)Proxy.newProxyInstance(com.reflect.core.Method.class.getClassLoader(), new Class[]{com.reflect.core.Method.class}, new NullImpl(detailMessage));
    }

    @SuppressWarnings("unchecked")
    public static <T> StaticMethod<T> newStaticMethodNullImpl(String detailMessage)
    {
        return (StaticMethod<T>)Proxy.newProxyInstance(StaticMethod.class.getClassLoader(), new Class[]{StaticMethod.class}, new NullImpl(detailMessage));
    }
}
