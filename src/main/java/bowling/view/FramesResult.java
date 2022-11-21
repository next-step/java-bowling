package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FramesResult {

    private static final int NO_SCORE = -1;
    private static final int MAX_FRAME_NUMBER = 10;
    private static final String FRAME_NUMBER_LINE;
    private static final String FRAME_SIGN_BAR = " %3s  |".repeat(MAX_FRAME_NUMBER - 1) + " %-5s|";
    private static final String FRAME_SCORE_BAR = "|      |" + " %3s  |".repeat(MAX_FRAME_NUMBER);

    private Frames frames;
    private final FrameResult frameResult = new FrameResult();

    static {
        FRAME_NUMBER_LINE = "| NAME |" + IntStream.rangeClosed(1, MAX_FRAME_NUMBER)
                .mapToObj(i -> String.format("  %02d  |", i))
                .collect(Collectors.joining());
    }

    private FramesResult() {}

    public FramesResult(final Frames frames) {

        this.frames = frames;
    }

    public static FramesResult init() {

        return new FramesResult();
    }

    public String frameNumberLine() {

        return FRAME_NUMBER_LINE;
    }

    public String playerFrameSigns(final Player player) {

        return playerName(player) + createSigns(player);
    }

    private String playerName(final Player player) {

        return String.format("| %4s |", player.getName());
    }

    private String createSigns(final Player player) {
        List<String> signs = player.getFrames().getFrames()
                .stream()
                .map(this::createFrameSign)
                .collect(Collectors.toList());

        return String.format(FRAME_SIGN_BAR, padEmptyString(signs));
    }

    public String playerScores(final Player player) {
        List<Integer> result = accumulateScore(getIntScores(player));

        List<String> stringScores = result.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());

        return String.format(FRAME_SCORE_BAR, padEmptyString(stringScores));
    }

    private String createFrameSign(Frame frame) {

        return frameResult.frameSign(frame);
    }

    private List<Integer> accumulateScore(List<Integer> scores) {

        final List<Integer> result = new ArrayList<>();
        final int size = scores.size();
        if (size > 0) {
            result.add(scores.get(0));
        }

        for (int i = 1; i < scores.size(); i++) {
            result.add(result.get(i - 1) + scores.get(i));
        }
        return result;
    }

    private List<Integer> getIntScores(final Player player) {

        return player.getFrames().getFrames()
                .stream()
                .map(this::getScore)
                .filter(score -> score != NO_SCORE)
                .collect(Collectors.toList());
    }

    private int getScore(final Frame frame) {

        return frameResult.frameScore(frame);
    }

    private Object[] padEmptyString(final List<String> strings) {

        int additionalSize = MAX_FRAME_NUMBER - strings.size();
        for (int i = 0; i < additionalSize; i++) {
            strings.add("");
        }

        return strings.toArray();
    }
}
