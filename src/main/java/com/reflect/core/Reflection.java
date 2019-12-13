package com.reflect.core;

import com.reflect.annotation.Parameter;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Modifier;

/**
 * Created by jrsen on 16-4-29.
 */
public final class Reflection {

    @SuppressWarnings("unchecked")
    public static <T> Class<T> init(ClassLoader classLoader, String srcClass, Class<?> injectClass) {
        try {
            return (Class<T>) init(classLoader, classLoader.loadClass(srcClass), injectClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> init(String srcClass, Class<?> injectClass) {
        try {
            return (Class<T>) init(ClassLoader.getSystemClassLoader(), Class.forName(srcClass), injectClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> Class<T> init(ClassLoader classLoader, Class<T> srcClass, Class<?> injectClass) {
        java.lang.reflect.Field[] injectFields = injectClass.getDeclaredFields();
        for (java.lang.reflect.Field injectField : injectFields) {
            if (!Modifier.isStatic(injectField.getModifiers()))
                continue;
            Class<?> classType = injectField.getType();
            if (classType == Constructor.class) {
                linkToConstructor(classLoader, srcClass, injectField);
            } else if (classType == Method.class) {
                linkToMethod(classLoader, srcClass, injectField);
            } else if (classType == StaticMethod.class) {
                linkToStaticMethod(classLoader, srcClass, injectField);
            } else if (classType == Field.class) {
                linkToField(srcClass, injectField);
            } else if (classType == StaticField.class) {
                linkToStaticField(srcClass, injectField);
            }
        }
        return srcClass;
    }

    private static void linkToConstructor(ClassLoader classLoader, Class<?> clazz, java.lang.reflect.Field injectField) {
        try {
            Constructor<?> constructor = getConstructor(clazz, getParameterTypes(classLoader, injectField));
            injectField.setAccessible(true);
            injectField.set(null, constructor);
        } catch (Throwable ignore) {
        }
    }

    private static void linkToMethod(ClassLoader classLoader, Class<?> clazz, java.lang.reflect.Field injectField) {
        try {
            String name = injectField.isAnnotationPresent(Parameter.class) && !injectField.getAnnotation(Parameter.class).name().isEmpty()
                ? injectField.getAnnotation(Parameter.class).name() : injectField.getName();
            Method<?> method = getMethod(clazz, name, getParameterTypes(classLoader, injectField));
            injectField.setAccessible(true);
            injectField.set(null, method);
        } catch (Throwable ignore) {
        }
    }

    private static void linkToStaticMethod(ClassLoader classLoader, Class<?> clazz, java.lang.reflect.Field injectField) {
        try {
            String name = injectField.isAnnotationPresent(Parameter.class) && !injectField.getAnnotation(Parameter.class).name().isEmpty()
                ? injectField.getAnnotation(Parameter.class).name() : injectField.getName();
            StaticMethod<?> staticMethod = getStaticMethod(clazz, name, getParameterTypes(classLoader, injectField));
            injectField.setAccessible(true);
            injectField.set(null, staticMethod);
        } catch (Throwable ignore) {
        }
    }

    private static void linkToField(Class<?> clazz, java.lang.reflect.Field injectField) {
        try {
            String name = injectField.isAnnotationPresent(Parameter.class) && !injectField.getAnnotation(Parameter.class).name().isEmpty()
                ? injectField.getAnnotation(Parameter.class).name() : injectField.getName();
            Field<?> field = getField(clazz, name);
            injectField.setAccessible(true);
            injectField.set(null, field);
        } catch (Throwable ignore) {
        }
    }

    private static void linkToStaticField(Class<?> clazz, java.lang.reflect.Field injectField) {
        try {
            String name = injectField.isAnnotationPresent(Parameter.class) && !injectField.getAnnotation(Parameter.class).name().isEmpty()
                ? injectField.getAnnotation(Parameter.class).name() : injectField.getName();
            StaticField<?> staticField = getStaticField(clazz, name);
            injectField.setAccessible(true);
            injectField.set(null, staticField);
        } catch (Throwable ignore) {
        }
    }

    private static Class<?>[] getParameterTypes(ClassLoader classLoader, AccessibleObject object) {
        if (object.isAnnotationPresent(Parameter.class)) {
            Parameter param = object.getAnnotation(Parameter.class);
            if (param.value().length > 0) {
                return param.value().clone();
            }

            Class[] clsParams =
                    {
                            param.c_arg0(), param.c_arg1(),
                            param.c_arg2(), param.c_arg3(),
                            param.c_arg4(), param.c_arg5(),
                            param.c_arg6(), param.c_arg7(),
                            param.c_arg8(), param.c_arg9(),
                            param.c_arg10(), param.c_arg11(),
                            param.c_arg12(), param.c_arg13(),
                            param.c_arg14(), param.c_arg15(),
                            param.c_arg16(), param.c_arg17(),
                            param.c_arg18(), param.c_arg19()
                    };
            String[] strParams =
                    {
                            param.s_arg0(), param.s_arg1(),
                            param.s_arg2(), param.s_arg3(),
                            param.s_arg4(), param.s_arg5(),
                            param.s_arg6(), param.s_arg7(),
                            param.s_arg8(), param.s_arg9(),
                            param.s_arg10(), param.s_arg11(),
                            param.s_arg12(), param.s_arg13(),
                            param.s_arg14(), param.s_arg15(),
                            param.s_arg16(), param.s_arg17(),
                            param.s_arg18(), param.s_arg19()
                    };
            int params_length = 0;
            final int N = strParams.length;
            for (int i = 0; i < N; i++) {
                if (!strParams[i].isEmpty()) {
                    try {
                        clsParams[i] = classLoader.loadClass(strParams[i]);
                    } catch (ClassNotFoundException ignore) {
                    }
                } else if (clsParams[i] == Void.class) {
                    params_length = i;
                    break;
                }
            }
            Class[] parameterTypes = new Class[params_length];
            System.arraycopy(clsParams, 0, parameterTypes, 0, params_length);
            return parameterTypes;
        }
        return new Class[0];
    }

    public static <T> Constructor<T> getConstructor(String srcClass, Class<?>... parameterTypes) {
        try {
            return getConstructor(Class.forName(srcClass), parameterTypes);
        } catch (ClassNotFoundException e) {
            return NullImpl.newConstructorNullImpl(String.format("can't found constructor in %s", srcClass));
        }
    }

    public static <T> Constructor<T> getConstructor(Class srcClass, Class<?>... parameterTypes) {
        try {
            java.lang.reflect.Constructor constructor = srcClass.getDeclaredConstructor(parameterTypes);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return new ConstructorImpl<>(constructor);
        } catch (NoSuchMethodException e) {
            return NullImpl.newConstructorNullImpl(String.format("can't found constructor in %s", srcClass.getName()));
        }
    }

    public static <T> Field<T> getField(String srcClass, String fieldName) {
        try {
            return getField(Class.forName(srcClass), fieldName);
        } catch (ClassNotFoundException e) {
            return NullImpl.newFieldNullImpl(String.format("%s not found in %s", fieldName, srcClass));
        }
    }

    public static <T> Field<T> getField(Class<?> srcClass, String fieldName) {
        try {
            java.lang.reflect.Field field = srcClass.getDeclaredField(fieldName);
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            return new FieldImpl<>(field);
        } catch (NoSuchFieldException e) {
            return NullImpl.newFieldNullImpl(String.format("%s not found in %s", fieldName, srcClass.getName()));
        }
    }

    public static <T> StaticField<T> getStaticField(String srcClass, String fieldName) {
        try {
            return getStaticField(Class.forName(srcClass), fieldName);
        } catch (ClassNotFoundException e) {
            return NullImpl.newStaticFieldNullImpl(String.format("%s not found in %s", fieldName, srcClass));
        }
    }

    public static <T> StaticField<T> getStaticField(Class<?> srcClass, String fieldName) {
        try {
            java.lang.reflect.Field field = srcClass.getDeclaredField(fieldName);
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            return new StaticFieldImpl<>(field);
        } catch (NoSuchFieldException e) {
            return NullImpl.newStaticFieldNullImpl(String.format("%s not found in %s", fieldName, srcClass.getName()));
        }
    }

    public static <T> Method<T> getMethod(String srcClass, String methodName, Class<?>... parameterTypes) {
        try {
            return getMethod(Class.forName(srcClass), methodName, parameterTypes);
        } catch (ClassNotFoundException e) {
            return NullImpl.newMethodNullImpl(String.format("%s not found in %s", methodName, srcClass));
        }
    }

    public static <T> Method<T> getMethod(Class<?> srcClass, String methodName, Class<?>... parameterTypes) {
        try {
            java.lang.reflect.Method method = srcClass.getDeclaredMethod(methodName, parameterTypes);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            return new MethodImpl<>(method);
        } catch (NoSuchMethodException e) {
            return NullImpl.newMethodNullImpl(String.format("%s not found in %s", methodName, srcClass.getName()));
        }
    }

    public static <T> StaticMethod<T> getStaticMethod(String srcClass, String methodName, Class<?>... parameterTypes) {
        try {
            return getStaticMethod(Class.forName(srcClass), methodName, parameterTypes);
        } catch (ClassNotFoundException e) {
            return NullImpl.newStaticMethodNullImpl(String.format("%s not found in %s", methodName, srcClass));
        }
    }

    public static <T> StaticMethod<T> getStaticMethod(Class<?> srcClass, String methodName, Class<?>... parameterTypes) {
        try {
            java.lang.reflect.Method method = srcClass.getDeclaredMethod(methodName, parameterTypes);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            return new StaticMethodImpl<>(method);
        } catch (NoSuchMethodException e) {
            return NullImpl.newStaticMethodNullImpl(String.format("%s not found in %s", methodName, srcClass.getName()));
        }
    }

    public static ObjectWrapper streamGetObject(Object obj) {
        return new ObjectWrapper(obj);
    }

}
