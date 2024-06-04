package com.example.TPXProj.models;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Object representation of a volunteer.
 *
 * @author Caleb Warner
 */
public class Volunteer {
    // *************************************************************************
    // Fields
    // *************************************************************************
    /** contribution name for volunteer. */
    private Integer contribution;

    /** ArrayList of activities for volunteer. */
    private ArrayList<Integer> activities;

    /** ArrayList of skills for volunteer. */
    private ArrayList<Integer> skills;

    /** Integer representing availability for volunteer. */
    private Integer availability;

    /** Integer representing location for volunteer. */
    private Integer location;


    // *************************************************************************
    // Constructors
    // *************************************************************************
    /**
     * Basic constructor used if new, empty Volunteer is needed.
     */
    public Volunteer() {
        // Set Integers to null and instantiate ArrayLists
        this.contribution = null;
        this.activities = new ArrayList<>();
        this.skills = new ArrayList<>();
        this.availability = null;
        this.location = null;
    } // public Volunteer()

    /**
     * Constructor used if a Volunteer needs to be created out of another volunteer.
     *
     * @param volunteer Volunteer to be copied
     */
    public Volunteer(Volunteer volunteer) {
        // Set all fields to the given value
        this.contribution = volunteer.contribution;
        this.activities = volunteer.activities;
        this.skills = volunteer.skills;
        this.availability = volunteer.availability;
        this.location = volunteer.location;
    } // public Volunteer()


    // *************************************************************************
    // Public Methods
    // *************************************************************************
    /**
     * Getter for contribution field
     *
     * @return contribution stored in field
     */
    public Integer getContribution() {
        return contribution;
    } // public Integer getContribution()

    /**
     * Setter for contribution field
     *
     * @param contribution value to be assigned
     */
    public void setContribution(Integer contribution) {
        this.contribution = contribution;
    } // public void setContribution()

    /**
     * Getter for activities field
     *
     * @return activities ArrayList stored in field
     */
    public ArrayList<Integer> getActivities() {
        return activities;
    } // public ArrayList<Integer> getActivities()

    /**
     * Setter for activities field
     *
     * @param activities ArrayList value to be assigned
     */
    public void setActivities(ArrayList<Integer> activities) {
        this.activities = activities;
    } // public void setActivities()

    /**
     * Method to add an integer to the activities ArrayList
     *
     * @param activity Integer to be added
     */
    public void addActivity(Integer activity) {
        this.activities.add(activity);
    } // public void addActivity()

    /**
     * Getter for skills field
     *
     * @return skills ArrayList stored in field
     */
    public ArrayList<Integer> getSkills() {
        return skills;
    } // public ArrayList<Integer> getSkills()

    /**
     * Setter for skills field
     *
     * @param skills ArrayList value to be assigned
     */
    public void setSkills(ArrayList<Integer> skills) {
        this.skills = skills;
    } // public void setSkills()

    /**
     * Method to add an integer to the skills ArrayList
     *
     * @param skill Integer to be added
     */
    public void addSkill(Integer skill) {
        this.skills.add(skill);
    } // public void addSkill()

    /**
     * Getter for availability field
     *
     * @return availability stored in field
     */
    public Integer getAvailability() {
        return availability;
    } // public Integer getAvailability()

    /**
     * Setter for availability field
     *
     * @param availability value to be assigned
     */
    public void setAvailability(Integer availability) {
        this.availability = availability;
    } // public void setAvailability()

    /**
     * Getter for location field
     *
     * @return location stored in field
     */
    public Integer getLocation() {
        return location;
    } // public Integer getLocation()

    /**
     * Setter for location field
     *
     * @param location value to be assigned
     */
    public void setLocation(Integer location) {
        this.location = location;
    } // public void setLocation()

    /**
     * Method to print out a volunteer for testing purposes
     *
     * @return String with all info of a volunteer separated by "<br>"
     */
    public String printVolunteer() {
        return  "Contribution: " + this.contribution + "<br>" +
                "Activities: " + Arrays.toString(activities.toArray()) + "<br>" +
                "Skills: " + Arrays.toString(skills.toArray()) + "<br>" +
                "Availability: " + this.availability + "<br>" +
                "Location: " + this.location;
    } // public String printVolunteer()
} // public class Volunteer
