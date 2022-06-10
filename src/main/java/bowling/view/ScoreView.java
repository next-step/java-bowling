package bowling.view;

import java.util.List;

import static bowling.view.StringView.*;

public class ScoreView {

    public static void printInitScore() {
        System.out.print(PIPE + EMPTY + PIPE);
        for (int i = FIRST_FRAME; i <= FINAL_FRAME; i++) {
            System.out.print(EMPTY + PIPE);
        }
        System.out.println();
    }

    public static void printScore(List<Integer> totalList) {
        if(totalList.isEmpty()) {
            printInitScore();
            return;
        }
        System.out.print(PIPE + "      " + PIPE);
        int size = totalList.size();
        for (int total : totalList) {
            print(total);
        }

        for (int i = size; i < FINAL_FRAME; i++) {
            System.out.print("      " + PIPE);
        }
        System.out.println();
    }

    private static void print(int total) {
        if (total == NOT_SCORE_STATE) {
            System.out.print("      " + PIPE);
            return;
        }
        System.out.print("  " + total + EMPTY_STATE.substring((int) (Math.log10(total) + 1)) + PIPE);
    }

}
