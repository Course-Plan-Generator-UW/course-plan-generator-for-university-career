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

public class Testing {
    private static ConstraintVerifier<ScheduleConstraintProvider, Schedule> constraintVerifier
            = ConstraintVerifier.build(new ScheduleConstraintProvider(), Schedule.class, CourseToUserNeedsMapping.class);

    // ---------- Score generation tests ------------
    @Test
    public void shouldGiveCorrectScoreForNotMeetingLitCredits() {
        Schedule problem = generateSimpleTestScheduleWithNullCourseInMappings();
        testConstraintsPenalty(ScheduleConstraintProvider::tooLittleLitCredit, problem, 12);
    }

    @Test
    public void shouldGiveCorrectScoreForEmptySchedule() {
        testAllConstraints(generateSimpleTestScheduleWithNullCourseInMappings(), HardSoftScore.of(-15, 0));
    }

    // ---------- Solution generation tests ------------
    @Test
    public void shouldSelectOnlyOneNeededCourseOutOfThree() {
        Schedule problem = generateRealLifeTestSchedule();
        Schedule solution = JavaSolver.solve(problem);
        assertEquals("[12-lit]", solution.toString());
    }

    protected static Schedule generateSimpleTestScheduleWithNullCourseInMappings() {
        List<Course> courses = new ArrayList<>();
        List<UserNeeds> userNeedsList = new ArrayList<>();
        userNeedsList.add(new UserNeeds(3, 12));

        List<CourseToUserNeedsMapping> courseToUserNeedsMappingList = new ArrayList<>();
        courseToUserNeedsMappingList.add(new CourseToUserNeedsMapping( 1L, null, userNeedsList.get(0)));

        return new Schedule(courses, userNeedsList, courseToUserNeedsMappingList);
    }

    protected static Schedule generateRealLifeTestSchedule() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("7-lit", 3, 7));
        courses.add(new Course("5-lit", 3, 5));
        courses.add(new Course("12-lit", 3, 12));

        List<UserNeeds> userNeedsList = new ArrayList<>();
        userNeedsList.add(new UserNeeds(3, 12));

        List<CourseToUserNeedsMapping> courseToUserNeedsMappingList = new ArrayList<>();
        for (int i = 0; i < courses.size(); i++) {
            courseToUserNeedsMappingList.add(new CourseToUserNeedsMapping((long) i, courses.get(i), userNeedsList.get(0)));
        }

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
        return list.toArray(new CourseToUserNeedsMapping[list.size()]);
    }

}
