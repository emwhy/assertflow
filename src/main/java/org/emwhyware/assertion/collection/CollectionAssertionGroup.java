package org.emwhyware.assertion.collection;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.emwhyware.assertion.AssertionGroup;

import java.util.Arrays;
import java.util.Collection;

public final class CollectionAssertionGroup {
    private final AssertionGroup group;

    public CollectionAssertionGroup(@NonNull AssertionGroup group) {
        this.group = group;
    }

    public CollectionIgnoringCaseOrTo expect(@NonNull Object[] actual) {
        return expect("", actual);
    }

    public CollectionIgnoringCaseOrTo expect(@NonNull String labelForActual, @NonNull Object[] actual) {
        return new CollectionIgnoringCaseOrTo(group, labelForActual, Arrays.stream(actual).toList(), false, false);
    }

    public CollectionIgnoringCaseOrTo expect(@NonNull Collection<?> actual) {
        return expect("", actual);
    }

    public CollectionIgnoringCaseOrTo expect(@NonNull String labelForActual, @NonNull Collection<?> actual) {
        return new CollectionIgnoringCaseOrTo(group, labelForActual, actual, false, false);
    }
}
