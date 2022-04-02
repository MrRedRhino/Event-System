package org.pipeman.events;

public class TestListener implements Listener {

    @EventHandler
    public void onTestEvent(TestEvent event) {
        System.out.println("event");
    }
}
