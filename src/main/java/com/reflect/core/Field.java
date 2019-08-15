package com.reflect.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public interface Field<T>
{
    T getUnsafe(Object obj) throws Exception;

    T get(Object obj);

    void setUnsafe(Object obj, T value) throws Exception;

    void set(Object obj, T value);

    int getModifiers();

    boolean isEnumConstant();

    boolean isSynthetic();

    String getName();

    Class<?> getDeclaringClass();

    Class<?> getType();

    Type getGenericType();

    Annotation[] getDeclaredAnnotations();

    <A extends Annotation> A getAnnotation(Class<A> annotationType);

    boolean isAnnotationPresent(Class<? extends Annotation> annotationType);
}
