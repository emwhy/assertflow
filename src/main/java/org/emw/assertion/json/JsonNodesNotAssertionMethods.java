package org.emw.assertion.json;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emw.assertion.AssertionGroup;
import org.emw.assertion.collection.CollectionAssertionMethods;
import org.json.JSONArray;

import java.util.Collection;
import java.util.List;

public class JsonNodesNotAssertionMethods extends JsonNodesAssertionMethods {
    public final JsonNodesCaseInsensitivityOnlyAssertionMethods inAnyOrder;
    public final JsonNodesAnyOrderOnlyAssertionMethods caseInsensitively;

    JsonNodesNotAssertionMethods(@Nullable JSONArray actual, boolean negated, boolean ignoreCase, boolean anyOrder, @NonNull List<String> excludedNodes) {
        super(actual, negated, ignoreCase, anyOrder, excludedNodes);
        this.inAnyOrder = new JsonNodesCaseInsensitivityOnlyAssertionMethods(actual, negated, ignoreCase, true, excludedNodes);
        this.caseInsensitively = new JsonNodesAnyOrderOnlyAssertionMethods(actual, negated, true, anyOrder, excludedNodes);
    }

    public JsonNodesNotAssertionMethods excluding(@NonNull String jsonPointer) {
        this.excludedNodes.add(jsonPointer);
        return this;
    }

}
