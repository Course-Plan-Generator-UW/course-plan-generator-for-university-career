package edu.wisc.scc.algorithm;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;

import java.time.Duration;

// Attempt to run in plain-java
public class JavaSolver {

    public static Schedule solve(Schedule problem) {
        // Build the Solver
        SolverFactory<Schedule> solverFactory = SolverFactory.create(new SolverConfig()
                .withSolutionClass(Schedule.class)
                .withEntityClasses(CourseToUserNeedsMapping.class)
                .withConstraintProviderClass(ScheduleConstraintProvider.class)
                .withTerminationSpentLimit(Duration.ofSeconds(5)));;
        Solver<Schedule> solver = solverFactory.buildSolver();

        // Solve the problem
        return solver.solve(problem);
    }


}