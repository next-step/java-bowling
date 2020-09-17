package bowling.view;

import java.util.Map;
import java.util.stream.IntStream;

public class ResultView {

    private static final String PRINT_HEADER_NAME_COLUMN = "| NAME |";
    private static final String PRINT_HEADER_FRAME_NUMBER_COLUMN = "  %02d  |";
    private static final String PRINT_BODY_PLAYER_NAME_COLUMN = "| %4s |";


    private ResultView() {
    }

//    public static void printFrameViewHeader(int frameNumber, String name, Map<Integer, Pins> score) {
//        printHeader(frameNumber);
////        printBody(frameNumber, name, score);
//    }

//    private static void printHeader(int frameNumber) {
//        System.out.print(PRINT_HEADER_NAME_COLUMN);
//
//        IntStream.range(1, frameNumber)
//                .boxed()
//                .forEach(frame -> {
//                    System.out.printf(String.format(PRINT_HEADER_FRAME_NUMBER_COLUMN, frame));
//                });
//
//        System.out.println();
//    }

//    private static void printBody(int frameNumber, String name, Map<Integer, Pins> score) {
//        System.out.print(String.format(PRINT_BODY_PLAYER_NAME_COLUMN, name));
//        System.out.println(score);
//        System.out.println();
//    }

//    private static String scoreToString(String state) {
//    }

}
