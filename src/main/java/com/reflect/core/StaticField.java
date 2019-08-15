package com.reflect.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public interface StaticField<T>
{
    T getUnsafe() throws Exception;

    T get();

    void setUnsafe(T value) throws Exception;

    void set(T value);

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
