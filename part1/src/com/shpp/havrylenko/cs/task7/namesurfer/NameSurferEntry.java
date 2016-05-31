package com.shpp.havrylenko.cs.task7.namesurfer;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.Arrays;
import java.util.stream.Collectors;

public class NameSurferEntry implements NameSurferConstants {

    private String name;
    private Integer[] positions;

    /**
     * Test
     */
    public static void main(String[] args) {
        NameSurferEntry entry = new NameSurferEntry("Sam 58 69 99 131 168 236 278 380 467 408 466 997");
        System.out.println(entry.toString());
    }

	/* Constructor: NameSurferEntry(line) */

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        String[] lines = line.split(" ");
        this.name = lines[0];
        positions = Arrays.stream(Arrays.copyOfRange(lines, 1, lines.length)).map(Integer::parseInt).toArray(Integer[]::new);
    }

	/* Method: getName() */

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return this.name;
    }

	/* Method: getRank(decade) */

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        if(decade >= 0 && decade <= NameSurferConstants.NDECADES)
            return positions[decade];
        return 0;
    }

	/* Method: toString() */

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        String pos = Arrays.stream(positions).map(Object::toString).collect(Collectors.joining(" "));
        return getName() + " [ " + pos + "]";
    }
}

