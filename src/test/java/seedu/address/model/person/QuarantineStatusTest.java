package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class QuarantineStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new QuarantineStatus(null));
    }

    @Test
    public void constructor_invalidQuarantineStatus_throwsIllegalArgumentException() {
        String invalidQuarantineStatus = "quarantined";
        assertThrows(IllegalArgumentException.class, () -> new QuarantineStatus(invalidQuarantineStatus));
    }

    @Test
    public void isValidQuarantineStatus() {
        // null quarantine status
        assertThrows(NullPointerException.class, () -> QuarantineStatus.isValidQuarantineStatus(null));

        // invalid quarantine status
        assertFalse(QuarantineStatus.isValidQuarantineStatus("")); // empty string
        assertFalse(QuarantineStatus.isValidQuarantineStatus(" ")); // spaces only
        assertFalse(QuarantineStatus.isValidQuarantineStatus("91")); // numbers only
        assertFalse(QuarantineStatus.isValidQuarantineStatus("phone")); // alphabets
        assertFalse(QuarantineStatus.isValidQuarantineStatus("9011p041")); // mix of alphabets and digits

        // valid quarantine status
        assertTrue(QuarantineStatus.isValidQuarantineStatus("true")); // boolean true
        assertTrue(QuarantineStatus.isValidQuarantineStatus("True")); // capitalised true
        assertTrue(QuarantineStatus.isValidQuarantineStatus("false")); // boolean false
        assertTrue(QuarantineStatus.isValidQuarantineStatus("False")); // capitalised false
        assertTrue(QuarantineStatus.isValidQuarantineStatus("FaLSe")); // mix of upper and lower cases
    }

    @Test
    public void equals() {
        QuarantineStatus quarantineStatus = new QuarantineStatus("True");

        // same object -> returns true
        assertTrue(quarantineStatus.equals(quarantineStatus));

        // same values -> returns true
        QuarantineStatus quarantineStatusCopy = new QuarantineStatus(quarantineStatus.value);
        assertTrue(quarantineStatus.equals(quarantineStatusCopy));

        // different types -> returns false
        assertFalse(quarantineStatus.equals(1));

        // null -> returns false
        assertFalse(quarantineStatus.equals(null));

        // different remark -> returns false
        QuarantineStatus differentQuarantineStatus = new QuarantineStatus("false");
        assertFalse(quarantineStatus.equals(differentQuarantineStatus));
    }
}
