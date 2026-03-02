package org.emwhyware.assertion.collection;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;
import org.emwhyware.assertion.Connector;

import java.util.Collection;

public class CollectionTo extends Connector {
    public final CollectionAllConditions to;

    protected CollectionTo(@Nullable AssertionGroup group, @NonNull String labelForActual, @Nullable Collection<?> actual, boolean negated, boolean ignoreCase, boolean anyOrder) {
        super(group, labelForActual);
        this.to = new CollectionAllConditions(group, labelForActual, actual, negated, ignoreCase, anyOrder);
    }
}
