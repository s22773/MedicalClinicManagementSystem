package Model;

import java.io.Serializable;

/**
 * The Gender enum represents the gender of a person.
 * It provides options for male, female, and other genders.
 */
public enum Gender implements Serializable {
    /**
     * Represents the male gender.
     */
    MALE,
    /**
     * Represents the female gender.
     */
    FEMALE,
    /**
     * Represents other genders not covered by male or female.
     */
    OTHER
}
