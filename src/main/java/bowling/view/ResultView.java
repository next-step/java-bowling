package bowling.domain.view;

import bowling.domain.Player;
import bowling.domain.frame.FrameRound;

import java.util.List;
import java.util.stream.IntStream;

public class ResultView {
    private static final String HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    public static void printPlayer(List<Player> players) {
        System.out.println(HEADER);
        players.stream()
                .map(Player::getPlayer)
                .forEach(System.out::println);
    }
}
