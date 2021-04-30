package edu.wisc.scc.algorithm;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.*;
import java.util.stream.IntStream;

import static org.optaplanner.core.api.score.stream.ConstraintCollectors.*;

public class ScheduleConstraintProvider implements ConstraintProvider{

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                // Hard conflicts
                majorRequirementNotSatisfied(constraintFactory),
                tooLittleCredit(constraintFactory),
                // Soft conflicts
                minimumPossibleCredits(constraintFactory)
        };
    }

    protected Constraint majorRequirementNotSatisfied(ConstraintFactory constraintFactory) {
        BinaryOperator<Integer[]> addArrayClass = (BinaryOperator<Integer[]>) ScheduleConstraintProvider::addArrays;
        BinaryOperator<Integer[]> subtractArrayClass = (BinaryOperator<Integer[]>) ScheduleConstraintProvider::subtractArrays;

        return constraintFactory.fromUnfiltered(CourseToUserNeedsMapping.class)
                .groupBy(CourseToUserNeedsMapping::getUserNeeds,
                        sum(CourseToUserNeedsMapping::getCourseMajorRequirementsFulfilled, getZeroArray(), addArrayClass, subtractArrayClass))
                .filter(this::ifMajorRequirementsNotSatisfied)
                .penalize("majorRequirementNotSatisfied",
                        HardSoftScore.ONE_HARD,
                        this::majorRequirementNotSatisfiedByAmount);
    }

    private Integer[] getZeroArray() {
        return CourseToUserNeedsMapping.no_major_requirements_fulfilled_array();
    }

    private static Integer[] addArrays(Integer[] array1, Integer[] array2) {
        if (array1.length==0) return array2;
        if (array2.length==0) return array1;

        return applyOn2Arrays(array1, array2, Integer::sum);
    }

    private static Integer[] subtractArrays(Integer[] array1, Integer[] array2) {
        if (array1.length==0 || array2.length==0)
            return array1; // 0 - {1,2,3} = 0 , {1,2,3} - 0 = {1,2,3} (always first array)

        return applyOn2Arrays(array1, array2, (x,y)->x-y);
    }

    private static Integer[] applyOn2Arrays(Integer[] array1, Integer[] array2, IntBinaryOperator operator) {
        Object[] intermediate = IntStream.range(0, array1.length)
                .map(index -> operator.applyAsInt(array1[index], array2[index]))
                .boxed()
                .toArray();
        return Arrays.copyOf(intermediate, intermediate.length, Integer[].class);
    }

    private boolean ifMajorRequirementsNotSatisfied(UserNeeds userNeeds, Integer[] satisfiedMajorRequirements) {
        if (isRequirementsSatisfiedLengthZero(satisfiedMajorRequirements)) {
            return !AreUserNeedsNothing(userNeeds);
        }

        return isFirstArrayGreater(userNeeds.getMajorRequirements(), satisfiedMajorRequirements);
    }

    private boolean isRequirementsSatisfiedLengthZero(Integer[] satisfiedMajorRequirements) {
        return satisfiedMajorRequirements.length == 0;
    }

    private boolean AreUserNeedsNothing(UserNeeds userNeeds) {
        return allZeroes(userNeeds.getMajorRequirements()) || userNeeds.getMajorRequirements().length == 0;
    }

    private boolean allZeroes(Integer[] array) {
        return Arrays.stream(array).allMatch(i -> i == 0);
    }

    public static boolean isFirstArrayGreater(Integer[] first, Integer[] second) {
        return IntStream.range(0, first.length).allMatch(i -> first[i] >= second[i]);
    }

    private int majorRequirementNotSatisfiedByAmount(UserNeeds userNeeds, Integer[] satisfiedMajorRequirements) {
        if (isRequirementsSatisfiedLengthZero(satisfiedMajorRequirements)) {
            if (AreUserNeedsNothing(userNeeds)) return 0;
            else return firstArrayGreaterByHowMuch(userNeeds.getMajorRequirements(), getZeroedIntegerArrayOfLength(userNeeds.getMajorRequirements().length));
        }

        return firstArrayGreaterByHowMuch(userNeeds.getMajorRequirements(), satisfiedMajorRequirements);
    }

    private Integer[] getZeroedIntegerArrayOfLength(int length) {
        return Collections.nCopies(length, 0).toArray(new Integer[0]);
    }

    public static int firstArrayGreaterByHowMuch(Integer[] first, Integer[] second) {
        return Arrays.stream(subtractArrays(first, second)).mapToInt(Integer::intValue).sum();
    }

    protected Constraint tooLittleCredit(ConstraintFactory constraintFactory) {
        return constraintFactory.fromUnfiltered(CourseToUserNeedsMapping.class)
                .groupBy(CourseToUserNeedsMapping::getUserNeeds, sum(CourseToUserNeedsMapping::getCourseCredits))
                .filter((scheduleNeeds, satisfiedCredits) -> scheduleNeeds.getMinCredits() > satisfiedCredits)
                .penalize("tooLittleCredit",
                        HardSoftScore.ONE_HARD,
                        (scheduleNeeds, satisfiedCredits) -> scheduleNeeds.getMinCredits() - satisfiedCredits);
    }

    private Constraint minimumPossibleCredits(ConstraintFactory constraintFactory) {
        return constraintFactory.from(CourseToUserNeedsMapping.class)
                .groupBy(CourseToUserNeedsMapping::getUserNeeds, sum(CourseToUserNeedsMapping::getCourseCredits))
                .filter((scheduleNeeds, satisfiedCredits) -> satisfiedCredits > scheduleNeeds.getMinCredits())
                .penalize("minimumPossibleCredits",
                        HardSoftScore.ONE_SOFT,
                        (scheduleNeeds, satisfiedCredits) -> satisfiedCredits - scheduleNeeds.getMinCredits());
    }


}
