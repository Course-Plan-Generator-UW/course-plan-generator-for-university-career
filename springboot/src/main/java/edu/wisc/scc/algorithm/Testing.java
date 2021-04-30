package edu.wisc.scc.algorithm;

import org.junit.jupiter.api.Test;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.test.api.score.stream.ConstraintVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Testing {
    private static ConstraintVerifier<ScheduleConstraintProvider, Schedule> constraintVerifier
            = ConstraintVerifier.build(new ScheduleConstraintProvider(), Schedule.class, CourseToUserNeedsMapping.class);
    private Schedule schedule;

    // ---------- Score generation tests ------------
    @Test
    public void shouldGiveCorrectScoreForNotMeetingAnyRequirements() {
        Schedule problem = generateSimpleTestScheduleWithNullCourseInMappings();
        testConstraintsPenalty(ScheduleConstraintProvider::majorRequirementNotSatisfied, problem, 3);
    }

    @Test
    public void shouldGiveCorrectScoreForEmptySchedule() {
        testAllConstraints(generateSimpleTestScheduleWithNullCourseInMappings(), HardSoftScore.of(-6, 0));
    }

    @Test
    public void shouldGiveCorrectScoreForRealisticSchedule() {
        testAllConstraints(generateRealLifeTestSchedule(), HardSoftScore.of(0, -6));
    }

    @Test
    public void shouldGiveCorrectScoreForInvalidSolution() {
        Schedule schedule = generateRealLifeScheduleInvalidSolution();
        testAllConstraints(schedule, HardSoftScore.of(-1, 0));
    }

    @Test
    public void shouldGiveCorrectScoreForInoptimalSolution() {
        Schedule schedule = generateRealLifeScheduleInoptimalSolution();
        testAllConstraints(schedule, HardSoftScore.of(0, -3));
    }

    @Test
    public void shouldGiveCorrectScoreForOptimalSolution() {
        Schedule schedule = generateRealLifeScheduleOptimalSolution();
        testAllConstraints(schedule, HardSoftScore.of(0, 0));
    }

     // ---------- Solution generation tests ------------

    @Test
    public void shouldSelectValidSolutionForRealisticSchedule() {
        Schedule problem = generateRealLifeTestSchedule();
        System.out.println(problem);
        Schedule solution = JavaSolver.solve(problem);
        System.out.println(solution.toString());

        assertTrue(ifValidSolutionForRealisticSchedule(solution));
    }

    private boolean ifValidSolutionForRealisticSchedule(Schedule solution) {
        List<Course> computedCourses = solution.getComputedCourses();

        List<String> computedCourseIDs = new ArrayList<>();
        for (Course course: computedCourses) computedCourseIDs.add(course.getCourseID());

        return computedCourseIDs.contains("both req") ||
                (computedCourseIDs.contains("first req") && computedCourseIDs.contains("second req"));
    }

    @Test
    public void shouldSelectOptimalSolutionForRealisticSchedule() {
        Schedule problem = generateRealLifeTestSchedule();
        System.out.println(problem);
        Schedule solution = JavaSolver.solve(problem);
        System.out.println(solution.toString());
        assertEquals("[both req]", solution.toString());
    }

    protected static Schedule generateSimpleTestScheduleWithNullCourseInMappings() {
        List<Course> courses = new ArrayList<>();
        List<UserNeeds> userNeedsList = new ArrayList<>();
        userNeedsList.add(new UserNeeds(3, new Integer[]{1, 2}));

        List<CourseToUserNeedsMapping> courseToUserNeedsMappingList = new ArrayList<>();
        courseToUserNeedsMappingList.add(new CourseToUserNeedsMapping( 1L, null, userNeedsList.get(0)));

        return new Schedule(courses, userNeedsList, courseToUserNeedsMappingList);
    }

    protected static Schedule generateRealLifeTestSchedule() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("first req", 3, new Integer[]{0, 1}));
        courses.add(new Course("second req", 3, new Integer[]{1, 0}));
        courses.add(new Course("both req", 3, new Integer[]{1, 1}));

        List<UserNeeds> userNeedsList = new ArrayList<>();
        userNeedsList.add(new UserNeeds(3, new Integer[]{1, 1}));

        return new Schedule(courses, userNeedsList);
    }

    protected static Schedule generateRealLifeScheduleInvalidSolution() {
        Schedule schedule = generateRealLifeTestSchedule();
        List<Course> courses = schedule.getCourses();
        List<UserNeeds> userNeedsList = schedule.getUserNeedsList();

        List<CourseToUserNeedsMapping> courseToUserNeedsMappingList = new ArrayList<>();
        courseToUserNeedsMappingList.add(new CourseToUserNeedsMapping(1L, courses.get(0), userNeedsList.get(0)));

        return new Schedule(courses, userNeedsList, courseToUserNeedsMappingList);
    }

    protected static Schedule generateRealLifeScheduleInoptimalSolution() {
        Schedule schedule = generateRealLifeTestSchedule();
        List<Course> courses = schedule.getCourses();
        List<UserNeeds> userNeedsList = schedule.getUserNeedsList();

        List<CourseToUserNeedsMapping> courseToUserNeedsMappingList = new ArrayList<>();
        courseToUserNeedsMappingList.add(new CourseToUserNeedsMapping(1L, courses.get(0), userNeedsList.get(0)));
        courseToUserNeedsMappingList.add(new CourseToUserNeedsMapping(2L, courses.get(1), userNeedsList.get(0)));

        return new Schedule(courses, userNeedsList, courseToUserNeedsMappingList);
    }

    protected static Schedule generateRealLifeScheduleOptimalSolution() {
        Schedule schedule = generateRealLifeTestSchedule();
        List<Course> courses = schedule.getCourses();
        List<UserNeeds> userNeedsList = schedule.getUserNeedsList();

        List<CourseToUserNeedsMapping> courseToUserNeedsMappingList = new ArrayList<>();
        courseToUserNeedsMappingList.add(new CourseToUserNeedsMapping(1L, courses.get(2), userNeedsList.get(0)));

        return new Schedule(courses, userNeedsList, courseToUserNeedsMappingList);
    }

    private static void testConstraintsPenalty(BiFunction<ScheduleConstraintProvider, ConstraintFactory, Constraint> constraint, Schedule problem, int penalty) {
        List<CourseToUserNeedsMapping> courseToUserNeedsMappingList = problem.getCourseToUserNeedsMappingList();
        constraintVerifier.verifyThat(constraint)
                .given((Object[]) getArrayFromList(courseToUserNeedsMappingList))
                .penalizesBy(penalty);
    }

    private static void testAllConstraints(Schedule problem, HardSoftScore expectedScore) {
        List<CourseToUserNeedsMapping> courseToUserNeedsMappingList = problem.getCourseToUserNeedsMappingList();
        constraintVerifier.verifyThat()
                .given((Object[]) getArrayFromList(courseToUserNeedsMappingList))
                .scores(expectedScore);
    }

    private static CourseToUserNeedsMapping[] getArrayFromList(List<CourseToUserNeedsMapping> list) {
        return list.toArray(new CourseToUserNeedsMapping[0]);
    }

}
