package org.emw.assertion.string;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emw.assertion.json.JsonAssertor;

/**
 * A list of <strong>expect</strong> assertion methods for string values.
 *
 * @see org.emw.assertion.collection.CollectionAssertor
 * @see org.emw.assertion.bool.BooleanAssertor
 * @see org.emw.assertion.number.NumberAssertor
 * @see org.emw.assertion.date.DateAssertor
 * @see org.emw.assertion.datetime.DateTimeAssertor
 * @see org.emw.assertion.time.TimeAssertor
 * @see org.emw.assertion.json.JsonAssertor
 */
public interface StringAssertor {
    default StringExpect expect(@Nullable String actual) {
        return expect("", actual);
    }

    default StringExpect expect(@NonNull String labelForActual, @Nullable String actual) {
        return new StringExpect(null, labelForActual, actual, false, false);
    }
}
