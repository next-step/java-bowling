package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String HEADER_MASSAGE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String NAME_MASSAGE = "|  %-4s|";
    private static final String EMPTY_MASSAGE = "      |";
    private static final String SCORE_MASSAGE = "  %-4s|";
    private static final String MAX_SCORE_MASSAGE = " %-5s|";
    private static final String JOIN_DELIMITER = "";
    private static final int MARKING_SIZE = 10;
    private static final int DEFAULT_MARK_LENGTH = 3;

    public static void result(Player player) {
        header();
        playerName(player.player());
        frame(player.frames());
    }

    private static void header() {
        System.out.println(HEADER_MASSAGE);
    }

    private static void playerName(String player) {
        System.out.print(String.format(NAME_MASSAGE, player));
    }

    private static void frame(List<Frame> frames) {
        System.out.println(String.join(JOIN_DELIMITER, framesAsMarking(frames)));
        System.out.println();
    }

    private static List<String> framesAsMarking(List<Frame> frames) {
        List<String> marking = frames.stream()
            .map(OutputView::format)
            .collect(Collectors.toCollection(ArrayList::new));

        while (marking.size() < MARKING_SIZE) {
            marking.add(EMPTY_MASSAGE);
        }

        return marking;
    }

    private static String format(Frame frame) {
        String mark = frame.mark();
        if (mark.length() > DEFAULT_MARK_LENGTH) {
            return String.format(MAX_SCORE_MASSAGE, mark);
        }
        return String.format(SCORE_MASSAGE, mark);
    }

}
