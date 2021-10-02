package bowling.view;

public class ResultView {

    private static final String NAME_BOARD = "| NAME |";
    private static final String BOARD_FORMAT = "  %02d  |";

    private static final String BOARD_NAME_FORMAT = "|  %-3s |";
    private static final String BOARD_SCORE_FORMAT = " %-5s|";

    private static final String SCORE_DELIMITER = "|";

    private ResultView() {
    }

//    public static void printBoard(User user, Frames frames) {
//        printScoreBoard();
//        printScoreResultBoard(user, frames);
//    }
//
//    public static void printScoreBoard() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(NAME_BOARD);
//        sb.append(IntStream.rangeClosed(FIRST_ROUND, FINAL_ROUND)
//            .mapToObj(ResultView::toBoardFormat)
//            .collect(Collectors.joining()));
//        System.out.println(sb.toString());
//    }
//
//    private static String toBoardFormat(int num) {
//        return String.format(BOARD_FORMAT, num);
//    }
//
//    public static void printScoreResultBoard(User user, Frames frames) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(toNameFormat(user));
//
//        sb.append(printFrameBoard(frames));
//        sb.append(printRemainBoard(frames));
//        System.out.println(sb.toString());
//    }
//
//    private static String printFrameBoard(Frames frames) {
//        return frames.scores()
//            .map(Score::values)
//            .map(ResultView::pinsToString)
//            .map(ResultView::toScoreFormat)
//            .collect(Collectors.joining());
//    }
//
//    private static String printRemainBoard(Frames frames) {
//        return IntStream.range(frames.size(), FINAL_ROUND)
//            .mapToObj(index -> toScoreFormat(""))
//            .collect(Collectors.joining());
//    }
//
//    private static String toScoreFormat(String s) {
//        return String.format(BOARD_SCORE_FORMAT, s);
//    }
//
//    private static String toNameFormat(User user) {
//        return String.format(BOARD_NAME_FORMAT, user.value());
//    }
//
//    private static String pinsToString(List<Pin> pins) {
//        return IntStream.range(0, pins.size())
//            .filter(index -> Objects.nonNull(pins.get(index)))
//            .mapToObj(index -> pinToString(pins, index))
//            .collect(Collectors.joining(SCORE_DELIMITER));
//    }
//
//    private static String pinToString(List<Pin> pins, int index) {
//        return PinType.pinToString(pins, index);
//    }

}
