package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.ScoreType;
import bowling.domain.player.Player;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FramesResult {
    private final static String FRAME_NUMBER_LINE;
    private static final String STRIKE = "X";
    private static final String GUTTER = "-";

    private final Player player;
    private final Frames frames;

    static {
        FRAME_NUMBER_LINE = "| NAME |" + IntStream.rangeClosed(1, 10)
                .mapToObj(i -> String.format("  %02d  |", i))
                .collect(Collectors.joining());
    }

    public FramesResult(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public String frameNumberLine() {
        return FRAME_NUMBER_LINE;
    }

    public String frameScores() {
        return String.format("| %4s |", player.Name().value()) + createScores();
    }

    private String createScores() {
        return frames.values()
                .stream()
                .map(this::createScore)
                .collect(Collectors.joining())
                .replaceAll("10", STRIKE)
                .replaceAll("0", GUTTER)
                + createEmptyFrame(frames.lastFrame());
    }

    private String createScore(Frame frame) {
        return createFrameScore(frame);
    }

    private String createFrameScore(Frame frame) {
        if (isFirstFrame(frame)) {
            return "";
        }

        if (frame.pinsSize() == 3) {
            if (frame.pinNumber(0) + frame.pinNumber(1) == 10) {
                return String.format(" %d|/|%d|", frame.pinNumber(0), frame.pinNumber(2));
            }

            if (frame.pinNumber(1) + frame.pinNumber(2) == 10) {
                return String.format(" %d|%d|/|", frame.pinNumber(0), frame.pinNumber(1));
            }

            return String.format(" %d|%d|%d|", frame.pinNumber(0), frame.pinNumber(1), frame.pinNumber(2));
        }

        ScoreType score = frame.scoreStatus();
        if (score.equals(ScoreType.FINAL_STRIKE)) {
            return String.format("  %d|%d |", frame.pinNumber(0), frame.pinNumber(1));
        }

        if (score.equals(ScoreType.STRIKE) || score.equals(ScoreType.PROCEEDING)) {
            return String.format("  %d   |", frame.pinNumber(0));
        }

        if (score.equals(ScoreType.SPARE)) {
            return String.format("  %d|/ |", frame.pinNumber(0));
        }

        return String.format("  %d|%d |", frame.pinNumber(0), frame.pinNumber(1));
    }

    private String createEmptyFrame(Frame frame) {
        if (isFirstFrame(frame)) {
            return "      |".repeat(10 - frame.number() + 1);
        }

        return "      |".repeat(10 - frame.number());
    }

    private boolean isFirstFrame(Frame frame) {
        return frame.number() == 1 && frame.isEmpty();
    }
}
