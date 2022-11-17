package bowling.step4.view;

import bowling.step4.dto.*;

import java.util.List;

public class ResultView {

    private static final String WALL_SHAPE = "|";
    private static final String BLANK = " ";
    private static final String ZERO_TEXT = "-";
    private static final String STRIKE_TEXT = "X";
    private static final int COLUMN_WIDTH = 6;
    private static final int COLUMN_LAST_INDEX = 10;

    public static void printScoreBoard(List<ResultDto> resultDto) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        for (int i = 0; i < resultDto.size(); i++) {
            printName(resultDto.get(i).name);
            printPinCount(resultDto.get(i).pitchesDto);
            printScore(resultDto.get(i).scoresDto);
        }
    }

    private static void printScore(List<ScoreDto> scoresDto) {
        System.out.print("|      ");
        for (int i = 0; i < scoresDto.size(); i++) {
            System.out.print(WALL_SHAPE);
            System.out.print(getScoreString(scoresDto.get(i)));
        }
        System.out.println(WALL_SHAPE);
    }

    private static String getScoreString(ScoreDto scoreDto) {
        if (scoreDto.score.isBlank()) {
            return BLANK.repeat(COLUMN_WIDTH);
        }
        String score = scoreDto.score;
        int blankCount = ((COLUMN_WIDTH - score.length()) / 2);
        return BLANK.repeat(blankCount) + score + BLANK.repeat(COLUMN_WIDTH - blankCount - score.length());
    }

    private static void printName(String name) {
        System.out.print(WALL_SHAPE);
        int blankCount = (COLUMN_WIDTH - name.length()) / 2;
        System.out.print(BLANK.repeat(blankCount) + name + BLANK.repeat(COLUMN_WIDTH - blankCount - name.length()));
    }

    private static void printPinCount(List<PitchDto> pitchesDto) {
        for (int i = 0; i < COLUMN_LAST_INDEX; i++) {
            System.out.print(WALL_SHAPE);
            System.out.print(getPinCountString(pitchesDto.get(i).pinCounts));
        }
        System.out.println(WALL_SHAPE);
    }

    private static String getPinCountString(List<Integer> pinCounts) {
        if (pinCounts.size() == 0) {
            return BLANK.repeat(COLUMN_WIDTH);
        }

        int pitchCounts = pinCounts.size();
        String result = "";

        if (pitchCounts == 1) {
            result += getText(pinCounts.get(0));
        }

        if (pitchCounts == 2) {
            result += getTextInCaseOfTwo(pinCounts);
        }

        if (pitchCounts == 3) {
            result += getTextInCaseOfThree(pinCounts);
        }

        int blankCount = ((COLUMN_WIDTH - result.length()) / 2);
        return BLANK.repeat(blankCount) + result + BLANK.repeat(COLUMN_WIDTH - blankCount - result.length());
    }


    private static String getTextInCaseOfThree(List<Integer> pinCounts) {

        if (pinCounts.get(0) != 10 && pinCounts.get(0) + pinCounts.get(1) == 10) {
            return String.format("%s|/|%s", getText(pinCounts.get(0)), getText(pinCounts.get(2)));
        }

        if (pinCounts.get(1) + pinCounts.get(2) == 10) {
            return String.format("%s|%s|/", getText(pinCounts.get(0)), getText(pinCounts.get(1)));
        }

        return String.format("%s|%s|%s", getText(pinCounts.get(0)), getText(pinCounts.get(1)), getText(pinCounts.get(2)));
    }

    private static String getTextInCaseOfTwo(List<Integer> pinCounts) {
        if (pinCounts.get(0) != 10 && pinCounts.get(0) + pinCounts.get(1) == 10) {
            return String.format("%s|/", getText(pinCounts.get(0)));
        }
        return String.format("%s|%s", getText(pinCounts.get(0)), getText(pinCounts.get(1)));
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
