package org.emwhyware.assertion;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public abstract class Connector {
    protected final String labelForActual;
    protected final AssertionGroup group;

    protected Connector(@Nullable AssertionGroup group, @NonNull String labelForActual) {
        this.group = group;
        this.labelForActual = labelForActual;
    }
}
