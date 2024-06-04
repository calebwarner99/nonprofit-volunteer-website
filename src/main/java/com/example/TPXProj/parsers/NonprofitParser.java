package com.example.TPXProj.parsers;

import com.example.TPXProj.models.Nonprofit;
import com.example.TPXProj.models.DatabaseNonprofit;

import java.util.ArrayList;

/**
 * Nonprofit parser handles all Nonprofit parsing.
 * This includes translating from html form String to Nonprofit object
 * as well as translating between database object representation of Nonprofit and the Nonprofit object.
 *
 * @author Caleb Warner
 */
public class NonprofitParser {
    // *************************************************************************
    // Constants
    // *************************************************************************
    /** Constant field to reference the name in a split html form component. */
    private static final Integer componentName = 0;

    /** Constant field to reference the value in a split html form component. */
    private static final Integer componentValue = 1;


    // *************************************************************************
    // Private Methods
    // *************************************************************************
    /**
     * Strings that contain special characters are stored in the database as "%(hex ascii value)".
     * This helper method reverts those special characters to their original values.
     *
     * @param input String as stored in the database
     * @return String as should be read by viewers
     */
    private static String cleanString(String input) {
        // Return null if already null
        if (input == null) {
            return null;
        }

        // Spaces are stored as "+"; revert back to spaces
        input = input.replace('+', ' ');

        String operateString = input;
        StringBuilder output = new StringBuilder();
        int index = 0;

        // Loop through operateString, looking for "%"; if found attempt to convert to char and append to output
        while ((index = operateString.indexOf("%")) >= 0) {
            try {
                // Extract hex value and attempt a parseInt with radix = 16 (hexadecimal)
                String hex = operateString.substring(index + 1, index + 3);
                int character = Integer.parseInt(hex, 16);

                // If succeeds, append everything before that character as well as the character
                output.append(operateString, 0,index);
                output.append((char) character);

                // Reduce operateString to continue looking for "%"
                operateString = operateString.substring(index + 3);
            } catch (NumberFormatException e) {
                // If failure, assume the viewer simply wanted a "%"; append to output and reduce operateString
                output.append(operateString, 0, index + 1);
                operateString = operateString.substring(index + 1);
            } // try-catch
        } // while

        // Append everything after last "%"
        output.append(operateString);

        // Return String ready to view
        return output.toString();
    } // private static String cleanString()

    /**
     * Some Nonprofits don't contain the information they need to submit.
     * This helper function determines whether that is the case or not.
     *
     * @param nonprofit Nonprofit object to be checked
     * @return True if no fields are null, false otherwise
     */
    private static boolean errorCheckNonprofit(Nonprofit nonprofit) {
        // Check if name is null or empty
        if (nonprofit.getName() == null || nonprofit.getName().equals("")) {
            return false;
        }

        // Check if website is null or empty
        if (nonprofit.getWebsite() == null || nonprofit.getWebsite().equals("")) {
            return false;
        }

        // Check if phone is null or empty
        if (nonprofit.getPhone() == null || nonprofit.getPhone().equals("")) {
            return false;
        }

        // Check if email is null or empty
        if (nonprofit.getEmail() == null || nonprofit.getEmail().equals("")) {
            return false;
        }

        // Check if needs are null or empty
        if (nonprofit.getNeeds().size() == 0) {
            return false;
        }

        // Check if activities are null or empty
        if (nonprofit.getActivities().size() == 0) {
            return false;
        }

        // Check if skills are null or empty
        if (nonprofit.getSkills().size() == 0) {
            return false;
        }

        // Check if commitments are null or empty
        if (nonprofit.getCommitments().size() == 0) {
            return false;
        }

        // Check if location is null or empty
        if (nonprofit.getLocation() == null || nonprofit.getLocation() < 0 || nonprofit.getLocation() > 8) {
            return false;
        }

        // Success; return true
        return true;
    } // private static boolean errorCheckNonprofit()


