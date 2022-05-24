package bowling.view;

import bowling.domain.Player;
import bowling.domain.Score;

import static bowling.util.Const.*;
import static bowling.util.Const.END_STR;

public class Output {
    public static void printFrame(Player player) {
       printContent(player);
    }

    public static void printFrame(int i, Player player) {
        System.out.println("Frame: " + i);
        printContent(player);
    }

    private static void printContent(Player player) {
        String frame = HEADER_STR + "\n" + BEGIN_STR + player.name() + DELIMITER_STR;
        frame += player.scores()
                .stream()
                .map(v -> Score.scoreBoard(v))
                .reduce("", (acc, cur) -> acc + cur + END_STR);
        System.out.println(frame);
    }
}
