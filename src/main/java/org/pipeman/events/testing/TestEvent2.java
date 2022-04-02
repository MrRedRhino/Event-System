package org.pipeman.events.testing;

import org.pipeman.events.Cancellable;
import org.pipeman.events.Event;

public class TestEvent2 extends Event implements Cancellable {
    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancel) {
    }
}
