package org.emwhyware.assertion.bool;

import org.checkerframework.checker.nullness.qual.NonNull;

public interface BooleanAssertor {
    default BooleanToOrNot expect(boolean actual) {
        return expect("", actual);
    }

    default BooleanToOrNot expect(@NonNull String labelForActual, boolean actual) {
        return new BooleanToOrNot(null, labelForActual, actual, false);
    }
}
