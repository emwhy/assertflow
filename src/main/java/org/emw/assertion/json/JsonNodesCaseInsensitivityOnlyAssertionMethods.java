package org.emw.assertion.json;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emw.assertion.AssertionGroup;
import org.emw.assertion.collection.CollectionAssertionMethods;
import org.json.JSONArray;

import java.util.Collection;
import java.util.List;

public class JsonNodesCaseInsensitivityOnlyAssertionMethods extends JsonNodesAssertionMethods {
    public final JsonNodesAssertionMethods caseInsensitively;

    JsonNodesCaseInsensitivityOnlyAssertionMethods(@Nullable JSONArray actual, boolean negated, boolean ignoreCase, boolean anyOrder, @NonNull List<String> excludedNodes) {
        super(actual, negated, ignoreCase, anyOrder,  excludedNodes);
        this.caseInsensitively = new JsonNodesAssertionMethods(actual, negated, true, anyOrder,  excludedNodes);
    }

    public JsonNodesCaseInsensitivityOnlyAssertionMethods excluding(@NonNull String jsonPointer) {
        this.excludedNodes.add(jsonPointer);
        return this;
    }

}
