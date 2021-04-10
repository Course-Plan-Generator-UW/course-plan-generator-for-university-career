package edu.wisc.scc.algorithm;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import static org.optaplanner.core.api.score.stream.ConstraintCollectors.*;

public class ScheduleConstraintProvider implements ConstraintProvider{

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                // Hard conflicts
                tooLittleLitCredit(constraintFactory),
                tooLittleCredit(constraintFactory),
                minimumPossibleCredits(constraintFactory)
        };
    }

    protected Constraint tooLittleLitCredit(ConstraintFactory constraintFactory) {
        return constraintFactory.fromUnfiltered(CourseToUserNeedsMapping.class)
                .groupBy(CourseToUserNeedsMapping::getScheduleNeeds, sum(CourseToUserNeedsMapping::getCourseLitCredits))
                .filter((scheduleNeeds, satisfiedLitCredits) -> scheduleNeeds.getLitCredits() > satisfiedLitCredits)
                .penalize("tooLittleLitCredit",
                        HardSoftScore.ONE_HARD,
                        (scheduleNeeds, satisfiedLitCredits) -> scheduleNeeds.getLitCredits() - satisfiedLitCredits);
    }

    protected Constraint tooLittleCredit(ConstraintFactory constraintFactory) {
        return constraintFactory.fromUnfiltered(CourseToUserNeedsMapping.class)
                .groupBy(CourseToUserNeedsMapping::getScheduleNeeds, sum(CourseToUserNeedsMapping::getCourseCredits))
                .filter((scheduleNeeds, satisfiedCredits) -> scheduleNeeds.getMinCredits() > satisfiedCredits)
                .penalize("tooLittleCredit",
                        HardSoftScore.ONE_HARD,
                        (scheduleNeeds, satisfiedCredits) -> scheduleNeeds.getMinCredits() - satisfiedCredits);
    }

    private Constraint minimumPossibleCredits(ConstraintFactory constraintFactory) {
        return constraintFactory.from(CourseToUserNeedsMapping.class)
                .groupBy(CourseToUserNeedsMapping::getScheduleNeeds, sum(CourseToUserNeedsMapping::getCourseCredits))
                .filter((scheduleNeeds, satisfiedCredits) -> satisfiedCredits > scheduleNeeds.getMinCredits())
                .penalize("minimumPossibleCredits",
                        HardSoftScore.ONE_SOFT,
                        (scheduleNeeds, satisfiedCredits) -> satisfiedCredits - scheduleNeeds.getMinCredits());
    }


}
