package bowling.step2.view;

import bowling.step2.domain.Frame;
import bowling.step2.domain.Player;
import bowling.step2.domain.Score;
import bowling.step2.utils.GameRuleUtils;

import java.util.List;
import java.util.Map;

public class ResultView {

    private static final String ZERO = "0";
    private static final String WALL_SHAPE = "|";
    private static final String BLANK = " ";
    private static final String NAME_TEXT = "NAME";
    private static final int COLUMN_WIDTH = 6;
    private static final String ZERO_TEXT = "-";
    private static final String SPARE_TEXT = "/";
    private static final String STRIKE_TEXT = "X";

    public static void printInitScoreBoard(Player player) {
        printIndex();
        printName(player);
        printInitBoard(GameRuleUtils.GAME_START_INDEX);
    }

    public static void printGameScoreBoard(Player player) {
        printIndex();
        printName(player);
        Map<Integer, Frame> scoresMap = player.scoreMap();
        printGameResult(scoresMap);
        printInitBoard(scoresMap.keySet().size() + 1);
    }

    private static void printInitBoard(int startIndex) {
        for (int i = startIndex; i <= GameRuleUtils.GAME_LAST_INDEX; i++) {
            System.out.print(WALL_SHAPE);
            System.out.print(BLANK.repeat(6));
        }
        System.out.println(WALL_SHAPE);
    }

    private static void printGameResult(Map<Integer, Frame> scoresMap) {

        for (int i = GameRuleUtils.GAME_START_INDEX; i <= scoresMap.keySet().size(); i++) {
            System.out.print(WALL_SHAPE);
            Frame frame = scoresMap.get(i);
            System.out.print(getScoresString(frame));
        }
    }

    private static String getScoresString(Frame frame) {
        String result = "";
        for (int i = 0; i < frame.Scores().size(); i++) {
            result = attachSeparator(frame.Scores(), result, i);
            result = getScore(i, frame, result);
        }
        int blankCount = ((COLUMN_WIDTH - result.length()) / 2);
        return BLANK.repeat(blankCount) + result + BLANK.repeat(COLUMN_WIDTH - blankCount - result.length());
    }

    private static String getScore(int index, Frame frame, String result) {
        List<Score> scores = frame.Scores();
        if (scores.get(index).isZero()) {
            return result + ZERO_TEXT;
        }

        if (scores.get(index).isStrike()) {
            return result + STRIKE_TEXT;
        }

        if (index == scores.size() - 1 && frame.isSpare()) {
            return result + SPARE_TEXT;
        }
        return result + frame.Scores().get(index).score();
    }

    private static String attachSeparator(List<Score> scores, String result, int i) {
        if (i > 0 && i != scores.size()) {
            result += WALL_SHAPE;
        }
        return result;
    }


    private static void printName(Player player) {
        System.out.print(WALL_SHAPE);
        int blankCount = (COLUMN_WIDTH - player.name().length()) / 2;
        System.out.print(BLANK.repeat(blankCount) + player.name() + BLANK.repeat(COLUMN_WIDTH - blankCount - player.name().length()));
    }

    private static void printIndex() {
        System.out.print(WALL_SHAPE);
        System.out.print(BLANK + NAME_TEXT + BLANK);
        for (int i = GameRuleUtils.GAME_START_INDEX; i <= GameRuleUtils.GAME_LAST_INDEX; i++) {
            System.out.print(WALL_SHAPE);
            System.out.print(BLANK.repeat(2) + getIndexNumberString(i) + BLANK.repeat(2));
        }
        System.out.println(WALL_SHAPE);
    }

    private static String getIndexNumberString(int num) {
        if (num < GameRuleUtils.GAME_LAST_INDEX) {
            return ZERO + num;
        }
        return String.valueOf(num);
    }
}
