package org.pipeman.events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListenerManager {
    private static final Map<Class<?>, List<RegisteredEventHandler>> listeners = new HashMap<>();

    public static void registerEventHandler(Listener listener) {
        Class<?> clazz = listener.getClass();
        for (Method method : clazz.getMethods()) {
            if (method.getAnnotation(EventHandler.class) != null) {
                if (method.getParameterCount() == 1) {
                    Class<?> param = method.getParameterTypes()[0];
                    if (isHighestClassEvent(param)) {
                        if (!listeners.containsKey(param)) listeners.put(param, new ArrayList<>());

                        boolean registered = false;
                        for (RegisteredEventHandler reh : listeners.get(param)) {
                            if (reh.getMethod().equals(method)) {
                                registered = true;
                            }
                        }
                        if (!registered) listeners.get(param).add(new RegisteredEventHandler(clazz, method));
                    }
                }
            }
        }
        System.out.println(listeners);
    }

    public static void fireEvent(Event event) throws InvocationTargetException, IllegalAccessException {
        List<RegisteredEventHandler> handlers = listeners.get(event.getClass());
        for (RegisteredEventHandler handler : handlers) {
            handler.invokeMethod(event);
        }
    }

    private static boolean isHighestClassEvent(Class<?> clazz) {
        Class<?> currentClass = clazz;
        while (true) {
            if (currentClass.getSuperclass() != null) {
                if (currentClass.equals(Event.class)) {
                    return true;
                }
                currentClass = currentClass.getSuperclass();
            } else {
                return false;
            }
        }
    }
}
