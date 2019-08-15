package com.reflect.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

final class AccessUtils
{

    static void fixAccessible(Constructor constructor)
    {
        try
        {
            constructor.setAccessible(true);
            Field accessFlagsField = Constructor.class.getSuperclass().getDeclaredField("accessFlags");
            accessFlagsField.setAccessible(true);
            accessFlagsField.set(constructor, constructor.getModifiers() | Modifier.PUBLIC & ~Modifier.FINAL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    static void fixAccessible(Field field)
    {
        try
        {
            field.setAccessible(true);
            java.lang.reflect.Field accessFlagsField = Field.class.getDeclaredField("accessFlags");
            accessFlagsField.setAccessible(true);
            accessFlagsField.set(field, field.getModifiers() | Modifier.PUBLIC & ~Modifier.FINAL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    static void fixAccessible(Method method)
    {
        try
        {
            method.setAccessible(true);
            Field accessFlagsField = Method.class.getSuperclass().getDeclaredField("accessFlags");
            accessFlagsField.setAccessible(true);
            accessFlagsField.set(method, method.getModifiers() | Modifier.PUBLIC & ~Modifier.FINAL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
