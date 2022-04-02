package org.pipeman.events.testing;

import org.pipeman.events.Cancellable;

public class TestEvent3 extends TestEvent2 implements Cancellable {
    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancel) {
    }
}
