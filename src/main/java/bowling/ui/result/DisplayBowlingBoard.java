package bowling.ui.result;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public final class DisplayBowlingBoard {
    private final List<Display> boards;

    public DisplayBowlingBoard(DisplayPlayerBowlingGrade displayPlayerBowlingGrade ) {
        this.boards = new ArrayList<>();
        this.boards.add(new DisplayBowlingHeader());
        this.boards.add(displayPlayerBowlingGrade);
    }

    @Override
    public String toString() {
        return boards.stream()
                     .map(Display::toDisplay)
                     .collect(joining("\n"));
    }
}
