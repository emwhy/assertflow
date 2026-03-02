package org.emwhyware.assertion.number;

import org.checkerframework.checker.nullness.qual.NonNull;

public interface NumberAssertor {
    default NumberToOrNot expect(int actual) {
        return expect(Integer.valueOf(actual));
    }

    default NumberToOrNot expect(long actual) {
        return expect(Long.valueOf(actual));
    }

    default NumberToOrNot expect(float actual) {
        return expect(Float.valueOf(actual));
    }

    default NumberToOrNot expect(double actual) {
        return expect(Double.valueOf(actual));
    }

    default NumberToOrNot expect(@NonNull String labelForActual, int actual) {
        return expect(labelForActual, Integer.valueOf(actual));
    }

    default NumberToOrNot expect(@NonNull String labelForActual, long actual) {
        return expect(labelForActual, Long.valueOf(actual));
    }

    default NumberToOrNot expect(@NonNull String labelForActual, float actual) {
        return expect(labelForActual, Float.valueOf(actual));
    }

    default NumberToOrNot expect(@NonNull String labelForActual, double actual) {
        return expect(labelForActual, Double.valueOf(actual));
    }

    default NumberToOrNot expect(@NonNull Number actual) {
        return expect("", actual);
    }

    default NumberToOrNot expect(@NonNull String labelForActual, @NonNull Number actual) {
        return new NumberToOrNot(null, labelForActual, actual, false);
    }
}
