package bowling.view;

import bowling.model.Player;
import bowling.model.frame.FinalFrame;
import bowling.model.frame.Frame;
import bowling.model.frame.Frames;
import bowling.model.state.Ready;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static final String NAME = "NAME";
    public static final int MIN_FRAME_NUMBER = 1;
    public static final int MAX_FRAME_NUMBER = 10;
    public static final String ZERO = "0";
    public static final String GUTTER = "-";
    public static final String STATE_DELIMITER = "|";
    public static final String FRAME_DELIMITER = " | ";
    public static final String PREFIX = "| ";
    public static final String SUFFIX = " |";
    public static final String BLANK = " ";

    private OutputView() {
    }

    public static void printBowlResult(Player player, Frames frames) {
        printTitle();
        printResult(player.getName(), frames.getFrames());
        System.out.println();
    }

    private static void printTitle() {
        List<String> titles = new ArrayList<>();

        titles.add(NAME);
        addTitleNumber(titles);

        System.out.println(frameFormat(titles));
    }

    private static void addTitleNumber(List<String> titles) {
        for (int i = MIN_FRAME_NUMBER; i <= MAX_FRAME_NUMBER; i++) {
            titles.add(String.format(" %02d ", i));
        }
    }

    private static void printResult(String name, List<Frame> frames) {
        List<String> result = new ArrayList<>();

        result.add(String.format("%4s", name));
        addStates(frames, result);
        addEmptyStates(frames, result);

        System.out.println(frameFormat(result).replaceAll(ZERO, GUTTER));
    }

    private static void addStates(List<Frame> frames, List<String> result) {
        for (int i = MIN_FRAME_NUMBER - 1; i < frames.size(); i++) {
            addState(frames.get(i), result);
        }
    }

    private static void addState(Frame frame, List<String> result) {
        if (frame instanceof FinalFrame) {
            List<String> states = ((FinalFrame) frame).getStates()
                    .stream()
                    .filter(state -> !(state instanceof Ready))
                    .map(Object::toString).collect(Collectors.toList());
            result.add(String.format("%-4s", String.join(STATE_DELIMITER, states)));
            return;
        }
        result.add(String.format(" %-3s", frame.getState().toString()));
    }

    private static void addEmptyStates(List<Frame> frames, List<String> result) {
        for (int i = frames.size(); i < MAX_FRAME_NUMBER; i++) {
            result.add(String.format("%4s", BLANK));
        }
    }

    private static String frameFormat(List<String> values) {
        return values.stream()
                .collect(Collectors.joining(FRAME_DELIMITER, PREFIX, SUFFIX));
    }
}
