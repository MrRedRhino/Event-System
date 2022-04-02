package org.pipeman.events;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class EntrypointThing {
    private static Map<Class<? extends Event>, List<RegisteredEventHandler>> listeners = new HashMap<>();

    public static void main(String[] args) {
        registerEventHandler(new TestListener());
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    fireEvent(new TestEvent());
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 1000);
    }

    public static void registerEventHandler(Listener listener) {
        Class<?> clazz = listener.getClass();
        List<Method> eventHandlers = new ArrayList<>();
        for (Method method : clazz.getMethods()) {
            for (Annotation a : method.getAnnotations()) {
                if (a.annotationType().equals(EventHandler.class)) {
                    eventHandlers.add(method);
                    break;
                }
            }
        }
        System.out.println(eventHandlers);
    }

    public static boolean fireEvent(Event event) throws InvocationTargetException, IllegalAccessException {
        List<RegisteredEventHandler> handlers = listeners.get(event.getClass());
        for (RegisteredEventHandler handler : handlers) {
            handler.invokeMethod(event);
        }

        return false;
    }
}
