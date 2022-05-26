package bowling.view;

import bowling.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ResultView {
    private static final String
            MENU = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final int FINAL_ROUND = 10;
    private static final String EMPTY = "    ";
    private static final String PIPELINE = "|";
    private static final String FINAL_ROUND_EMPTY = "          ";
    private static final List<String> RESULT_STATES = Arrays.asList("","","","","","","","","","","","");

    public static void printFirstRoundFrame(User user) {
        printEmptyRound(user);
        printEmptyScore();
    }

    public static void printEmptyScore() {
        System.out.print(PIPELINE);
        for(int k = 0; k<=FINAL_ROUND; k++) {
            System.out.print("      " + PIPELINE);
        }
        System.out.println();
    }

    public static void printEmptyRound(User user) {
        System.out.println(MENU);
        System.out.print(user.getName());
        for(int k = 0; k<FINAL_ROUND; k++) {
            System.out.print("      " + PIPELINE);
        }
        System.out.println();
    }

    public static void printState(User user, String stateExpression, int i) {
        System.out.println(MENU);
        System.out.print(user.getName());
        String frameResult = "";
        if(i <= 10) {
            frameResult = "  " + stateExpression + EMPTY.substring(stateExpression.length());
            RESULT_STATES.set(i -1, frameResult + PIPELINE);
        }
        if(i > 10) {
            frameResult = "  "+stateExpression + FINAL_ROUND_EMPTY.substring(stateExpression.length());
            RESULT_STATES.set(RESULT_STATES.size() - 1, frameResult + PIPELINE);
        }

        for (String resultState : RESULT_STATES) {
            System.out.print(resultState);
        }
        for(int k = i; k<FINAL_ROUND; k++) {
            System.out.print("      " + PIPELINE);
        }
        System.out.println();
    }

}
