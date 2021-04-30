package edu.wisc.scc.algorithm;

public class UserNeeds {

    private int minCredits; // e.g 120
    private Integer[] major_requirements; // e.g [3, 4, 2..] courses needed for each section

    public UserNeeds(int minCredits, Integer[] major_requirements) {
        this.minCredits = minCredits;
        this.major_requirements = major_requirements;
    }

    @Override
    public String toString() {
        return "minCredits: " + minCredits + " major_requirements: " + major_requirements;
    }

    public int getMinCredits() {
        return minCredits;
    }

    public Integer[] getMajorRequirements() {
        return major_requirements;
    }
}
