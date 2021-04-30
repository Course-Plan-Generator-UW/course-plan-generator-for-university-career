package edu.wisc.scc.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private String courseID;
    private int credits;
    private Integer[] major_reqs_satisfied; // = {0, 1, 0..}

    public Course(String courseID, int credits, Integer[] major_reqs_satisfied) {
        this.courseID = courseID;
        this.credits = credits;
        this.major_reqs_satisfied = major_reqs_satisfied;
    }

    public String getCourseID() {
        return courseID;
    }

    public int getCredits() {
        return credits;
    }

    public Integer[] getMajor_reqs_satisfied() {
        return major_reqs_satisfied;
    }

    @Override
    public String toString() {
        return courseID;
    }

}
