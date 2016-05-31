package com.shpp.havrylenko.cs.task7.namesurfer;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NameSurferDataBase implements NameSurferConstants {

    private List<String> database;
    private List<NameSurferEntry> databaseEntries;

    public static void main(String[] args) {
        NameSurferDataBase db;
        try {
            db = new NameSurferDataBase(NAMES_DATA_FILE);
            System.out.println(db.findEntry("Sam"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

	/* Constructor: NameSurferDataBase(filename) */

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) throws IOException, URISyntaxException {
        try {
            URL path = NameSurferDataBase.class.getResource(filename);
            database = Files.readAllLines(Paths.get(path.toURI()));
        } catch(IOException | URISyntaxException e) {
            e.printStackTrace();
            throw e;
        }
        databaseEntries = new ArrayList<>();
        database.forEach(p -> databaseEntries.add(new NameSurferEntry(p)));
    }
	
	/* Method: findEntry(name) */

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        return databaseEntries.stream().filter(p -> (p.getName().toLowerCase().equals(name.toLowerCase())))
                              .collect(Collectors.toList()).get(0);
    }
}

