package bowling.step2.view;

import bowling.step2.domain.Player;

public class ResultView {

    private static final int GAME_START_INDEX = 1;
    private static final int GAME_LAST_INDEX = 10;
    private static final String ZERO = "0";
    private static final String WALL_SHAPE = "|";
    private static final String BLANK = " ";
    private static final String NAME_TEXT = "NAME";
    private static final int COLUMN_WIDTH = 6;

    public static void printGrameScoreBoard(Player player) {
        System.out.println();
        printIndex();
        printName(player);
        printResult();
    }

    private static void printResult() {
        for (int i = GAME_START_INDEX; i <= GAME_LAST_INDEX; i++) {
            System.out.print(WALL_SHAPE);
            System.out.print(BLANK.repeat(6));
        }
        System.out.print(WALL_SHAPE);
    }

    private static void printName(Player player) {
        System.out.print(WALL_SHAPE);
        int blankCount = (COLUMN_WIDTH - player.name().length()) / 2;
        System.out.print(BLANK.repeat(blankCount) + player.name() + BLANK.repeat(COLUMN_WIDTH - blankCount - player.name().length()));
    }

    private static void printIndex() {
        System.out.print(WALL_SHAPE);
        System.out.print(BLANK + NAME_TEXT + BLANK);
        for (int i = GAME_START_INDEX; i <= GAME_LAST_INDEX; i++) {
            System.out.print(WALL_SHAPE);
            System.out.print(BLANK.repeat(2) + getIndexNumberString(i) + BLANK.repeat(2));
        }
        System.out.println(WALL_SHAPE);
    }

    private static String getIndexNumberString(int num) {
        if (num < GAME_LAST_INDEX) {
            return ZERO + num;
        }
        return String.valueOf(num);

    }
}
