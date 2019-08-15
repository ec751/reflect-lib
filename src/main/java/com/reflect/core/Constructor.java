package com.reflect.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public interface Constructor<T>
{

    T newInstanceUnSafe(Object... args) throws Exception;

    T newInstance(Object... args);

    Annotation[] getAnnotations();

    int getModifiers();

    boolean isVarArgs();

    boolean isSynthetic();

    String getName();

    Class getDeclaringClass();

    Class<?>[] getExceptionTypes();

    Class<?>[] getParameterTypes();

    TypeVariable<java.lang.reflect.Constructor>[] getTypeParameters();

    String toGenericString();

    Type[] getGenericParameterTypes();

    Type[] getGenericExceptionTypes();

    Annotation[] getDeclaredAnnotations();

    Annotation[][] getParameterAnnotations();

    boolean isAccessible();

    boolean isAnnotationPresent(Class<? extends Annotation> annotationType);

    <A extends Annotation> A getAnnotation(Class<A> annotationType);
}
