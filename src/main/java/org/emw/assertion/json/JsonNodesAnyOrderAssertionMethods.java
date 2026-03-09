package org.emw.assertion.json;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emw.assertion.AssertionGroup;
import org.emw.assertion.collection.CollectionAssertionMethods;
import org.json.JSONArray;

import java.util.Collection;
import java.util.List;

public class JsonNodesAnyOrderAssertionMethods extends JsonNodesAssertionMethods {
    public final JsonNodesCaseInsensitivityOnlyAssertionMethods not;
    public final JsonNodesNotOnlyAssertionMethods caseInsensitively;

    JsonNodesAnyOrderAssertionMethods(@Nullable JSONArray actual, boolean negated, boolean ignoreCase, boolean anyOrder, @NonNull List<String> excludedNodes) {
        super(actual, negated, ignoreCase, anyOrder, excludedNodes);
        this.not = new JsonNodesCaseInsensitivityOnlyAssertionMethods(actual, !negated, ignoreCase, anyOrder, excludedNodes);
        this.caseInsensitively = new JsonNodesNotOnlyAssertionMethods(actual, negated, true, anyOrder, excludedNodes);
    }

    public JsonNodesAnyOrderAssertionMethods excluding(@NonNull String jsonPointer) {
        this.excludedNodes.add(jsonPointer);
        return this;
    }
}
