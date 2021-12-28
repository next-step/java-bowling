package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Player;

public class ResultView {

    private static final String NAME = "NAME";
    private static final String LINE = "|";
    private static final String ONE_SPACE = " ";
    private static final String TWO_SPACE = "  ";
    private static final String THREE_SPACE = "   ";
    private static final String ZERO_STRING = "0";
    private static final String NAME_FORMAT = "|  %s |";
    private static final String EMPTY_FRAME = "       ";
    private static final String ENTER = "\n";
    private static final int FINAL_FRAME = 10;
    private static final int FIRST_FRAME = 1;
    private static final int MAX_PIN = 10;
    private static final int MIN_PIN = 0;
    private static final String SPARE_MARK = "/";
    private static final String GUTTER_MARK = "-";
    private static final String STRIKE_MARK = "X";
    private static final String LINE_MARK = "|";

    private ResultView() {
        throw new AssertionError();
    }

    public static void printFrameNumber(int number) {
        System.out.print(number);
    }

    public static void printEmptyFrame(Player player) {
        System.out.println(printHead());
        System.out.print(printName(player.getPlayerName().getName()));
        System.out.print(printEmptyScore(FINAL_FRAME));
        System.out.println(ENTER);
    }

    public static void printScoreFrame(Player player, Frames frames) {
        System.out.println(printHead());
        System.out.print(printName(player.getPlayerName().getName()));
        System.out.print(printEachFrame(frames));
        System.out.print(printEmptyScore(FINAL_FRAME- frames.getFrameNumber()));
        System.out.println(ENTER);
    }

    private static String printHead() {
        StringBuilder headBuilder = new StringBuilder(LINE + ONE_SPACE + NAME + ONE_SPACE + LINE);
        for (int number = 1; number <= 10; number++) {
            headBuilder.append(TWO_SPACE).append(printHeadFrameNumber(number)).append(THREE_SPACE).append(LINE);
        }
        return headBuilder.toString();
    }

    private static String printHeadFrameNumber(int number) {
        if (number != FINAL_FRAME) {
            return ZERO_STRING + number;
        }
        return String.valueOf(number);
    }

    static String printName(String name) {
        return String.format(NAME_FORMAT, name);
    }

    static String printEmptyScore(int countOfEmptyFrames) {
        StringBuilder builder = new StringBuilder();
        for (int i = FIRST_FRAME; i <= countOfEmptyFrames; i++) {
            builder.append(EMPTY_FRAME + LINE);
        }
        return builder.toString();
    }

    static String printEachFrame(Frames frames) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < frames.getFrameNumber(); i++) {
            String pin = convertScore(frames.findFrame(i));
            String spacing = spacing(pin);
            builder.append(spacing)
                    .append(pin)
                    .append(spacing)
                    .append(LINE);
        }
        return builder.toString();
    }

    private static String convertScore(Frame frame) {
        int first = frame.getCountOfHits(0);
        int second = frame.getCountOfHits(1);
        int bonus = frame.getCountOfHits(2);
        if (frame.getCountOfPitching() == 1) {
            return scoreToMark(first);
        }
        if (frame.getCountOfPitching() == 2) {
            return scoreToMark(first, second);
        }
        return scoreToMark(first, second, bonus);
    }

    private static String scoreToMark(int first) {
        if (first == MIN_PIN) {
            return GUTTER_MARK;
        }
        if (first == MAX_PIN) {
            return STRIKE_MARK;
        }
        return String.valueOf(first);
    }

    private static String scoreToMark(int first, int second) {
        if (first + second == MAX_PIN && first == MIN_PIN) {
            return GUTTER_MARK + LINE_MARK + SPARE_MARK;
        }
        if (first + second == MAX_PIN) {
            return first + LINE_MARK + SPARE_MARK;
        }
        if (first + second == MIN_PIN) {
            return GUTTER_MARK;
        }
        return scoreToMark(first) + LINE_MARK + scoreToMark(second);
    }

    private static String scoreToMark(int first, int second, int bonus) {
        if (first == MAX_PIN) {
            return STRIKE_MARK + LINE_MARK + scoreToMark(second, bonus);
        }
        return scoreToMark(first, second) + LINE_MARK + scoreToMark(bonus);
    }

    private static String spacing(String text) {
        if (text.length() == 1) {
            return THREE_SPACE;
        }
        if (text.length() == 3) {
            return TWO_SPACE;
        }
        if (text.length() == 5) {
            return ONE_SPACE;
        }
        return "";
    }

}
