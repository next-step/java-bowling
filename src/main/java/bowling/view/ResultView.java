package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.dto.BowlingDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

public class ResultView {
    private static final String UPPER_FRAME = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10    |";

    public static void printBowling(BowlingDto bowlingDto) {
        StringBuilder sb = new StringBuilder();
        sb.append(UPPER_FRAME)
                .append(System.lineSeparator())
                .append(getScores(bowlingDto))
                .append(getFrameScore(bowlingDto));
        System.out.println(sb);
    }

    private static String getFrameScore(BowlingDto bowlingDto) {
        StringBuilder sb = new StringBuilder();
        sb.append("|")
                .append(getWhiteSpace())
                .append(getFrameScoreAccumulate(bowlingDto))
                .append(System.lineSeparator());
        return sb.toString();
    }

    private static String getWhiteSpace() {
        StringBuilder sb = new StringBuilder();
        String result = String.format("%-4s", "");
        result = String.format("%6s", result);
        sb.append(result)
                .append("|");
        return sb.toString();
    }

    private static String getFrameScoreAccumulate(BowlingDto bowlingDto) {
        StringBuilder sb = new StringBuilder();
        List<String> accumulateFrameScores = new ArrayList<>(getAccumulateFrameScores(bowlingDto.getFrames()));
        IntStream.range(0, Frames.FINAL_FRAME)
                .forEach(index -> sb.append(normalFrameAccumulateScoreToPrint(accumulateFrameScores.get(index))));
        sb.append(finalFrameAccumulateScoreToPrint(accumulateFrameScores.get(Frames.FINAL_FRAME)));
        return sb.toString();
    }

    private static String normalFrameAccumulateScoreToPrint(String frameScore) {
        StringBuilder sb = new StringBuilder();
        String result = String.format("%-4s", frameScore);
        result = String.format("%6s", result);
        sb.append(result)
                .append("|");
        return sb.toString();
    }

    private static String finalFrameAccumulateScoreToPrint(String frameScore) {
        StringBuilder sb = new StringBuilder();
        String result = String.format("%-6s", frameScore);
        result = String.format("%8s", result);
        sb.append(result)
                .append("|");
        return sb.toString();
    }


    private static List<String> getAccumulateFrameScores(Map<Integer, Frame> frames) {
        Optional<Integer> accumulateScore = Optional.of(0);
        List<String> accumulateFrameScores = new ArrayList<>();
        for (Frame frame : frames.values()) {
            accumulateScore = addAccumulateScore(accumulateScore, frame.frameScore());
            accumulateFrameScores.add(frameScoreToString(accumulateScore));
        }
        return accumulateFrameScores;
    }

    private static Optional<Integer> addAccumulateScore(Optional<Integer> accumulateScore, Optional<Integer> frameScore) {
        if (!accumulateScore.isPresent() || !frameScore.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(accumulateScore.get() + frameScore.get());

    }


    private static String frameScoreToString(Optional<Integer> frameScore) {
        if (frameScore.isPresent()) {
            return frameScore.get().toString();
        }
        return "";
    }


    private static String getScores(BowlingDto bowlingDto) {
        StringBuilder sb = new StringBuilder();
        sb.append("|")
                .append(getUser(bowlingDto.getPlayer()))
                .append(getNormalFrameScore(bowlingDto.getFrames()))
                .append(getFinalFrameScore(bowlingDto.getFrames()))
                .append(System.lineSeparator());
        return sb.toString();
    }

    private static String getUser(String user) {
        StringBuilder sb = new StringBuilder();
        String result = String.format("%-4s", user);
        result = String.format("%6s", result);
        sb.append(result)
                .append("|");
        return sb.toString();
    }

    private static String getNormalFrameScore(Map<Integer, Frame> frames) {
        StringBuilder sb = new StringBuilder();
        frames.keySet()
                .stream()
                .filter(k -> k < Frames.FINAL_FRAME)
                .forEach(k -> sb.append(normalFrameScoreToPrint(frames.get(k)))
                        .append("|"));
        return sb.toString();
    }

    private static String getFinalFrameScore(Map<Integer, Frame> frames) {
        StringBuilder sb = new StringBuilder();
        sb.append(finalFrameScoreToPrint(frames.get(Frames.FINAL_FRAME)))
                .append("|");
        return sb.toString();
    }


    private static String normalFrameScoreToPrint(Frame normalFrame) {
        StringBuilder sb = new StringBuilder();
        normalFrame.getScores()
                .forEach(score -> sb.append(score.getExpression()));

        if (normalFrame.getScores().size() == 2) {
            sb.insert(1, "|");
        }

        String result = String.format("%-4s", sb.toString());
        result = String.format("%6s", result);

        return result;
    }

    private static String finalFrameScoreToPrint(Frame finalFrame) {
        StringBuilder sb = new StringBuilder();
        finalFrame.getScores()
                .forEach(score -> sb.append(score.getExpression()));

        if (finalFrame.getScores().size() == 3) {
            sb.insert(2, "|");
        }
        if (finalFrame.getScores().size() >= 2) {
            sb.insert(1, "|");
        }

        String result = String.format("%-6s", sb.toString());
        result = String.format("%8s", result);

        return result;
    }


}
