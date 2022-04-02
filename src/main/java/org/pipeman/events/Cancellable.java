package org.pipeman.events;

public interface Cancellable {
    public boolean isCancelled();

    public void setCancelled(boolean cancel);
}
