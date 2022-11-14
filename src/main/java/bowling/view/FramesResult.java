package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FramesResult {

    private static final int NO_SCORE = -1;
    private static final int MAX_FRAME_NUMBER = 10;

    private static final String FRAME_NUMBER_LINE;
    private final Participant participant;
    private final Frames frames;
    private final FrameResult frameResult = new FrameResult();

    static {
        FRAME_NUMBER_LINE = "| NAME |" + IntStream.rangeClosed(1, MAX_FRAME_NUMBER)
                .mapToObj(i -> String.format("  %02d  |", i))
                .collect(Collectors.joining());
    }

    public FramesResult(final Participant participant, final Frames frames) {

        this.participant = participant;
        this.frames = frames;
    }

    public String frameNumberLine() {

        return FRAME_NUMBER_LINE;
    }

    private String playerName() {

        return String.format("| %4s |", participant.getParticipant());
    }

    public String frameSigns() {

        return playerName() + createSigns();
    }

    private String createSigns() {

        return frames.getFrames()
                .stream()
                .map(this::createFrameSign)
                .collect(Collectors.joining()) + createEmptyFrame(frames.lastFrame());
    }

    public String frameScores() {

        return "|      |" + createScores();
    }

    private String createScores() {

        final List<Integer> scores = frames.getFrames()
                .stream()
                .map(this::getScore)
                .filter(score -> score != NO_SCORE)
                .collect(Collectors.toList());

        final List<Integer> result = new ArrayList<>();
        if (scores.size() > 0) {
            result.add(scores.get(0));
        }

        for (int i = 1; i < scores.size(); i++) {
            result.add(result.get(i - 1) + scores.get(i));
        }

        return result.stream()
                .map(this::createScore)
                .collect(Collectors.joining()) + createEmptyFrame(MAX_FRAME_NUMBER - result.size());
    }

    private String createScore(final int score) {

        return String.format(" %3d ", score) + " |";
    }

    private int getScore(final Frame frame) {

        return frameResult.frameScore(frame);
    }

    private String createFrameSign(final Frame frame) {

        return frameResult.frameSign(frame) + "|";
    }

    private String createEmptyFrame(final Frame frame) {

        return createEmptyFrame(MAX_FRAME_NUMBER - frame.getFrameNumber());
    }

    private String createEmptyFrame(final int count) {

        return "      |".repeat(count);
    }
}
