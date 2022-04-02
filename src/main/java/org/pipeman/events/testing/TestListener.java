package org.pipeman.events.testing;

import org.pipeman.events.EventHandler;
import org.pipeman.events.Listener;

public class TestListener implements Listener {
    @EventHandler
    public void onTestEvent(TestEvent event) {
        System.out.println("event");
    }

    @EventHandler
    public void onTest2Event(TestEvent3 event2) {
        System.out.println("event3");
    }

    @EventHandler
    public void onTest3Event(TestEvent3 event2) {
        System.out.println("event32");
    }
}
