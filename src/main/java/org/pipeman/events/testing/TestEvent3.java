package org.pipeman.events.testing;

import org.pipeman.events.Cancellable;

public class TestEvent3 extends TestEvent2 implements Cancellable {
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
