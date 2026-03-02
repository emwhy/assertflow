package org.emwhyware.assertion.regression;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.emwhyware.assertion.AssertionGroup;
import org.emwhyware.assertion.Assertor;
import org.emwhyware.assertion.exception.AssertionGroupError;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class AssertionTest implements Assertor {
    @Test
    public void testString() {
        final String nullString = null;
        
        expect("Test 1", "test").to.startWith("te");
        expect("Test 2", "test").ignoringCase.to.startWith("TES");
        expect("Test 3", "test").not.to.startWith("es");
        expect("Test 4", "test").ignoringCase.not.to.startWith("ES");

        expect("Test 5", "test").to.endWith("st");
        expect("Test 6", "test").ignoringCase.to.endWith("ST");
        expect("Test 7", "test").not.to.endWith("es");
        expect("Test 8", "test").ignoringCase.not.to.endWith("ES");

        expect("Test 9", "test").to.equal("test");
        expect("Test 10", "test").ignoringCase.to.equal("TEST");
        expect("Test 11", "test").not.to.equal("es");
        expect("Test 12", "test").ignoringCase.not.to.equal("ES");

        expect("Test 13", "test").to.contain("es");
        expect("Test 14", "test").ignoringCase.to.contain("ES");
        expect("Test 15", "test").not.to.contain("estt");
        expect("Test 16", "test").ignoringCase.not.to.contain("ESTT");

        expect("Test 17", "test").to.match("\\w+");
        expect("Test 18", "test").not.to.match("\\d+");

        expect("Test 19", "test").to.beOneOf("test", "test2", "test3");
        expect("Test 20", "test").ignoringCase.to.beOneOf("TEST", "TEST2",  "TEST3");
        expect("Test 21", "test").not.to.beOneOf("TEST", "TEST2", "TEST3");
        expect("Test 22", "test").ignoringCase.not.to.beOneOf("TEST1", "TEST2", "TEST3");

        expect("Test 23", "").to.beEmpty();
        expect("Test 24", "test").not.to.beEmpty();
        expect("Test 25", nullString).to.beNull();
        expect("Test 26", "null").not.to.beNull();

        expectError(() -> expect("Test 27", "test").to.startWith("es"));

        // startWith Failures
        expectError(() -> expect("Test 28", "test").ignoringCase.to.startWith("abc"));
        expectError(() -> expect("Test 29", "test").not.to.startWith("te"));
        expectError(() -> expect("Test 30", "test").ignoringCase.not.to.startWith("TE"));

        // endWith Failures
        expectError(() -> expect("Test 31", "test").to.endWith("te"));
        expectError(() -> expect("Test 32", "test").ignoringCase.to.endWith("ABC"));
        expectError(() -> expect("Test 33", "test").not.to.endWith("st"));
        expectError(() -> expect("Test 34", "test").ignoringCase.not.to.endWith("ST"));

        // equal Failures
        expectError(() -> expect("Test 35", "test").to.equal("TEST"));
        expectError(() -> expect("Test 36", "test").ignoringCase.to.equal("abc"));
        expectError(() -> expect("Test 37", "test").not.to.equal("test"));
        expectError(() -> expect("Test 38", "test").ignoringCase.not.to.equal("TEST"));

        // contain Failures
        expectError(() -> expect("Test 39", "test").to.contain("abc"));
        expectError(() -> expect("Test 40", "test").ignoringCase.to.contain("ABC"));
        expectError(() -> expect("Test 41", "test").not.to.contain("es"));
        expectError(() -> expect("Test 42", "test").ignoringCase.not.to.contain("ES"));

        // match (Regex) Failures
        expectError(() -> expect("Test 43", "test").to.match("\\d+"));
        expectError(() -> expect("Test 44", "test").not.to.match(".*st"));

        // beOneOf Failures
        expectError(() -> expect("Test 45", "test").to.beOneOf("abc", "123"));
        expectError(() -> expect("Test 46", "test").ignoringCase.to.beOneOf("ABC", "123"));
        expectError(() -> expect("Test 47", "test").not.to.beOneOf("test", "other"));
        expectError(() -> expect("Test 48", "test").ignoringCase.not.to.beOneOf("TEST", "other"));

        // beEmpty Failures
        expectError(() -> expect("Test 49", "test").to.beEmpty());
        expectError(() -> expect("Test 50", "").not.to.beEmpty());

        // beNull Failures
        expectError(() -> expect("Test 51", "test").to.beNull());
        expectError(() -> expect("Test 52", nullString).not.to.beNull());

        // Blank/Whitespace checks (if applicable in your class)
        expectError(() -> expect("Test 53", "   ").to.beEmpty());
    }

    @Test
    public void testCollection() {
        expect("Test 1", new String[] { "test1", "test2" }).to.beSameAs(new String[] { "test1", "test2" });
        expect("Test 2", new String[] { "test1", "test2" }).to.beSameInAnyOrderAs(new String[] { "test2", "test1" });
        expect("Test 3", new String[] { "test1", "test2" }).to.have("test2", "test1");
        expect("Test 4", new String[] { "test1", "test2" }).ignoringCase.to.beSameAs(new String[] { "Test1", "test2" });
        expect("Test 5", new String[] { "test1", "test2" }).ignoringCase.to.beSameInAnyOrderAs(new String[] { "Test2", "test1" });
        expect("Test 6", new String[] { "test1", "test2" }).ignoringCase.to.have("Test2");
        expect("Test 7", new String[] { "test1", "test2" }).to.haveSizeOf(2);

        expectError(() -> expect("Test 8", new String[] { "test1", "test2" }).to.beSameAs(new String[] { "Test1", "test2" }));
        expectError(() -> expect("Test 9", new String[] { "test1", "test2" }).not.to.beSameAs(new String[] { "test1", "test2" }));
        expectError(() -> expect("Test 10", new String[] { "test1", "test2" }).to.beSameInAnyOrderAs(new String[] { "Test2", "test1" }));
        expectError(() -> expect("Test 11", new String[] { "test1", "test2" }).to.have("Test2"));
        expectError(() -> expect("Test 12", new String[] { "test1", "test2" }).to.have("test1", "test2", "test3"));
        expectError(() -> expect("Test 13", new String[] { "test1", "test2" }).to.haveSizeOf(1));
    }

    @Test
    public void testBoolean() {
        expect("Test 1", true).to.beTrue();
        expect("Test 2", false).not.to.beTrue();

        expectError(() -> expect("Test 3", false).to.beTrue());
        expectError(() -> expect("Test 4", true).not.to.beTrue());
    }

    @Test
    public void testDate() {
        expect("Test 1", LocalDate.of(2020, 1, 1)).to.beSameDate(LocalDate.of(2020, 1, 1));
        expect("Test 2", LocalDate.of(2020, 1, 1)).not.to.beSameDate(LocalDate.of(2020, 1, 2));
        expect("Test 3", LocalDate.of(2020, 1, 1)).to.beAfter(LocalDate.of(2019, 12, 31));
        expect("Test 4", LocalDate.of(2020, 1, 1)).to.beBefore(LocalDate.of(2020, 1, 2));
        expect("Test 5", LocalDate.of(2020, 1, 1)).to.beSameOrBefore(LocalDate.of(2020, 1, 1));
        expect("Test 6", LocalDate.of(2020, 1, 1)).to.beSameOrBefore(LocalDate.of(2020, 1, 2));
        expect("Test 7", LocalDate.of(2020, 1, 1)).to.beSameOrAfter(LocalDate.of(2020, 1, 1));
        expect("Test 8", LocalDate.of(2020, 1, 1)).to.beSameOrAfter(LocalDate.of(2019, 12, 31));
        expect("Test 9", LocalDate.of(2020, 1, 1)).to.beBetween(LocalDate.of(2019, 12, 31), LocalDate.of(2020, 1, 2));

        expectError(() -> expect("Test 10", LocalDate.of(2020, 1, 1)).to.beSameDate(LocalDate.of(2020, 1, 2)));
        expectError(() -> expect("Test 11", LocalDate.of(2020, 1, 1)).not.to.beSameDate(LocalDate.of(2020, 1, 1)));
        expectError(() -> expect("Test 12", LocalDate.of(2020, 1, 1)).to.beAfter(LocalDate.of(2020, 1, 1)));
        expectError(() -> expect("Test 13", LocalDate.of(2020, 1, 1)).to.beAfter(LocalDate.of(2020, 1, 2)));
        expectError(() -> expect("Test 14", LocalDate.of(2020, 1, 1)).to.beBefore(LocalDate.of(2020, 1, 1)));
        expectError(() -> expect("Test 15", LocalDate.of(2020, 1, 1)).to.beBefore(LocalDate.of(2019, 12, 31)));
        expectError(() -> expect("Test 16", LocalDate.of(2020, 1, 1)).to.beSameOrBefore(LocalDate.of(2019, 12, 31)));
        expectError(() -> expect("Test 17", LocalDate.of(2020, 1, 1)).to.beSameOrAfter(LocalDate.of(2020, 1, 2)));
        expectError(() -> expect("Test 18", LocalDate.of(2020, 1, 3)).to.beBetween(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 2)));
        expectError(() -> expect("Test 19", LocalDate.of(2020, 1, 5)).to.beBetween(LocalDate.of(2019, 12, 31), LocalDate.of(2020, 1, 1)));
        expectError(() -> expect("Test 20", LocalDate.of(2020, 1, 1)).to.beBetween(LocalDate.of(2020, 1, 2), LocalDate.of(2021, 1, 1)));
    }

    @Test
    public void testNumber() {
        expect("Test 1", 10).to.equal(10);
        expect("Test 2", 10.1).to.equal(10.1f);
        expect("Test 3", 10.1).not.to.equal(10);
        expect("Test 4", 10.1).to.beMoreThan(10);
        expect("Test 5", 10.1).to.beLessThan(10.2f);
        expect("Test 6", 10).to.beMoreThanOrEqual(9.9f);
        expect("Test 7", 10).to.beLessThanOrEqual(10.1f);
        expect("Test 8", 10).to.beMoreThanOrEqual(10.0f);
        expect("Test 9", 10).to.beLessThanOrEqual(10.0);
        expect("Test 10", 10).to.beBetween(9.9, 11);

        expectError(() -> expect("Test 11", 10).to.beBetween(10.1, 11));

        expectError(() -> expect("Test 12", 10).to.equal(11));
        expectError(() -> expect("Test 13", 10.1).to.equal(10.11));
        expectError(() -> expect("Test 14", 10).not.to.equal(10.0));

        expectError(() -> expect("Test 15", 10).to.beMoreThan(10));
        expectError(() -> expect("Test 16", 10).to.beMoreThan(10.1));
        expectError(() -> expect("Test 17", -5).to.beMoreThan(0));

        expectError(() -> expect("Test 18", 10).to.beLessThan(10));
        expectError(() -> expect("Test 19", 10.5).to.beLessThan(10.4));
        expectError(() -> expect("Test 20", 0).to.beLessThan(-1));

        expectError(() -> expect("Test 21", 10).to.beMoreThanOrEqual(11));
        expectError(() -> expect("Test 22", 10.1).to.beLessThanOrEqual(10.0));
        expectError(() -> expect("Test 23", -10).to.beMoreThanOrEqual(-9.9));

        expectError(() -> expect("Test 24", 11.1).to.beBetween(10, 11));
        expectError(() -> expect("Test 25", 9.9).to.beBetween(10, 11));
        expectError(() -> expect("Test 26", 5).to.beBetween(6, 7));

        expectError(() -> expect("Test 27", 100L).to.beLessThan(50));
        expectError(() -> expect("Test 28", 0.01f).to.beMoreThan(0.1));
    }

    @Test
    public void testGroup() {
        try {
            AssertionGroup.group(g -> {
                g.expect("test").to.equal("test1");
                g.expect("test").to.equal("test2");
                g.expect(1).to.equal(1);
                g.expect(1).to.equal(0);
            });
        } catch (AssertionGroupError ex) {
            expect(ex.getMessage()).to.contain("Expected 'test' to equal 'test1'.");
            expect(ex.getMessage()).to.contain("Expected 'test' to equal 'test2'.");
            expect(ex.getMessage()).to.contain("Expected '1' to equal '0'.");
        }
    }

    private void expectError(AssertionErrorAction action) {
        try {
            action.expectAssertionError();
        } catch (AssertionError ex) {
            System.out.println("Error message = \"" + ex.getMessage() + "\"");
            return;
        }
        throw new AssertionError("Expected AssertionError.");
    }

    private void expectError(@NonNull AssertionErrorAction action, @NonNull String errorMessage) {
        try {
            action.expectAssertionError();
        } catch (AssertionError ex) {
            if (!ex.getMessage().equals(errorMessage)) {
                throw new AssertionError("Unexpected AssertionError message. Actual: [" + ex.getMessage() + "] Expected: [" + errorMessage + "]");
            }
            return;
        }
        throw new AssertionError("Expected AssertionError.");
    }

    interface AssertionErrorAction {
        void expectAssertionError();
    }
}
