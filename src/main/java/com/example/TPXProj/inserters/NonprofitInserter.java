package com.example.TPXProj.inserters;

import com.example.TPXProj.mappers.OutputMapper;
import com.example.TPXProj.models.Nonprofit;
import com.example.TPXProj.readers.FileReader;

import java.util.PriorityQueue;

/**
 * Class responsible for reading a specified html file and inserting the results from the ranking into that file
 *
 * @author Caleb Warner
 */
public class NonprofitInserter {
    // *************************************************************************
    // Constants
    // *************************************************************************
    /** String defining the place holder where name must be inserted. */
    private static final String namePlaceHolder = "[name?]";

    /** String defining the place holder where website must be inserted. */
    private static final String websitePlaceHolder = "[website?]";

    /** String defining the place holder where phone must be inserted. */
    private static final String phonePlaceHolder = "[phone?]";

    /** String defining the place holder where email must be inserted. */
    private static final String emailPlaceHolder = "[email?]";

    /** String defining the place holder where needs must be inserted. */
    private static final String needsPlaceHolder = "[needs?]";

    /** String defining the place holder where activities must be inserted. */
    private static final String activitiesPlaceHolder = "[activities?]";

    /** String defining the place holder where skills must be inserted. */
    private static final String skillsPlaceHolder = "[skills?]";

    /** String defining the place holder where commitments must be inserted. */
    private static final String commitmentsPlaceHolder = "[commitments?]";

    /** String defining the place holder where location must be inserted. */
    private static final String locationPlaceHolder = "[location?]";


    // *************************************************************************
    // Fields
    // *************************************************************************
    /** OutputMapper to be shared by the private methods in the class. */
    private static OutputMapper outputMapper;


    // *************************************************************************
    // Private Methods
    // *************************************************************************
    /**
     * Private helper method serving to insert a single component (ex. name) into fileString by finding placeHolder
     *
     * @param fileString Html file the component must be inserted into
     * @param newValue The component value which should replace placeHolder
     * @param placeHolder The place holder which the method should find and replace
     * @return The new fileString with the component value inserted
     */
    private static String insertComponent(String fileString, String newValue, String placeHolder) {
        int length = placeHolder.length();

        // Iterate over fileString and for each placeHolder, insert newValue instead
        int index;
        while ((index = fileString.indexOf(placeHolder)) != -1) {
            fileString = fileString.substring(0, index) + newValue + fileString.substring(index + length);
        } // while

        return fileString;
    } // private static String insertComponent()

    /**
     * Private helper method serving to insert an entire nonprofit by calling insertComponent() as
     * many times as necessary.
     *
     * @param fileString Html file the nonprofit must be inserted into
     * @param nonprofit The nonprofit that should be inserted
     * @param iteration The rank of the nonprofit so that it is inserted in the correct order
     * @return The new fileString with the nonprofit inserted
     */
    private static String insertNonprofit(String fileString, Nonprofit nonprofit, int iteration) {
        // Get the value of the current placeHolder which depends on the iteration
        String namePlaceHolder = NonprofitInserter.namePlaceHolder.replace('?', (char) (iteration + '1'));
        String websitePlaceHolder = NonprofitInserter.websitePlaceHolder.replace('?', (char) (iteration + '1'));
        String phonePlaceHolder = NonprofitInserter.phonePlaceHolder.replace('?', (char) (iteration + '1'));
        String emailPlaceHolder = NonprofitInserter.emailPlaceHolder.replace('?', (char) (iteration + '1'));
        String needsPlaceHolder = NonprofitInserter.needsPlaceHolder.replace('?', (char) (iteration + '1'));
        String activitiesPlaceHolder = NonprofitInserter.activitiesPlaceHolder.replace('?', (char) (iteration + '1'));
        String skillsPlaceHolder = NonprofitInserter.skillsPlaceHolder.replace('?', (char) (iteration + '1'));
        String commitmentsPlaceHolder = NonprofitInserter.commitmentsPlaceHolder.replace('?', (char) (iteration + '1'));
        String locationPlaceHolder = NonprofitInserter.locationPlaceHolder.replace('?', (char) (iteration + '1'));

        // Call insertComponent with the current place holder and the respective component
        fileString = insertComponent(fileString, nonprofit.getName(), namePlaceHolder);
        fileString = insertComponent(fileString, nonprofit.getWebsite(), websitePlaceHolder);
        fileString = insertComponent(fileString, nonprofit.getPhone(), phonePlaceHolder);
        fileString = insertComponent(fileString, nonprofit.getEmail(), emailPlaceHolder);
        fileString = insertComponent(fileString, outputMapper.mapNeeds(nonprofit.getNeeds()), needsPlaceHolder);
        fileString = insertComponent(fileString, outputMapper.mapActivities(nonprofit.getActivities()), activitiesPlaceHolder);
        fileString = insertComponent(fileString, outputMapper.mapSkills(nonprofit.getSkills()), skillsPlaceHolder);
        fileString = insertComponent(fileString, outputMapper.mapCommitments(nonprofit.getCommitments()), commitmentsPlaceHolder);
        fileString = insertComponent(fileString, outputMapper.mapLocation(nonprofit.getLocation()), locationPlaceHolder);

        return fileString;
    } // private static String insertNonprofit()


    // *************************************************************************
    // Public Methods
    // *************************************************************************
    /**
     * Run the inserting process by calling insertNonprofit n times (4 in this case).
     * Each iteration inserts the nth ranked nonprofit
     *
     * @param nonprofitsRanked Max heap priority queue of nonprofits
     * @param filePath File to read the html file from
     * @return The String of the file with all nonprofits inserted
     */
    public static String run(PriorityQueue<Nonprofit> nonprofitsRanked, String filePath) {
        // Utilize FileReader to read the file
        String fileString = FileReader.readFile(filePath);

        // Instantiate outputMapper for helper methods
        outputMapper = new OutputMapper();

        // Iterate through nonprofitsRanked and insert the top 4
        for (int i = 0; i < 4; i++) {
            if (nonprofitsRanked.size() == 0) {
                break;
            }

            Nonprofit curNonprofit = nonprofitsRanked.remove();

            fileString = insertNonprofit(fileString, curNonprofit, i);
        } // for

        return fileString;
    } // public static String run()
} // public class NonprofitInserter
