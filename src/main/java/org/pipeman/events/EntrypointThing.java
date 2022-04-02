package org.pipeman.events;

import org.pipeman.events.testing.TestEvent;
import org.pipeman.events.testing.TestEvent2;
import org.pipeman.events.testing.TestEvent3;
import org.pipeman.events.testing.TestListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class EntrypointThing {
    private static final Map<Class<?>, List<RegisteredEventHandler>> listeners = new HashMap<>();

    public static void main(String[] args) {
        registerEventHandler(new TestListener());
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    fireEvent(new TestEvent3());
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 1000);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    fireEvent(new TestEvent());
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 2000);
    }

    public static void registerEventHandler(Listener listener) {
        Class<?> clazz = listener.getClass();
        for (Method method : clazz.getMethods()) {
            if (method.getAnnotation(EventHandler.class) != null) {
                if (method.getParameterCount() == 1) {
                    Class<?> param = method.getParameterTypes()[0];
                    if (isHighestClassEvent(param)) {
                        if (!listeners.containsKey(param)) listeners.put(param, new ArrayList<>());
                        listeners.get(param).add(new RegisteredEventHandler(clazz, method));
                    }
                }
            }
        }
        System.out.println(listeners);
    }

    public static boolean fireEvent(Event event) throws InvocationTargetException, IllegalAccessException {
        List<RegisteredEventHandler> handlers = listeners.get(event.getClass());
        for (RegisteredEventHandler handler : handlers) {
            handler.invokeMethod(event);
        }

        return false;
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
