package edu.wisc.scc.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private String courseID;
    private int credits;
    private int litCredits;

    public Course(String courseID, int credits, int litCredits) {
        this.courseID = courseID;
        this.credits = credits;
        this.litCredits = litCredits;
    }

    public String getCourseID() {
        return courseID;
    }

    public int getCredits() {
        return credits;
    }

    public int getLitCredits() {
        return litCredits;
    }

    @Override
    public String toString() {
        return courseID;
    }

}
