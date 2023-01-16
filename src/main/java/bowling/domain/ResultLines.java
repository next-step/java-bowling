package bowling.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultLines {

    public static final String NAME_MESSAGE = " NAME ";
    public static final String BLANK_MESSAGE = "";
    public static final String NUMBER_FORMAT = "%02d";
    public static final int RESULT_MAX_SIZE = Frames.MAX_FRAMENUMBER + 1;

    static final List<String> FRAME_NUMBERS;
    private final LinkedList<String> frameResults;
    private final LinkedList<String> frameScores;

    static {
        FRAME_NUMBERS = new ArrayList<>();
        FRAME_NUMBERS.add(NAME_MESSAGE);
        FRAME_NUMBERS.addAll(IntStream.rangeClosed(Frames.MIN_FRAMENUMBER, Frames.MAX_FRAMENUMBER)
                .mapToObj(s -> String.format(NUMBER_FORMAT, s))
                .map(String::valueOf)
                .collect(Collectors.toList()));
    }

    public ResultLines(Player player, List<Frame> frames) {
        frameResults = createFrameResults(player, frames);
        frameScores = createFrameScores(frames);
    }

    private LinkedList<String> createFrameScores(List<Frame> frames) {
        LinkedList<String> frameScores = frames.stream()
                .map(Frame::calculateScore)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(String::valueOf)
                .collect(Collectors.toCollection(LinkedList::new));
        frameScores.addFirst(BLANK_MESSAGE);

        return makeResultsSizeMax(frameScores, frameScores.size());
    }

    private LinkedList<String> createFrameResults(Player player, List<Frame> frames) {
        LinkedList<String> frameResults = frames.stream()
                .map(Object::toString)
                .collect(Collectors.toCollection(LinkedList::new));
        frameResults.addFirst(player.name());

        return makeResultsSizeMax(frameResults, frameResults.size());
    }

    private LinkedList<String> makeResultsSizeMax(LinkedList<String> results, int currentResultsSize) {
        for (int i = 0; i < (RESULT_MAX_SIZE - currentResultsSize); i++) {
            results.addLast(BLANK_MESSAGE);
        }
        return results;
    }

    public List<String> frameNumbers() {
        return Collections.unmodifiableList(FRAME_NUMBERS);
    }

    public List<String> frameResults() {
        return Collections.unmodifiableList(frameResults);
    }

    public List<String> frameScores() {
        return Collections.unmodifiableList(frameScores);
    }
}
