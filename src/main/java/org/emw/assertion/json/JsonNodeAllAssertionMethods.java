package org.emw.assertion.json;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

public class JsonNodeAllAssertionMethods extends JsonNodeAssertionMethods {
    public final JsonNodeNotAssertionMethods not;
    public final JsonNodeCaseInsensitivelyAssertionMethods caseInsensitively;

    protected JsonNodeAllAssertionMethods(@Nullable Object obj, boolean negated, boolean ignoreCase, List<String> excludedNodes) {
        super(obj, negated, ignoreCase, excludedNodes);
        this.not = new JsonNodeNotAssertionMethods(obj, !negated, ignoreCase, excludedNodes);
        this.caseInsensitively = new JsonNodeCaseInsensitivelyAssertionMethods(obj, negated, true, excludedNodes);
    }

    public JsonNodeAllAssertionMethods excluding(@NonNull String jsonPointer) {
        this.excludedNodes.add(jsonPointer);
        return this;
    }
}
