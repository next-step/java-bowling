package bowling.view;

import bowling.domain.User;
import bowling.domain.Users;

public class ResultView {
    private static final String
            MENU = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String EMPTY = "      ";
    private static final String PIPE = "|";
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
        System.out.print(PIPE + "  " + user.getLetters() + " " + PIPE);
        for(int i = FIRST_FRAME ; i<= FINAL_FRAME; i++) {
            System.out.print(EMPTY + PIPE);
        }
        System.out.println();
    }

    private static void printRound() {
        System.out.println(MENU);
    }

}
