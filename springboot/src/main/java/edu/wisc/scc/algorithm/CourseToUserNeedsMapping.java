package edu.wisc.scc.algorithm;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class CourseToUserNeedsMapping {

    private Long id;
    @PlanningVariable(valueRangeProviderRefs = "courseRange", nullable = true)
    private Course course;
    private UserNeeds userNeeds;

    public CourseToUserNeedsMapping() {
    }

    public CourseToUserNeedsMapping(Long id, Course course, UserNeeds userNeeds) {
        this.id = id;
        this.course = course;
        this.userNeeds = userNeeds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public int getCourseLitCredits() {
        if (course==null) return 0;
        return course.getLitCredits();
    }

    public int getCourseCredits() {
        if (course==null) return 0;
        return course.getCredits();
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public UserNeeds getScheduleNeeds() {
        return userNeeds;
    }

    public void setScheduleNeeds(UserNeeds userNeeds) {
        this.userNeeds = userNeeds;
    }


}
