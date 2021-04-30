package edu.wisc.scc.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class Schedule {

    @ValueRangeProvider(id= "courseRange")
    @ProblemFactCollectionProperty
    private List<Course> courses;

    @ProblemFactCollectionProperty
    private List<UserNeeds> userNeedsList;

    @PlanningEntityCollectionProperty
    private List<CourseToUserNeedsMapping> courseToUserNeedsMappingList;

    @PlanningScore
    private HardSoftScore score;

    public Schedule(){
    }

    // Note that this method assumes there's only one userNeeds object
    public Schedule(List<Course> courses, List<UserNeeds> userNeedsList) {
        this(courses, userNeedsList, new ArrayList<>());
        for (int i = 0; i < courses.size(); i++) {
            this.courseToUserNeedsMappingList.add(new CourseToUserNeedsMapping((long) i, courses.get(i), userNeedsList.get(0)));
        }
    }

    public Schedule(List<Course> courses, List<UserNeeds> userNeedsList, List<CourseToUserNeedsMapping> courseToUserNeedsMappingList){
        this.courses = courses;
        this.userNeedsList = userNeedsList;
        this.courseToUserNeedsMappingList = courseToUserNeedsMappingList;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<UserNeeds> getUserNeedsList() {
        return userNeedsList;
    }

    public List<CourseToUserNeedsMapping> getCourseToUserNeedsMappingList() {
        return courseToUserNeedsMappingList;
    }

    public HardSoftScore getScore() {
        return score;
    }

    @Override
    public String toString() {
        return getComputedCourses().toString();
    }

    public List<Course> getComputedCourses() {
        List<Course> courses = new ArrayList<>();
        for (CourseToUserNeedsMapping courseToUserNeedsMapping: courseToUserNeedsMappingList) {
            if (courseToUserNeedsMapping.getCourse()!=null) {
                courses.add(courseToUserNeedsMapping.getCourse());
            }
        }
        return courses;
    }

}
