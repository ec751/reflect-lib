package com.reflect.core;

import java.lang.reflect.Method;

public final class ObjectWrapper
{

    private Field<Object> mField;
    private Object mObject;

    ObjectWrapper(Object object)
    {
        mObject = object;
    }

    public ObjectWrapper getField(String name)
    {
        try
        {
            if(mField != null) mObject = mField.getUnsafe(mObject);
            for(Class<?> clazz = mObject.getClass(); clazz != null; clazz = clazz.getSuperclass())
            {
                try
                {
                    mField = new Field<>(clazz.getDeclaredField(name));
                    return this;
                }
                catch(NoSuchFieldException ignore)
                {
//                    ignore.printStackTrace();
                }
            }
            throw new NoSuchFieldException();
        }
        catch(Throwable t)
        {
            throw new RuntimeException(t);
        }
    }

    public Object getObject()
    {
        return mField == null ? mObject : mField.get(mObject);
    }

    public void setObject(Object obj)
    {
        if(mField == null)
        {
            throw new UnsupportedOperationException("not found field to set obj!");
        }
        else
        {
            mField.set(this.mObject, obj);
        }
    }

    public ObjectWrapper callMethod(String name, Object... args)
    {
        try
        {
            Class<?> clazz = mField == null ? mObject.getClass() : mField.getType();
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for(int i = 0; i < declaredMethods.length; i++)
            {
                Method declaredMethod = declaredMethods[i];
                if(declaredMethod.getName().equals(name))
                {
                    Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
                    if(args.length == parameterTypes.length)
                    {
                        for(int j = 0; j < parameterTypes.length; j++)
                        {
                            if(args[j] != null && !parameterTypes[j].isAssignableFrom(args[j].getClass()))
                            {
                                break;
                            }
                        }
                        // find method
                        mObject = declaredMethod.invoke(getObject(), args);
                        mField = null;
                    }
                }
            }
            return this;
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
