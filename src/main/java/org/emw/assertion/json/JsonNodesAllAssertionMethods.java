package org.emw.assertion.json;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emw.assertion.AssertionGroup;
import org.emw.assertion.collection.CollectionAssertionMethods;
import org.json.JSONArray;

import java.util.Collection;
import java.util.List;

public class JsonNodesAllAssertionMethods extends JsonNodesAssertionMethods {
    public final JsonNodesNotAssertionMethods not;
    public final JsonNodesAnyOrderAssertionMethods inAnyOrder;
    public final JsonNodesCaseInsensitivelyAssertionMethods caseInsensitively;

    JsonNodesAllAssertionMethods(@Nullable JSONArray actual, boolean negated, boolean ignoreCase, boolean anyOrder, @NonNull List<String> excludedNodes) {
        super(actual, negated, ignoreCase, anyOrder, excludedNodes);

        this.not = new JsonNodesNotAssertionMethods(actual, !negated, ignoreCase, anyOrder, excludedNodes);
        this.inAnyOrder = new JsonNodesAnyOrderAssertionMethods(actual, negated, ignoreCase, true, excludedNodes);
        this.caseInsensitively = new JsonNodesCaseInsensitivelyAssertionMethods(actual, negated, true, anyOrder, excludedNodes);
    }

    public JsonNodesAllAssertionMethods excluding(@NonNull String jsonPointer) {
        this.excludedNodes.add(jsonPointer);
        return this;
    }

}
