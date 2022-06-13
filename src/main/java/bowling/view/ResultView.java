package bowling.view;

import bowling.domain.FinalFrame;
import bowling.domain.NormalFrame;
import bowling.domain.Pitching;

import java.util.*;

public class ResultView {
    private static final int TEN = 10;
    private static final int INDEX_MAXIMUM = 10;
    private static final int ZERO = 0;
    private static final String TERM = "      |";
    private static final String STRIKE = "X  ";
    private static final String GUTTER = "-";

    private ResultView() {
    }

    public static void drawName(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n");
        stringBuilder.append(String.format("|%5s |      |      |      |      |      |      |      |      |      |      |", name));

        System.out.println(String.format("%s", stringBuilder));
    }

    public static String selectNormalResult(NormalFrame normalFrame) {
        List<Pitching> pitchings = normalFrame.getPitchings();
        if (pitchings.size() == 1) {
            return findFirstResult(pitchings.get(0).getPitchingNumber());
        }
        int firstNumber = pitchings.get(0).getPitchingNumber();
        int secondNumber = pitchings.get(1).getPitchingNumber();
        return findSecondResult(firstNumber, secondNumber);
    }

    public static String findSecondResult(int firstNumber, int secondNumber) {
        if (firstNumber + secondNumber == TEN) {
            return firstNumber + "|/";
        }
        return discriminateGutter(firstNumber) + "|" + discriminateGutter(secondNumber);
    }

    public static String findFirstResult(int pitchingNumber) {
        if (pitchingNumber == TEN) {
            return STRIKE;
        }

        return discriminateGutter(pitchingNumber) + "  ";
    }

    private static String discriminateGutter(int number) {
        if (number == ZERO) {
            return GUTTER;
        }
        return String.valueOf(number);
    }

    public static void drawOneFrame(String name, List<String> results) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(resultPreFormat(name));
        stringBuilder.append(drawNormalResult(results));
        System.out.println(String.format("%s", stringBuilder));
    }


    private static String resultPreFormat(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n");
        stringBuilder.append(String.format("|%5s |", name));

        return String.format("%s", stringBuilder);
    }

    private static String drawNormalResult(List<String> results) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < results.size(); i++) {
            stringBuilder.append(String.format("%5s |", results.get(i)));
        }
        stringBuilder.append(String.format("%s \n\n", TERM.repeat(INDEX_MAXIMUM - results.size())));
        return String.format("%s", stringBuilder);
    }
}
