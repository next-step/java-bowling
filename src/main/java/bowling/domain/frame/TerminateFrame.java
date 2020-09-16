package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

import bowling.domain.core.RolledResult;
import bowling.ui.result.DisplayRolledResult;

import static bowling.domain.core.Spare.expectSpareAfterBonusBowl;
import static java.util.stream.Collectors.joining;

public final class TerminateFrame implements Frame {
    public static final int MAX_TRY_COUNT_SIZE = 3;
    private final List<RolledResult> rolledResults;
    private int tryCount;

    TerminateFrame() {
        this.rolledResults = new ArrayList<>(MAX_TRY_COUNT_SIZE);
        tryCount = 0;
    }

    @Override
    public void updateRolledResult(RolledResult rolledResult) {
        rolledResult = expectSpareAfterBonusBowl(tryCount, rolledResult);
        if (rolledResult.isCompleteState()) {
            rolledResults.add(rolledResult);
        }
        tryCount += rolledResult.tryCountByTerminateFrame();
    }

    @Override
    public int getScore() {
        return rolledResults.stream()
                            .mapToInt(RolledResult::getRolledResultScore)
                            .sum();
    }

    @Override
    public void updateScore(final RolledResult nextRolledResult) {
    }

    @Override
    public RolledResult getRolledResult() {
        throw new UnsupportedOperationException();
    }

    @Override
    public DisplayRolledResult toDisplayRolledResult() {
        return new DisplayRolledResult(description(), getScore());
    }

    private String description() {
        return rolledResults.stream()
                            .map(RolledResult::description)
                            .collect(joining("|"));
    }

    @Override
    public int increaseNextStepFrame() {
        if (MAX_TRY_COUNT_SIZE <= tryCount) {
            return 1;
        }
        return 0;
    }
}
