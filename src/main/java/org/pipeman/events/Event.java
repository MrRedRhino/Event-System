package org.pipeman.events;

public abstract class Event implements Cancellable {
    boolean cancel = false;
}
