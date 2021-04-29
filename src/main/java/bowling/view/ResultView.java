package bowling.view;

import bowling.domain.frame.Frame;
import bowling.dto.BowlingDto;

import java.util.Map;

public class ResultView {
    private static final String UPPER_FRAME = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10    |";
    private static final Integer FINAL_FRAME = 9;

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

    private static String getNormalFrame(Map<Integer, Frame> frames) {
        StringBuilder sb = new StringBuilder();
        frames.keySet()
                .stream()
                .filter(k -> k < FINAL_FRAME)
                .forEach(k -> sb.append(normalFrameToPrint(frames.get(k)))
                        .append("|"));
        return sb.toString();
    }

    private static String getFinalFrame(Map<Integer, Frame> frames) {
        StringBuilder sb = new StringBuilder();
        sb.append(finalFrameToPrint(frames.get(FINAL_FRAME)))
                .append("|");
        return sb.toString();
    }


    private static String normalFrameToPrint(Frame normalFrame) {
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

    private static String finalFrameToPrint(Frame finalFrame) {
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
