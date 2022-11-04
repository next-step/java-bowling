package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.domain.FinalFrame.MAX_FRAME_NUMBER;
import static bowling.domain.Frame.NO_SCORE;

public class FramesResult {
    private final static String FRAME_NUMBER_LINE;

    private final FrameResult frameResult = new FrameResult();
    private final Player player;
    private final Frames frames;

    static {
        FRAME_NUMBER_LINE = "| NAME |" + IntStream.rangeClosed(1, MAX_FRAME_NUMBER)
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

    public String frameSigns() {
        return playerName() + createSigns();
    }

    private String playerName() {
        return String.format("| %4s |", player.getName().getValue());
    }

    private String createSigns() {
        return frames.values()
                .stream()
                .map(this::createFrameSign)
                .collect(Collectors.joining()) + createEmptyFrame(frames.lastFrame());
    }

    public String frameScores() {
        return "|      |" + createScores();
    }

    private String createScores() {
        List<Integer> scores = frames.values()
                .stream()
                .map(this::getScore)
                .filter(score -> score != NO_SCORE)
                .collect(Collectors.toList());

        List<Integer> result = new ArrayList<>();
        if (scores.size() > 0) {
            result.add(scores.get(0));
        }

        for (int i = 1; i < scores.size(); i++) {
            result.add(result.get(i - 1) + scores.get(i));
        }

        return result.stream()
                .map(this::createScore)
                .collect(Collectors.joining()) + createEmptyFrame(10 - result.size());
    }

    private String createScore(int score) {
        return String.format(" %3d ", score) + " |";
    }

    private int getScore(Frame frame) {
        return frameResult.frameScore(frame);
    }

    private String createFrameSign(Frame frame) {
        return frameResult.frameSign(frame) + "|";
    }

    private String createEmptyFrame(Frame frame) {
        return createEmptyFrame(MAX_FRAME_NUMBER - frame.number());
    }

    private String createEmptyFrame(int count) {
        return "      |".repeat(count);
    }
}
