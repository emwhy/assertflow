package org.emwhyware.assertion.date;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.emwhyware.assertion.AssertionGroup;

import java.sql.Date;
import java.time.LocalDate;

public final class DateAssertionGroup {
    private final AssertionGroup group;

    public DateAssertionGroup(@NonNull AssertionGroup group) {
        this.group = group;
    }

    public DateToOrNot expect(@NonNull Date actual) {
        return expect("", actual.toLocalDate());
    }

    public DateToOrNot expect(@NonNull LocalDate actual) {
        return expect("", actual);
    }

    public DateToOrNot expect(@NonNull String labelForActual, @NonNull Date actual) {
        return expect(labelForActual, actual.toLocalDate());
    }

    public DateToOrNot expect(@NonNull String labelForActual, @NonNull LocalDate actual) {
        return new DateToOrNot(group, labelForActual, actual, false);
    }
}
