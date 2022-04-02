package org.pipeman.events.testing;

import org.pipeman.events.Cancellable;
import org.pipeman.events.Event;

public class TestEvent extends Event implements Cancellable {
    boolean cancel = false;
    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
