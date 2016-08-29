package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

    public final String value;
    private boolean isPrivate;
    
    public final Block blk;
    public final PostalCode postalCode;
    public final Street street;
    public final Unit unit;
    

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        this.isPrivate = isPrivate;
        if (!isValidAddress(address)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        this.value = address;
        
        String [] splitAdd=address.split(",");
        blk =new Block(splitAdd[0]);
        street =new Street(splitAdd[1]);
        unit =new Unit(splitAdd[2]);
        postalCode =new PostalCode(splitAdd[3]);
    }

    /**
     * Returns true if a given string is a valid person email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.blk.getBlkNum().equals(((Address)other).blk.getBlkNum()) 
                && this.postalCode.getPostalCode().equals(((Address)other).postalCode.getPostalCode())
                && this.street.getStreetNum().equals(((Address)other).street.getStreetNum())
                && this.unit.getUnitNum().equals(((Address)other).unit.getUnitNum()));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
