package edu.wisc.scc.algorithm;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class ScheduleWebController {

    @Autowired
    private static SolverManager<Schedule, UUID> solverManager;

    @PostMapping("/solve")
    public static Schedule solve(Schedule problem) {
        UUID problemId = UUID.randomUUID();
        // Submit the problem to start solving
        SolverJob<Schedule, UUID> solverJob = solverManager.solve(problemId, problem);
        Schedule solution;
        try {
            // Wait until the solving ends
            solution = solverJob.getFinalBestSolution();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException("Solving failed.", e);
        }
        return solution;
    }

}