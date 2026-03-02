package org.emwhyware.assertion.collection;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Arrays;
import java.util.Collection;

public interface CollectionAssertor {
    default CollectionIgnoringCaseOrTo expect(@NonNull Object[] actual) {
        return expect("", actual);
    }

    default CollectionIgnoringCaseOrTo expect(@NonNull String labelForActual, @NonNull Object[] actual) {
        return new CollectionIgnoringCaseOrTo(null, labelForActual, Arrays.stream(actual).toList(), false, false);
    }

    default CollectionIgnoringCaseOrTo expect(@NonNull Collection<?> actual) {
        return expect("", actual);
    }

    default CollectionIgnoringCaseOrTo expect(@NonNull String labelForActual, @NonNull Collection<?> actual) {
        return new CollectionIgnoringCaseOrTo(null, labelForActual, actual, false, false);
    }
}
