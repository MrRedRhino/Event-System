package org.pipeman.events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RegisteredEventHandler {
    Method method;
    Object obj;

    public RegisteredEventHandler(Class<?> clazz, Method method) {
        try {
            this.obj = clazz.getDeclaredConstructor().newInstance();
            this.method = method;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void invokeMethod(Event event) throws InvocationTargetException, IllegalAccessException {
        method.invoke(this.obj, event);
    }
}
