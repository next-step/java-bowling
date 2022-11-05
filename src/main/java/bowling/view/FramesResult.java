package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Name;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FramesResult {

    private static final int MAX_FRAME_NUMBER = 10;
    private static final String FRAME_NUMBER_LINE;
    private static final String STRIKE = "X";
    private static final String GUTTER = "-";

    private final Name name;
    private final Frames frames;

    static {
        FRAME_NUMBER_LINE = "| NAME |" + IntStream.rangeClosed(1, MAX_FRAME_NUMBER)
                .mapToObj(i -> String.format("  %02d  |", i))
                .collect(Collectors.joining());
    }

    public FramesResult(Name name, Frames frames) {

        this.name = name;
        this.frames = frames;
    }

    public String frameNumberLine() {

        return FRAME_NUMBER_LINE;
    }

    public String frameScores() {

        return createName() + createScores();
    }

    private String createName() {

        return String.format("| %4s |", name.getName());
    }

    private String createScores() {

        return frames.getFrames()
                .stream()
                .map(this::createFrameScore)
                .collect(Collectors.joining())
                .replaceAll("10", STRIKE)
                .replaceAll("0", GUTTER)
                + createEmptyFrame(frames.lastFrame());
    }

    private String createFrameScore(Frame frame) {

        return new FrameResult(frame).createFrameScore();
    }

    private String createEmptyFrame(Frame frame) {

        if (isFirstFrame(frame)) {
            return "      |".repeat(MAX_FRAME_NUMBER - frame.getNumber() + 1);
        }

        return "      |".repeat(MAX_FRAME_NUMBER - frame.getNumber());
    }

    private boolean isFirstFrame(Frame frame) {

        return frame.getNumber() == 1 && frame.isEmpty();
    }
}
