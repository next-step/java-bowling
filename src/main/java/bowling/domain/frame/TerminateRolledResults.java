package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

import bowling.domain.core.RolledResult;
import bowling.domain.core.state.Score;

import static bowling.domain.frame.TerminateFrame.MAX_TRY_COUNT_SIZE;
import static java.util.stream.Collectors.joining;

final class TerminateRolledResults {
    private final List<RolledResult> rolledResults;
    private int tryCount;

    TerminateRolledResults() {
        this.rolledResults = new ArrayList<>(MAX_TRY_COUNT_SIZE);
        tryCount = 0;
    }

    void add(RolledResult nextRolledResult){
        if (nextRolledResult.isCompleteState()) {
            this.rolledResults.add(nextRolledResult);
            this.tryCount += nextRolledResult.tryCountByTerminateFrame();
        }
    }

    String description() {
        return rolledResults.stream()
                            .map(RolledResult::description)
                            .collect(joining("|"));
    }

    boolean isNotEmpty(){
        return !rolledResults.isEmpty();
    }

    int increaseNextStepFrame() {
        if (MAX_TRY_COUNT_SIZE <= tryCount) {
            return 1;
        }
        return 0;
    }

    int getScore(){
        return rolledResults.stream()
                            .map(RolledResult::getScore)
                            .mapToInt(Score::getScore)
                     .sum();
    }

    Score calculateScore(Score score) {
        for (RolledResult rolledResult : rolledResults) {
            score = rolledResult.calculateScore(score);
        }
        return score;
    }
}
