package org.pipeman.events;

public class TestEvent extends Event implements Cancellable {
    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancel) {

    }
}
