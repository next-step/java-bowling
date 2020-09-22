package bowling.ui.result;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

public final class DisplayBowlingBoard {
    private final List<Display> boards;

    private static final Display empty = new Display() {
        @Override
        public String getName() {
            return "";
        }

        @Override
        public String toResults() {
            return "";
        }
    };

    public DisplayBowlingBoard() {
        this.boards = Arrays.asList(
            new DisplayBowlingHeader(),
            empty
        );
    }

    public DisplayBowlingBoard updateDisplayPlayerBowlingGrade(DisplayPlayerBowlingGrade displayPlayerBowlingGrade){
        boards.set(1, displayPlayerBowlingGrade);
        return this;
    }

    @Override
    public String toString() {
        return boards.stream()
                     .map(Display::toDisplay)
                     .collect(joining("\n"));
    }
}
