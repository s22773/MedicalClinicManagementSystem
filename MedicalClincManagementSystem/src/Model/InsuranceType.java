package Model;

import java.io.Serializable;

/**
 * The InsuranceType enum represents different types of insurance.
 * It provides options for employment, student, private, and public insurance types.
 */
public enum InsuranceType implements Serializable {
    /**
     * Represents employment insurance type.
     */
    EMPLOYMENT,
    /**
     * Represents student insurance type.
     */
    STUDENT,
    /**
     * Represents private insurance type.
     */
    PRIVATE,
    /**
     * Represents public insurance type.
     */
    PUBLIC
}
