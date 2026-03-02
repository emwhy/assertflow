package org.emwhyware.assertion.collection;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;
import org.emwhyware.assertion.Connector;
import org.emwhyware.assertion.string.StringConditions;

import java.util.Collection;

public class CollectionTo extends Connector {
    public final CollectionConditions to;

    protected CollectionTo(@Nullable AssertionGroup group, @NonNull String labelForActual, @NonNull Collection<?> actual, boolean negated, boolean ignoreCase) {
        super(group, labelForActual);
        this.to = new CollectionConditions(group, labelForActual, actual, negated, ignoreCase);
    }
}
