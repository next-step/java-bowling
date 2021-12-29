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
    private static final String JOIN_DELIMITER = "";


    public static void result(Player player) {
        header();
        playerName(player.player());
        frame(player.frames());
    }

    private static void frame(List<Frame> frames) {
        List<String> marking = frames.stream()
            .map(frame -> String.format(SCORE_MASSAGE, frame.mark()))
            .collect(Collectors.toCollection(ArrayList::new));

        while (marking.size() < 10) {
            marking.add(EMPTY_MASSAGE);
        }
        System.out.println(String.join(JOIN_DELIMITER, marking));
        System.out.println();
    }

    private static void playerName(String player) {
        System.out.print(String.format(NAME_MASSAGE, player));
    }

    private static void header() {
        System.out.println(HEADER_MASSAGE);
    }


}
