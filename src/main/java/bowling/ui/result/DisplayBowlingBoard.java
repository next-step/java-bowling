package bowling.ui.result;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public final class DisplayBowlingBoard {
    private final Map<String, Display> boards;

    private static final List<DisplayRolledResult> empty = Stream.generate(()-> new DisplayRolledResult("", 0))
                                                                 .limit(10)
                                                                 .collect(toList());

    public DisplayBowlingBoard(List<String> playerNames) {
        boards = new LinkedHashMap<>();
        boards.put("header", new DisplayBowlingHeader());
        for (String playerName : playerNames) {
            boards.put(playerName, new DisplayPlayerBowlingGrade(playerName, empty));
        }
    }

    public DisplayBowlingBoard updateDisplayPlayerBowlingGrade(String playerName, DisplayPlayerBowlingGrade displayPlayerBowlingGrade){
        boards.put(playerName, displayPlayerBowlingGrade);
        return this;
    }

    @Override
    public String toString() {
        return boards.values().stream()
                     .map(Display::toDisplay)
                     .collect(joining("\n"));
    }
}
