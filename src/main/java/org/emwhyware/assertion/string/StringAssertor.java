package org.emwhyware.assertion.string;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public interface StringAssertor {
    default StringIgnoringCaseOrTo expect(@Nullable String actual) {
        return expect("", actual);
    }

    default StringIgnoringCaseOrTo expect(@NonNull String labelForActual, @Nullable String actual) {
        return new StringIgnoringCaseOrTo(null, labelForActual, actual, false, false);
    }
}
