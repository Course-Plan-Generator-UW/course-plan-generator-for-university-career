package edu.wisc.scc.algorithm;

public class UserNeeds {

    private int minCredits; // lets say 15
    private int litCredits; // lets say 12

    public UserNeeds(int minCredits, int litCredits) {
        this.minCredits = minCredits;
        this.litCredits = litCredits;
    }

    @Override
    public String toString() {
        return "min: " + minCredits + " lit: " + litCredits;
    }

    public int getMinCredits() {
        return minCredits;
    }

    public int getLitCredits() {
        return litCredits;
    }
}
