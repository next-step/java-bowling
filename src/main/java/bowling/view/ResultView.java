package bowling.view;

import bowling.domain.Bowling;
import bowling.domain.FinalFrame;

import java.util.*;

public class ResultView {
    private static final int TEN = 10;
    private static final int ONE = 1;
    private static final int INDEX_MAXIMUM = 9;
    private static final int ZERO = 0;
    private static final String TERM = "      |";
    private static final String STRIKE = "X  ";
    private static final String GUTTER = "-";

    private ResultView() {
    }

    public static void drawBowling(String name, Bowling bowling) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n");
        stringBuilder.append(String.format("|%5s |      |      |      |      |      |      |      |      |      |      |\n\n", name));
        List<String> results = new ArrayList<>();
        List<Integer> sum = new ArrayList<>();

        bowling.getNormalFrames().stream().forEachOrdered(normalFrame -> {
            int num1 = normalFrame.getFirstTry().getFirstNumber();
            int num2 = normalFrame.getSecondTry().getSecondNumber();
            results.add(oneTumble(num1, num2));
            sum.add(num1 + num2);
        });
        FinalFrame finalFrame = bowling.getFinalFrame();
        stringBuilder.append(drawNormalResults(name, results, sum));
        stringBuilder.append(drawFinalResult(name, results, finalFrame));

        System.out.println(stringBuilder);

    }

    private static String drawNormalResults(String name, List<String> results, List<Integer> sum) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < results.size(); i++) {
            stringBuilder.append(String.format("%d 프레임 투구 : %d\n", i + ONE, sum.get(i)));
            stringBuilder.append(resultPreFormat(name));
            stringBuilder.append(drawNormalResult(i, results));
        }
        return String.format("%s", stringBuilder);
    }

    private static String resultPreFormat(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n");
        stringBuilder.append(String.format("|%5s |", name));

        return String.format("%s", stringBuilder);
    }

    private static String drawNormalResult(int size, List<String> results) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j <= size; j++) {
            stringBuilder.append(String.format("%5s |", results.get(j)));
        }
        stringBuilder.append(String.format("%s \n\n", TERM.repeat(INDEX_MAXIMUM - size)));
        return String.format("%s", stringBuilder);
    }

    private static String drawFinalResult(String name, List<String> results, FinalFrame finalFrame) {
        StringBuilder stringBuilder = new StringBuilder();
        int finalFirst = finalFrame.getNormalFrame().getFirstTry().getFirstNumber();
        int finalSecond = finalFrame.getNormalFrame().getSecondTry().getSecondNumber();
        int BonusScore = finalFrame.getBonus();
        String finalResult = oneTumble(finalFirst, finalSecond);
        if (BonusScore > ZERO) {
            finalResult += "|" + selectFinalBonus(BonusScore);
        }
        results.add(finalResult);

        stringBuilder.append(String.format("%s 프레임 투구 : %d", TEN, finalFirst + finalSecond));
        if (BonusScore > ZERO) {
            stringBuilder.append(String.format(" + %s", BonusScore));
        }
        stringBuilder.append("\n" + resultPreFormat(name));
        stringBuilder.append(drawNormalResult(INDEX_MAXIMUM, results));
        return String.format("%s", stringBuilder);
    }

    private static String oneTumble(int num1, int num2) {
        if (num1 + num2 == TEN) {
            return selectSpecial(num1);
        }
        return discriminateGutter(num1) + "|" + discriminateGutter(num2);
    }

    private static String selectSpecial(int num1) {
        if (num1 == TEN) {
            return STRIKE;
        }
        return num1 + "|/";
    }

    private static String discriminateGutter(int number) {
        if (number == ZERO) {
            return GUTTER;
        }
        return String.valueOf(number);
    }

    private static String selectFinalBonus(int bonusScore) {
        if (bonusScore == TEN) {
            return STRIKE;
        }
        return String.valueOf(bonusScore);
    }
}
