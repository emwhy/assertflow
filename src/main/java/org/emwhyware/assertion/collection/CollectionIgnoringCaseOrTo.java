package org.emwhyware.assertion.collection;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;
import org.emwhyware.assertion.string.StringTo;
import org.emwhyware.assertion.string.StringToOrNot;

import java.util.Collection;

public class CollectionIgnoringCaseOrTo extends CollectionToOrNot {
    public final CollectionToOrNot ignoringCase;

    protected CollectionIgnoringCaseOrTo(@Nullable AssertionGroup group, @NonNull String labelForActual, @NonNull Collection<?> actual, boolean negated, boolean ignoreCase) {
        super(group, labelForActual, actual, negated, ignoreCase);

        this.ignoringCase = new CollectionToOrNot(group, labelForActual, actual, negated, true);
    }
}
