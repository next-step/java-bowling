package bowling.view;

import bowling.domain.FramesList;

import static bowling.view.StringView.*;

public class StateView {

    public static void printInitExpression() {
        for (int i = FIRST_FRAME; i <= FINAL_FRAME; i++) {
            printEmptySlot();
        }
        System.out.println();
    }


    public static void printState(int idx, FramesList userFrames, int round) {
        for (int i = 1; i < round; i++) {
            String expression = userFrames.getUserFrame(idx, i).frameExpression();
            System.out.print("  " + expression + EMPTY_STATE.substring(expression.length()) + PIPE);
        }

        checkFinalState(userFrames.getUserFrame(idx, round).frameExpression());

        for (int i = round + 1; i <= FINAL_FRAME; i++) {
            printEmptySlot();
        }
        System.out.println();
    }

    private static void checkFinalState(String expression) {
        if (expression.length() == THREE_STRIKE_EXPRESSION) {
            System.out.print(" " + expression + EMPTY_FINAL_STATE.substring(expression.length()) + PIPE);
            return;
        }
        System.out.print("  " + expression + EMPTY_STATE.substring(expression.length()) + PIPE);
    }

    private static void printEmptySlot() {
        System.out.print(EMPTY + PIPE);
    }

}
