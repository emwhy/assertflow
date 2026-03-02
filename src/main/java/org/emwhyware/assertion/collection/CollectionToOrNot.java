package org.emwhyware.assertion.collection;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;

import java.util.Collection;

public class CollectionToOrNot extends CollectionTo {
    public final CollectionTo not;

    protected CollectionToOrNot(@Nullable AssertionGroup group, @NonNull String labelForActual, @NonNull Collection<?> actual, boolean negated, boolean ignoreCase) {
        super(group, labelForActual, actual, negated, ignoreCase);
        this.not = new CollectionTo(group, labelForActual, actual, !negated, ignoreCase);
    }
}
