package bowling.view;

public class ResultView {
    private static final String BOUND_LINE = "|";
    private static final String NAME = " NAME ";
    private static final int INIT_GAME = 0;
    private static final int FINAL_FRAME = 0;
    private static final String BLANK = " ";

//    public static void printResult(String player, int score) {
//        for (int numberOfGame = 0; numberOfGame <= FINAL_FRAME; numberOfGame++) {
//            printGameFrame(numberOfGame);
//        }
//
//        System.out.println();
//
//        for (int numberOfGame = 0; numberOfGame <= FINAL_FRAME; numberOfGame++) {
//            printScore(frame);
//        }
//    }
//
//    private static void printScore(String player, int numberOfGame, int score) {
//        if (numberOfGame == INIT_GAME) {
//            System.out.print(BOUND_LINE + BLANK + player + BLANK + BLANK + BOUND_LINE);
//        }
//        if (numberOfGame > INIT_GAME && numberOfGame < FINAL_FRAME) {
//            if(score == 10) {
//                System.out.print(BLANK + "X " + BLANK + BOUND_LINE);
//            }
//            if(score == 0) {
//                System.out.print(BLANK + "- " + BLANK + BOUND_LINE);
//            }
//            System.out.print(BLANK + score + BLANK + BOUND_LINE);
//        }
//    }
//
//    private static void printGameFrame(int numberOfGame) {
//        if (numberOfGame == INIT_GAME) {
//            System.out.print(BOUND_LINE + NAME + BOUND_LINE);
//        }
//        if (numberOfGame > INIT_GAME && numberOfGame < FINAL_FRAME) {
//            System.out.print(BLANK + "0" + numberOfGame + BLANK + BOUND_LINE);
//        }
//        if (numberOfGame == FINAL_FRAME){
//            System.out.print(BLANK + numberOfGame + BLANK + BOUND_LINE);
//        }
//    }
}

