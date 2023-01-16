package bowling.refactoring.view;

import bowling.refactoring.view.dto.*;

import java.util.List;

public class ResultView {

    private static final String WALL_SHAPE = "|";
    private static final String BLANK = " ";
    private static final String ZERO_TEXT = "-";
    private static final String STRIKE_TEXT = "X";
    private static final int COLUMN_WIDTH = 6;
    private static final int COLUMN_LAST_INDEX = 10;

    public static void printScoreBoard(List<ResultDto> results) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        for (ResultDto result : results) {
            printName(result.name);
            printScore(result.scores);
            printTotalScore(result.scores);
        }
    }

    private static void printName(String name) {
        System.out.print(WALL_SHAPE);
        int blankCount = (COLUMN_WIDTH - name.length()) / 2;
        System.out.print(BLANK.repeat(blankCount) + name + BLANK.repeat(COLUMN_WIDTH - blankCount - name.length()));
    }

    private static void printTotalScore(List<ScoreDto> scores) {
        System.out.println();
        System.out.print("|      ");
        for (int i = 0; i < COLUMN_LAST_INDEX; i++) {
            System.out.print(WALL_SHAPE);
            boolean isBlank = i > scores.size();
            System.out.print(printTotalScoreString(isBlank, i, scores));
        }
        System.out.println(WALL_SHAPE);
    }


    private static String printTotalScoreString(Boolean isBlank, int i, List<ScoreDto> scores) {
        if (isBlank) {
            return BLANK.repeat(COLUMN_WIDTH);
        }
        String score = String.valueOf(scores.get(i).totalScore);
        int blankCount = ((COLUMN_WIDTH - score.length()) / 2);
        return BLANK.repeat(blankCount) + score + BLANK.repeat(COLUMN_WIDTH - blankCount - score.length());
    }

    private static void printScore(List<ScoreDto> scores) {
        for (int i = 0; i < COLUMN_LAST_INDEX; i++) {
            System.out.print(WALL_SHAPE);

            boolean isBlank = i > scores.size();
            System.out.print(getScoreString(isBlank, i, scores));
        }
        System.out.print("|");
    }

    private static String getScoreString(Boolean isBlank, int i, List<ScoreDto> scores) {
        if (isBlank) {
            return BLANK.repeat(COLUMN_WIDTH);
        }

        List<Integer> values = scores.get(i).values;
        int pitchCounts = values.size();
        String result = "";

        if (pitchCounts == 1) {
            result += getText(values.get(0));
        }

        if (pitchCounts == 2) {
            result += getTextInCaseOfTwo(values);
        }

        if (pitchCounts == 3) {
            result += getTextInCaseOfThree(values);
        }

        int blankCount = ((COLUMN_WIDTH - result.length()) / 2);
        return BLANK.repeat(blankCount) + result + BLANK.repeat(COLUMN_WIDTH - blankCount - result.length());
    }


    private static String getTextInCaseOfThree(List<Integer> values) {

        if (!(values.get(0) == 10) && values.get(0) + values.get(1) == 10) {
            return String.format("%s|/|%s", getText(values.get(0)), getText(values.get(2)));
        }

        if (values.get(1) + values.get(2) == 10) {
            return String.format("%s|%s|/", getText(values.get(0)), getText(values.get(1)));
        }

        return String.format("%s|%s|%s", getText(values.get(0)), getText(values.get(1)), getText(values.get(2)));
    }

    private static String getTextInCaseOfTwo(List<Integer> values) {
        if (!(values.get(0) == 10) && values.get(0) + values.get(1) == 10) {
            return String.format("%s|/", getText(values.get(0)));
        }
        return String.format("%s|%s", getText(values.get(0)), getText(values.get(1)));
    }

    private static String getText(Integer score) {
        if (score == 10) {
            return STRIKE_TEXT;
        }
        if (score == 0) {
            return ZERO_TEXT;
        }
        return String.valueOf(score);
    }
}
