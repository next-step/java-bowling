package bowling.view;

import bowling.domain.frame.Frames;
import bowling.domain.frame.Scores;
import bowling.dto.BowlingDto;

import java.util.Map;

public class ResultView {
    private static final String UPPER_FRAME = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10    |";

    public static void printBowling(BowlingDto bowlingDto) {
        StringBuilder sb = new StringBuilder();
        sb.append(UPPER_FRAME)
                .append(System.lineSeparator());
        sb.append(getBowling(bowlingDto));
        sb.append(System.lineSeparator());
        System.out.println(sb);
    }

    private static String getBowling(BowlingDto bowlingDto) {
        StringBuilder sb = new StringBuilder();
        sb.append("|")
                .append(getUser(bowlingDto.getPlayer()))
                .append(getNormalFrame(bowlingDto.getFrames()))
                .append(getFinalFrame(bowlingDto.getFrames()));
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

    private static String getNormalFrame(Map<Integer, Scores> frames) {
        StringBuilder sb = new StringBuilder();
        frames.keySet()
                .stream()
                .filter(k -> k < Frames.FINAL_FRAME)
                .forEach(k -> sb.append(normalFrameToPrint(frames.get(k)))
                        .append("|"));
        return sb.toString();
    }

    private static String getFinalFrame(Map<Integer, Scores> frames) {
        StringBuilder sb = new StringBuilder();
        sb.append(finalFrameToPrint(frames.get(Frames.FINAL_FRAME)))
                .append("|");
        return sb.toString();
    }


    private static String normalFrameToPrint(Scores normalScores) {
        StringBuilder sb = new StringBuilder();
        normalScores.getScores()
                .forEach(score -> sb.append(score.getExpression()));

        if (normalScores.getScores().size() == 2) {
            sb.insert(1, "|");
        }

        String result = String.format("%-4s", sb.toString());
        result = String.format("%6s", result);

        return result;
    }

    private static String finalFrameToPrint(Scores finalScores) {
        StringBuilder sb = new StringBuilder();
        finalScores.getScores()
                .forEach(score -> sb.append(score.getExpression()));

        if (finalScores.getScores().size() == 3) {
            sb.insert(2, "|");
        }
        if (finalScores.getScores().size() >= 2) {
            sb.insert(1, "|");
        }

        String result = String.format("%-6s", sb.toString());
        result = String.format("%8s", result);

        return result;
    }


}
