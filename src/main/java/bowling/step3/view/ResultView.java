package bowling.step3.view;

import bowling.step3.domain.*;

import java.util.List;
import java.util.Map;

public class ResultView {

    private static final String WALL_SHAPE = "|";
    private static final String BLANK = " ";
    private static final int COLUMN_WIDTH = 6;
    private static final int COLUMN_LAST_INDEX = 10;
    private static final String ZERO_TEXT = "-";
    private static final String STRIKE_TEXT = "X";

    public static void printScoreBoard(Player player) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        printName(player);
        printPinCount(player.frames());
        printScore(player.frames());
    }

    private static void printScore(Frames frames) {
        System.out.print("|      ");
        Map<Integer, Frame> frameMap = frames.frameMap();
        for (int i = 1; i <= frameMap.size(); i++) {
            System.out.print(WALL_SHAPE);
            System.out.print(getPointString(frameMap.get(i)));
        }
        System.out.println(WALL_SHAPE);
    }

    private static String getPointString(Frame frame) {
        if (frame.score() == null) {
            return BLANK.repeat(COLUMN_WIDTH);
        }
        String point = String.valueOf(frame.score().value());
        int blankCount = ((COLUMN_WIDTH - point.length()) / 2);
        return BLANK.repeat(blankCount) + point + BLANK.repeat(COLUMN_WIDTH - blankCount - point.length());
    }

    private static void printName(Player player) {
        System.out.print(WALL_SHAPE);
        int blankCount = (COLUMN_WIDTH - player.name().length()) / 2;
        System.out.print(BLANK.repeat(blankCount) + player.name() + BLANK.repeat(COLUMN_WIDTH - blankCount - player.name().length()));
    }

    private static void printPinCount(Frames frames) {
        Map<Integer, Frame> frameMap = frames.frameMap();
        for (int i = 1; i <= COLUMN_LAST_INDEX; i++) {
            System.out.print(WALL_SHAPE);
            Frame frame = frameMap.get(i);
            System.out.print(getPinCountString(frame));
        }
        System.out.println(WALL_SHAPE);
    }

    private static String getPinCountString(Frame frame) {
        int pitchCounts = frame.pitches().pitches().size();
        if (pitchCounts == 0) {
            return BLANK.repeat(COLUMN_WIDTH);
        }

        String result = "";
        if (pitchCounts == 1) {
            result += getText(frame.pitches().pitches().get(0).count());
        }

        if (pitchCounts == 2) {
            result += getTextInCaseOfTwo(frame.pitches().pitches());
        }

        if (pitchCounts == 3) {
            result += getTextInCaseOfThree(frame.pitches().pitches());
        }

        int blankCount = ((COLUMN_WIDTH - result.length()) / 2);
        return BLANK.repeat(blankCount) + result + BLANK.repeat(COLUMN_WIDTH - blankCount - result.length());
    }

    private static String getTextInCaseOfThree(List<Pitch> pitches) {

        if (pitches.get(0).count() != 10 && pitches.get(0).count() + pitches.get(1).count() == 10) {
            return String.format("%s|/|%s", getText(pitches.get(0).count()), getText(pitches.get(2).count()));
        }

        if (pitches.get(1).count() + pitches.get(2).count() == 10) {
            return String.format("%s|%s|/", getText(pitches.get(0).count()), getText(pitches.get(1).count()));
        }

        return String.format("%s|%s|%s", getText(pitches.get(0).count()), getText(pitches.get(1).count()), getText(pitches.get(2).count()));
    }

    private static String getTextInCaseOfTwo(List<Pitch> pitches) {
        if (pitches.get(0).count() != 10 && pitches.get(0).count() + pitches.get(1).count() == 10) {
            return String.format("%s|/", getText(pitches.get(0).count()));
        }
        return String.format("%s|%s", getText(pitches.get(0).count()), getText(pitches.get(1).count()));
    }

    private static String getText(int count) {
        if (count == 10) {
            return STRIKE_TEXT;
        }
        if (count == 0) {
            return ZERO_TEXT;
        }
        return String.valueOf(count);
    }
}
