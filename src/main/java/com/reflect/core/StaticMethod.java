package com.reflect.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public interface StaticMethod<T>
{
    T invokeUnsafe(Object... args) throws Exception;

    T invoke(Object... args);

    Annotation[] getAnnotations();

    int getModifiers();

    boolean isVarArgs();

    boolean isBridge();

    boolean isSynthetic();

    String getName();

    Class<?> getDeclaringClass();

    Class<?>[] getExceptionTypes();

    Class<?>[] getParameterTypes();

    Class<?> getReturnType();

    String toGenericString();

    TypeVariable<Method>[] getTypeParameters();

    Type[] getGenericParameterTypes();

    boolean isAnnotationPresent(Class<? extends Annotation> annotationType);

    Type[] getGenericExceptionTypes();

    Type getGenericReturnType();

    Annotation[] getDeclaredAnnotations();

    <A extends Annotation> A getAnnotation(Class<A> annotationType);

    Annotation[][] getParameterAnnotations();

    Object getDefaultValue();
}
