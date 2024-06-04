package com.example.TPXProj.mappers;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class responsible for translating between the indices given by the html form to their actual English values;
 *
 * @author Caleb Warner
 */
public class OutputMapper {
    // *************************************************************************
    // Fields
    // *************************************************************************
    /** HashMap responsible for mapping a need index to its need String. */
    private HashMap<Integer, String> needMap;

    /** HashMap responsible for mapping an activity index to its activity String. */
    private HashMap<Integer, String> activityMap;

    /** HashMap responsible for mapping a skill index to its skill String. */
    private HashMap<Integer, String> skillMap;

    /** HashMap responsible for mapping a commitment index to its commitment String. */
    private HashMap<Integer, String> commitmentMap;

    /** HashMap responsible for mapping a location index to its location String. */
    private HashMap<Integer, String> locationMap;


    // *************************************************************************
    // Constructors
    // *************************************************************************
    /**
     * Constructor responsible for initializing and filling all the HashMaps.
     */
    public OutputMapper() {
        // Instantiate and fill needMap
        needMap = new HashMap<>();
        needMap.put(0, "Volunteering");
        needMap.put(1, "Money");
        needMap.put(2, "Donation of Goods");

        // Instantiate and fill activityMap
        activityMap = new HashMap<>();
        activityMap.put(0, "Administration");
        activityMap.put(1, "Advocacy");
        activityMap.put(2, "Arts");
        activityMap.put(3, "Civic Engagement");
        activityMap.put(4, "Children & Young People");
        activityMap.put(5, "Education");
        activityMap.put(6, "Elderly & Disabled");
        activityMap.put(7, "Environmental");
        activityMap.put(8, "Food");
        activityMap.put(9, "Fundraising");
        activityMap.put(10, "Health");
        activityMap.put(11, "Homeless");
        activityMap.put(12, "Human Services");
        activityMap.put(13, "Research");

        // Instantiate and fill skillMap
        skillMap = new HashMap<>();
        skillMap.put(0, "Communication/Interpersonal");
        skillMap.put(1, "Creativity");
        skillMap.put(2, "Leadership");
        skillMap.put(3, "Mentoring & Training Others");
        skillMap.put(4, "Organization & Planning");
        skillMap.put(5, "Problem-Solving");
        skillMap.put(6, "Relationship Building");
        skillMap.put(7, "Reporting");
        skillMap.put(8, "Teamwork");
        skillMap.put(9, "Time-Management");
        skillMap.put(10, "Timeliness");

        // Instantiate and fill commitmentMap
        commitmentMap = new HashMap<>();
        commitmentMap.put(0, "1-2 Hours");
        commitmentMap.put(1, "3-4 Hours");
        commitmentMap.put(2, "5-6 Hours");
        commitmentMap.put(3, "7-8 Hours");
        commitmentMap.put(4, "9-10 Hours");
        commitmentMap.put(5, "10+ Hours");

        // Instantiate and fill locationMap
        locationMap = new HashMap<>();
        locationMap.put(0, "Central");
        locationMap.put(1, "Northern");
        locationMap.put(2, "Southern");
        locationMap.put(3, "Eastern");
        locationMap.put(4, "Western");
        locationMap.put(5, "Northeastern");
        locationMap.put(6, "Northwestern");
        locationMap.put(7, "Southeastern");
        locationMap.put(8, "Southwestern");
    }


    // *************************************************************************
    // Public Methods
    // *************************************************************************
    /**
     * Take a single need and map it to its String literal.
     *
     * @param need Value to be mapped
     * @return Respective String literal
     */
    public String mapNeed(Integer need) {
        return needMap.get(need);
    } // public String mapNeed()

    /**
     * Take an ArrayList of needs and map each one to its String literal.
     *
     * @param needs ArrayList of needs to be mapped
     * @return ArrayList of respective String literals
     */
    public String mapNeeds(ArrayList<Integer> needs) {
        StringBuilder output = new StringBuilder();

        // For each need, map, and append comma if necessary
        for (int i = 0; i < needs.size(); i++) {
            output.append(needMap.get(needs.get(i)));

            if (i < needs.size() - 1) {
                output.append(", ");
            }
        } // for

        return output.toString();
    } // public String mapNeeds()

    /**
     * Take a single activity and map it to its String literal.
     *
     * @param activity Value to be mapped
     * @return Respective String literal
     */
    public String mapActivity(Integer activity) {
        return activityMap.get(activity);
    } // public String mapActivity()

    /**
     * Take an ArrayList of activities and map each one to its String literal.
     *
     * @param activities ArrayList of activities to be mapped
     * @return ArrayList of respective String literals
     */
    public String mapActivities(ArrayList<Integer> activities) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < activities.size(); i++) {
            output.append(activityMap.get(activities.get(i)));

            if (i < activities.size() - 1) {
                output.append(", ");
            }
        } // for

        return output.toString();
    } // public String mapActivities()

    /**
     * Take a single skill and map it to its String literal.
     *
     * @param skill Value to be mapped
     * @return Respective String literal
     */
    public String mapSkill(Integer skill) {
        return skillMap.get(skill);
    } // public String mapSkill()

    /**
     * Take an ArrayList of skills and map each one to its String literal.
     *
     * @param skills ArrayList of skills to be mapped
     * @return ArrayList of respective String literals
     */
    public String mapSkills(ArrayList<Integer> skills) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < skills.size(); i++) {
            output.append(skillMap.get(skills.get(i)));

            if (i < skills.size() - 1) {
                output.append(", ");
            }
        } // for

        return output.toString();
    } // public String mapSkills()

    /**
     * Take a single commitment and map it to its String literal.
     *
     * @param commitment Value to be mapped
     * @return Respective String literal
     */
    public String mapCommitment(Integer commitment) {
        return commitmentMap.get(commitment);
    } // public String mapCommitment()

    /**
     * Take an ArrayList of commitments and map each one to its String literal.
     *
     * @param commitments ArrayList of commitments to be mapped
     * @return ArrayList of respective String literals
     */
    public String mapCommitments(ArrayList<Integer> commitments) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < commitments.size(); i++) {
            output.append(commitmentMap.get(commitments.get(i)));

            if (i < commitments.size() - 1) {
                output.append(", ");
            }
        } // for

        return output.toString();
    } // public String mapCommitments()

    /**
     * Take a location and map it to its String literal.
     *
     * @param location Value to be mapped
     * @return Respective String literal
     */
    public String mapLocation(Integer location) {
        return locationMap.get(location);
    } // public String mapLocation()
} // public class OutputMapper