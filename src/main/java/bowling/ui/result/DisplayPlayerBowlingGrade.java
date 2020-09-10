package bowling.ui.result;

import java.util.List;

import static java.util.stream.Collectors.joining;

public final class DisplayPlayerBowlingGrade implements Display {
    private final String playerName;
    private final List<String> playerBowlingGrades;

    public DisplayPlayerBowlingGrade(String playerName, List<String> playerBowlingGrades) {
        this.playerName = playerName;
        this.playerBowlingGrades = playerBowlingGrades;
    }

    @Override
    public String toString() {
        return toDisplay();
    }

    @Override
    public String getName() {
        return playerName;
    }

    @Override
    public String toResults() {
        return playerBowlingGrades.stream()
                                  .map(this::toResult)
                                  .collect(joining("|"));
    }
}
