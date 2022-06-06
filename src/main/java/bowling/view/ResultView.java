package bowling.view;

import bowling.domain.FramesList;
import bowling.domain.User;
import bowling.domain.Users;

public class ResultView {
    private static final String
            MENU = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String EMPTY = "      ";
    private static final String EMPTY_STATE = "    ";
    private static final String EMPTY_FINAL_STATE = "      ";
    private static final String PIPE = "|";
    private static final int THREE_STRIKE_EXPRESSION = 5;
    private static final int FIRST_FRAME = 1;
    private static final int FINAL_FRAME = 10;

    public static void printInit(Users users) {
        printRound();
        users.getUsers().forEach(user -> {
            printInitExpression(user);
            printInitScore();
        });
    }

    private static void printInitScore() {
        System.out.print(PIPE + EMPTY + PIPE);
        for(int i = FIRST_FRAME ; i<= FINAL_FRAME; i++) {
            System.out.print(EMPTY + PIPE);
        }
        System.out.println();
    }

    private static void printInitExpression(User user) {
        printUser(user);
        for(int i = FIRST_FRAME ; i<= FINAL_FRAME; i++) {
            printEmptySlot();
        }
        System.out.println();
    }

    private static void printUser(User user) {
        System.out.print(PIPE + "  " + user.getLetters() + " " + PIPE);
    }

    private static void printRound() {
        System.out.println(MENU);
    }

    private static void printEmptySlot() {
        System.out.print(EMPTY + PIPE);
    }

    public static void printState(Users users, FramesList userFrames, int round) {
        printRound();
        for(int i = 0; i<users.size(); i++) {
            printUser(users.getUsers().get(i));
            printStateUntilRound(userFrames, i ,round);
        }
    }

    private static void printStateUntilRound(FramesList userFrames, int idx, int round) {
        for(int i =1; i<=round; i++) {
            String expression = userFrames.getUserFrames().get(idx).getFrame(i).frameExpression();
            if(expression.length() == THREE_STRIKE_EXPRESSION) {
                System.out.print(" " + expression +  EMPTY_FINAL_STATE.substring(expression.length()) + PIPE);
                break;
            }
            System.out.print("  " + expression +  EMPTY_STATE.substring(expression.length()) + PIPE);
        }
        for(int i = round + 1; i<=FINAL_FRAME; i++) {
            printEmptySlot();
        }
        System.out.println();
    }
}
