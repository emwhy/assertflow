package org.emwhyware.assertion.date;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.sql.Date;
import java.time.LocalDate;

public interface DateAssertor {
    default DateToOrNot expect(@NonNull Date actual) {
        return expect("", actual.toLocalDate());
    }

    default DateToOrNot expect(@NonNull LocalDate actual) {
        return expect("", actual);
    }

    default DateToOrNot expect(@NonNull String labelForActual, @NonNull Date actual) {
        return expect(labelForActual, actual.toLocalDate());
    }

    default DateToOrNot expect(@NonNull String labelForActual, @NonNull LocalDate actual) {
        return new DateToOrNot(null, labelForActual, actual, false);
    }
}
