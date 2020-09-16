package bowling.ui.result;

import java.util.List;

import static java.util.stream.Collectors.joining;

public final class DisplayPlayerBowlingGrade implements Display {
    private final String playerName;
    private final List<DisplayRolledResult> playerBowlingGrades;

    public DisplayPlayerBowlingGrade(String playerName, List<DisplayRolledResult> playerBowlingGrades) {
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
        return String.format("%s|\n|        |%s", description(), scores());
    }

    private String description() {
        return playerBowlingGrades.stream()
                                  .map(DisplayRolledResult::getDescription)
                                  .map(this::toResult)
                                  .collect(joining("|"));
    }

    private String scores() {
        return playerBowlingGrades.stream()
                                  .map(DisplayRolledResult::getScore)
                                  .map(this::toResult)
                                  .collect(joining("|"));
    }
}