    // *************************************************************************
    // Public Methods
    // *************************************************************************
    /**
     * Takes a string given by an html form request and splits based on the "&" and "=" syntax used.
     * Checks that values are not missing and assigns them to a new Nonprofit object.
     * This new Nonprofit object is returned.
     *
     * @param parseString String generated by an html form post request
     * @return New Nonprofit object created from info found in parseString
     */
    public static Nonprofit parseString(String parseString) {
        // Split up String based on html form "&" syntax into components
        String[] parseArray = parseString.split("&");

        // Create empty Nonprofit to be filled
        Nonprofit nonprofit = new Nonprofit();

        // Iterate through components and add info to nonprofit
        for (String formData : parseArray) {
            // Split up components based on the "=" syntax dividing field name and value
            String[] components = formData.split("=");
            switch(components[componentName]) {
                case "name":
                    // Set name accordingly
                    if (components.length < 2) {
                        nonprofit.setName(null);
                    } else {
                        nonprofit.setName(components[componentValue]);
                    }
                    break;
                case "website":
                    // Set website accordingly
                    if (components.length < 2) {
                        nonprofit.setWebsite(null);
                    } else {
                        nonprofit.setWebsite(components[componentValue]);
                    }
                    break;
                case "phone":
                    // Set phone accordingly
                    if (components.length < 2) {
                        nonprofit.setPhone(null);
                    } else {
                        nonprofit.setPhone(components[componentValue]);
                    }
                    break;
                case "email":
                    // Set email accordingly
                    if (components.length < 2) {
                        nonprofit.setEmail(null);
                    } else {
                        nonprofit.setEmail(components[componentValue]);
                    }
                    break;
                case "need":
                    // Set need accordingly (no check is necessary as it won't appear if it is empty)
                    nonprofit.addNeed(Integer.parseInt(components[componentValue]));
                    break;
                case "activity":
                    // Set activity accordingly (same as above and so on)
                    nonprofit.addActivity(Integer.parseInt(components[componentValue]));
                    break;
                case "skill":
                    // Set skill accordingly
                    nonprofit.addSkill(Integer.parseInt(components[componentValue]));
                    break;
                case "commitment":
                    // Set commitment accordingly
                    nonprofit.addCommitment(Integer.parseInt(components[componentValue]));
                    break;
                case "location":
                    // Set location accordingly
                    nonprofit.setLocation(Integer.parseInt(components[componentValue]));
                    break;
                default:
                    // Should not ever occur, print error message and disregard
                    System.out.println("This shouldn't exist, lol");
                    break;
            } // switch
        } // for each

        // Return filled in nonprofit
        return nonprofit;
    } // public static Nonprofit parseString()

    /**
     * Takes in a list of database objects and translates them to Nonprofit objects stored in an ArrayList.
     *
     * @param nonprofits All database objects returned by the repository object
     * @return ArrayList of Nonprofit objects that have been parsed
     */
    public static ArrayList<Nonprofit> parseDatabase(Iterable<DatabaseNonprofit> nonprofits) {
        // Create new Nonprofit ArrayList
        ArrayList<Nonprofit> nonprofitArrayList = new ArrayList<>();

        // Iterate through database nonprofits and do necessary translating to store into Nonprofit objects
        for (DatabaseNonprofit nonprofit : nonprofits) {
            // Create new Nonprofit object (curNonprofit = Nonprofit() & nonprofit = DatabaseNonprofit()
            Nonprofit curNonprofit = new Nonprofit();

            // Set contact info fields
            curNonprofit.setName(nonprofit.getName());
            curNonprofit.setWebsite(nonprofit.getWebsite());
            curNonprofit.setPhone(nonprofit.getPhone());
            curNonprofit.setEmail(nonprofit.getEmail());


            if (nonprofit.getNeeds() == null ||
                nonprofit.getActivities() == null ||
                nonprofit.getSkills() == null ||
                nonprofit.getCommitments() == null) {

                //Don't include bad nonprofit in ArrayList
                continue;
            }

            // Needs is stored as String in database so translate to Integer
            int needs = Integer.parseInt(nonprofit.getNeeds());
            for (int i = 0; needs > 0; i++) {
                // String in database acts as binary number; test bit in needs to see if contains active flag
                if (needs % 10 != 0) {
                    curNonprofit.addNeed(i);
                }
                needs /= 10;
            } // for

            // Activities is stored as String in database; translate to Long (more activities)
            long activities = Long.parseLong(nonprofit.getActivities());
            for (int i = 0; activities > 0; i++) {
                // Iterate through pseudo-binary long and add activities as necessary
                if (activities % 10 != 0) {
                    curNonprofit.addActivity(i);
                }
                activities /= 10;
            } // for

            // Similar to activities
            long skills = Long.parseLong(nonprofit.getSkills());
            for (int i = 0; skills > 0; i++) {
                // Iterate through and add as necessary
                if (skills % 10 != 0) {
                    curNonprofit.addSkill(i);
                }
                skills /= 10;
            } // for

            // Similar to needs
            int commitments = Integer.parseInt(nonprofit.getCommitments());
            for (int i = 0; commitments > 0; i++) {
                // Iterate through and add as necessary
                if (commitments % 10 != 0) {
                    curNonprofit.addCommitment(i);
                }
                commitments /= 10;
            } // for

            // Set location
            curNonprofit.setLocation(nonprofit.getLocation());

            // Nonprofit is set, check if it is valid
            if (!errorCheckNonprofit(curNonprofit)) {
                //Don't include bad nonprofit
                continue;
            }

            // Nonprofit is good; clean up strings in case they need it
            curNonprofit.setName(cleanString(curNonprofit.getName()));
            curNonprofit.setWebsite(cleanString(curNonprofit.getWebsite()));
            curNonprofit.setPhone(cleanString(curNonprofit.getPhone()));
            curNonprofit.setEmail(cleanString(curNonprofit.getEmail()));

            // Add valid, clean nonprofit to ArrayList
            nonprofitArrayList.add(curNonprofit);
        } // for each

        // Return all nonprofits in ArrayList
        return nonprofitArrayList;
    } // public static ArrayList<Nonprofit> parseDatabase()

