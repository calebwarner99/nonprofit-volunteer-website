package com.example.TPXProj.models;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Object representation of a nonprofit for manipulation and processing within the program.
 *
 * @author Caleb Warner
 */
public class Nonprofit {
    // *************************************************************************
    // Fields
    // *************************************************************************
    /** String name for nonprofit. */
    private String name;

    /** String website for nonprofit. */
    private String website;

    /** String phone for nonprofit. */
    private String phone;

    /** String email for nonprofit. */
    private String email;

    /** ArrayList of needs for nonprofit. */
    private ArrayList<Integer> needs;

    /** ArrayList of activities for nonprofit. */
    private ArrayList<Integer> activities;

    /** ArrayList of skills for nonprofit. */
    private ArrayList<Integer> skills;

    /** ArrayList of commitments for nonprofit. */
    private ArrayList<Integer> commitments;

    /** Integer representing the location of the nonprofit. */
    private Integer location;

    /** Integer containing the score the nonprofit has with the associated volunteer. */
    private Integer score;


    // *************************************************************************
    // Constructors
    // *************************************************************************
    /**
     * Basic constructor used if new, empty Nonprofit is needed.
     */
    public Nonprofit() {
        // Set all contact info fields to null
        this.name = null;
        this.website = null;
        this.phone = null;
        this.email = null;

        // Set all ArrayLists to new, empty ArrayLists
        this.needs = new ArrayList<>();
        this.activities = new ArrayList<>();
        this.skills = new ArrayList<>();
        this.commitments = new ArrayList<>();

        // Set Integers to null
        this.location = null;
        this.score = null;
    } // public Nonprofit()

    /**
     * Constructor used if a Nonprofit needs to be created out of another nonprofit.
     *
     * @param nonprofit Nonprofit to be copied
     */
    public Nonprofit(Nonprofit nonprofit) {
        // Set all fields to the given value
        this.name = nonprofit.name;
        this.website = nonprofit.website;
        this.phone = nonprofit.phone;
        this.email = nonprofit.email;
        this.needs = nonprofit.needs;
        this.activities = nonprofit.activities;
        this.skills = nonprofit.skills;
        this.commitments = nonprofit.commitments;
        this.location = nonprofit.location;

        // Set score to null; don't know what it is
        this.score = null;
    } // public Nonprofit()


    // *************************************************************************
    // Public Methods
    // *************************************************************************
    /**
     * Getter for name field
     *
     * @return name stored in field
     */
    public String getName() {
        return name;
    } // public String getName()

    /**
     * Setter for name field
     *
     * @param name value to be assigned
     */
    public void setName(String name) {
        this.name = name;
    } // public void setName()

    /**
     * Getter for website field
     *
     * @return website stored in field
     */
    public String getWebsite() {
        return website;
    } // public String getWebsite()

    /**
     * Setter for website field
     *
     * @param website value to be assigned
     */
    public void setWebsite(String website) {
        this.website = website;
    } // public void setWebsite()

    /**
     * Getter for phone field
     *
     * @return phone stored in field
     */
    public String getPhone() {
        return phone;
    } // public String getPhone()

    /**
     * Setter for phone field
     *
     * @param phone value to be assigned
     */
    public void setPhone(String phone) {
        this.phone = phone;
    } // public void setPhone()

    /**
     * Getter for email field
     *
     * @return email stored in field
     */
    public String getEmail() {
        return email;
    } // public String getEmail()

    /**
     * Setter for email field
     *
     * @param email value to be assigned
     */
    public void setEmail(String email) {
        this.email = email;
    } // public void setEmail()

    /**
     * Getter for needs field
     *
     * @return needs ArrayList stored in field
     */
    public ArrayList<Integer> getNeeds() {
        return needs;
    } // public ArrayList<Integer> getNeeds()

    /**
     * Setter for needs field
     *
     * @param needs ArrayList value to be assigned
     */
    public void setNeeds(ArrayList<Integer> needs) {
        this.needs = needs;
    } // public void setNeeds()

    /**
     * Method to add an integer to the needs ArrayList
     *
     * @param need Integer to be added
     */
    public void addNeed(Integer need) {
        this.needs.add(need);
    } // public void addNeed()

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
     * Getter for commitments field
     *
     * @return commitments ArrayList stored in field
     */
    public ArrayList<Integer> getCommitments() {
        return commitments;
    } // public ArrayList<Integer> getCommitments()

    /**
     * Setter for commitments field
     *
     * @param commitments ArrayList value to be assigned
     */
    public void setCommitments(ArrayList<Integer> commitments) {
        this.commitments = commitments;
    } // public void setCommitments()

    /**
     * Method to add an integer to the commitments ArrayList
     *
     * @param commitment Integer to be added
     */
    public void addCommitment(Integer commitment) {
        this.commitments.add(commitment);
    } // public void addCommitment()

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
     * Getter for score field
     *
     * @return score stored in field
     */
    public Integer getScore() {
        return score;
    } // public Integer getScore()

    /**
     * Setter for score field
     *
     * @param score value to be assigned
     */
    public void setScore(Integer score) {
        this.score = score;
    } // public void setScore()

    /**
     * Method to print out a nonprofit for testing purposes
     *
     * @return String with all info of a nonprofit separated by "<br>"
     */
    public String printNonprofit() {
        return  "Name: " + this.name + "<br>" +
                "Website: " + this.website + "<br>" +
                "Phone: " + this.phone + "<br>" +
                "Email: " + this.email + "<br>" +
                "Needs: " + Arrays.toString(needs.toArray()) + "<br>" +
                "Activities: " + Arrays.toString(activities.toArray()) + "<br>" +
                "Skills: " + Arrays.toString(skills.toArray()) + "<br>" +
                "Commitments: " + Arrays.toString(commitments.toArray()) + "<br>" +
                "Location: " + this.location;
    } // public String printNonprofit()
} // public class Nonprofit