package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultLines {

    public static final String NAME_MESSAGE = " NAME ";
    public static final String BLANK_MESSAGE = "";
    public static final String NUMBER_FORMAT = "%02d";

    static final List<String> FIRST_LINE;
    private final LinkedList<String> secondLine;

    static {
        FIRST_LINE = new ArrayList<>();
        FIRST_LINE.add(NAME_MESSAGE);
        FIRST_LINE.addAll(IntStream.rangeClosed(Frames.MIN_FRAMENUMBER, Frames.MAX_FRAMENUMBER)
                .mapToObj(s -> String.format(NUMBER_FORMAT, s))
                .map(String::valueOf)
                .collect(Collectors.toList()));
    }

    public ResultLines(Player player, List<Frame> frameResults) {
        secondLine = frameResults.stream()
                .map(Object::toString)
                .collect(Collectors.toCollection(LinkedList::new));

        makeResultsMaxSize(frameResults);
        secondLine.addFirst(player.name());
    }

    private void makeResultsMaxSize(List<Frame> frameResults) {
        int frameResultsSize = frameResults.size();
        for (int i = 0; i < (Frames.MAX_FRAMENUMBER - frameResultsSize); i++) {
            secondLine.addLast(BLANK_MESSAGE);
        }
    }

    public List<String> firstLine() {
        return Collections.unmodifiableList(FIRST_LINE);
    }

    public List<String> secondLine() {
        return Collections.unmodifiableList(secondLine);
    }
}