    /**
     * Method that translates Nonprofit object that has been parsed from html form String into a database object
     * that can be saved.
     *
     * Returns object with name==null if nonprofit does not pass error checking
     *
     * @param nonprofit Nonprofit to be translated
     * @return Database object that is ready to be saved
     */
    public static DatabaseNonprofit deparseNonprofit(Nonprofit nonprofit) {
        // Don't save a nonprofit that is not fully filled in
        if (!errorCheckNonprofit(nonprofit)) {
            // Return object with name == null flag set to communicate inadequacy
            return new DatabaseNonprofit();
        }

        // Database object to be filled in
        DatabaseNonprofit databaseNonprofit = new DatabaseNonprofit();

        // Set contact info fields
        databaseNonprofit.setName(nonprofit.getName());
        databaseNonprofit.setWebsite(nonprofit.getWebsite());
        databaseNonprofit.setPhone(nonprofit.getPhone());
        databaseNonprofit.setEmail(nonprofit.getEmail());

        // Translate each need to ten to the power of their index
        // Then add this value to needs as a pseudo-binary number
        int needs = 0;
        for (Integer need : nonprofit.getNeeds()) {
            // "Binary" 1/active bit that represents the needs index
            int activeBit = 1;

            // Multiplies 1 by 10 index number of times to place the 1 correctly
            for (int i = 0; i < need; i++) {
                activeBit *= 10;
            } // for

            // Add this value to total needs
            needs += activeBit;
        } // for
        // Database stores this as a String
        databaseNonprofit.setNeeds(Integer.toString(needs));

        // See above
        long activities = 0;
        for (Integer activity : nonprofit.getActivities()) {
            long activeBit = 1;

            for (int i = 0; i < activity; i++) {
                activeBit *= 10;
            } // for

            activities += activeBit;
        } // for
        databaseNonprofit.setActivities(Long.toString(activities));

        // See needs
        long skills = 0;
        for (Integer skill : nonprofit.getSkills()) {
            long activeBit = 1;

            for (int i = 0; i < skill; i++) {
                activeBit *= 10;
            } // for

            skills += activeBit;
        } // for
        databaseNonprofit.setSkills(Long.toString(skills));

        // See needs
        int commitments = 0;
        for (Integer commitment : nonprofit.getCommitments()) {
            int activeBit = 1;

            for (int i = 0; i < commitment; i++) {
                activeBit *= 10;
            } // for

            commitments += activeBit;
        } // for
        databaseNonprofit.setCommitments(Integer.toString(commitments));

        // Set location
        databaseNonprofit.setLocation(nonprofit.getLocation());

        // Return deparsed database object
        return databaseNonprofit;
    } // public static DatabaseNonprofit deparseNonprofit()
} // public class NonprofitParser