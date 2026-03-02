package org.emwhyware.assertion.date;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;
import org.emwhyware.assertion.collection.CollectionTo;

import java.time.LocalDate;
import java.util.Collection;

public class DateToOrNot extends DateTo {
    public final DateTo not;

    protected DateToOrNot(@Nullable AssertionGroup group, @NonNull String labelForActual, @NonNull LocalDate actual, boolean negated) {
        super(group, labelForActual, actual, negated);
        this.not = new DateTo(group, labelForActual, actual, !negated);
    }
}
