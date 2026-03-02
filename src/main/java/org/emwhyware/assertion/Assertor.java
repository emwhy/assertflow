package org.emwhyware.assertion;

import org.emwhyware.assertion.bool.BooleanAssertor;
import org.emwhyware.assertion.collection.CollectionAssertor;
import org.emwhyware.assertion.date.DateAssertor;
import org.emwhyware.assertion.number.NumberAssertor;
import org.emwhyware.assertion.string.StringAssertor;

public interface Assertor extends StringAssertor, CollectionAssertor, BooleanAssertor, DateAssertor, NumberAssertor {
}
