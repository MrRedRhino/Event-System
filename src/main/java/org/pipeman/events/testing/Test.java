package org.pipeman.events.testing;

import org.pipeman.events.ListenerManager;

import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        ListenerManager.registerEventHandler(new TestListener());
        ListenerManager.registerEventHandler(new TestListener());
        ListenerManager.fireEvent(new TestEvent3());
    }
}
