package bowling.view;

import bowling.domain.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static bowling.controller.BowlingGameController.MAX_FRAME_NO;
import static bowling.controller.BowlingGameController.START_FRAME_NO;

public class ResultView {
    private static final String BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    protected static final String DELIMITER = "|";
    protected static final String EMPTY_FRAME = "      |";
    protected static final String SYMBOL_GUTTER = "-";
    protected static final String ZERO = "0";
    protected static final String SCORE_INDENT = "|      |";
    protected static final String EMPTY = "";

    public void printBoard(Players players) {
        
        System.out.println(BOARD_HEADER);

        players.forEach(player -> {
            System.out.print(playerName(player.name()));
            System.out.println(frameResult(player.frames()));
            System.out.println(scoreResult(player.frames()));
        });
    }

    private String scoreResult(Frames frames) {
        StringBuilder builder = new StringBuilder();
        builder.append(SCORE_INDENT);

        IntStream.rangeClosed(START_FRAME_NO, MAX_FRAME_NO)
                .forEach(frameNo -> builder.append(scorePitches(frameNo, frames)));

        return builder.toString();
    }

    private String scorePitches(int frameNo, Frames frames) {
        Map<Integer, FrameScore> scoreMap = frames.frameScores();

        if (scoreMap.containsKey(frameNo)) {
            FrameScore frameScore = scoreMap.get(frameNo);
            String frameResult = scoreResult(frameScore.isCalculating(), frameNo, scoreMap.values());
            return String.format(" %-3s  " + DELIMITER, frameResult);
        }

        return EMPTY_FRAME;
    }

    public String scoreResult(boolean isCalculating, int frameNo, Collection<FrameScore> frameScore) {
        if (isCalculating) {
            return EMPTY;
        }
        return String.valueOf(accumulateScore(frameNo, frameScore));
    }

    private int accumulateScore(int frameNo, Collection<FrameScore> frameScore) {
        return frameScore.stream()
                .limit(frameNo)
                .mapToInt(score -> score.score().intValue())
                .sum()
                ;
    }

    private String frameResult(Frames frames) {
        StringBuilder builder = new StringBuilder();

        IntStream.rangeClosed(START_FRAME_NO, MAX_FRAME_NO)
                .forEach(frameNo -> builder.append(framePitches(frameNo, frames)));

        return builder.toString();
    }

    private String framePitches(int frameNo, Frames frames) {
        Map<Integer, Frame> frameMap = frames.frames();

        if (frameMap.containsKey(frameNo)) {
            String frameResult = frameResult(frameMap.get(frameNo));
            return String.format(" %-3s  " + DELIMITER, frameResult);
        }

        return EMPTY_FRAME;
    }

    public String frameResult(Frame frame) {
        Pitches pitches = frame.pitches();

        return pitchesToString(pitches);
    }

    private String pitchesToString(Pitches pitches) {
        List<String> pitchesDisplay = new ArrayList<>();
        pitches.forEach(pitch -> pitchesDisplay.add(pitchToString(pitch)));

        return String.join("|", pitchesDisplay);
    }

    private String pitchToString(Pitch pitch) {
        return convertZeroToHyphen(pitch.display());
    }

    private String playerName(String name) {
        return String.format(DELIMITER + " %4s " + DELIMITER, name);
    }

    private String convertZeroToHyphen(String target) {
        return target.replace(ZERO, SYMBOL_GUTTER);
    }
}
