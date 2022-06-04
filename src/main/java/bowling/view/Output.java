package bowling.view;

import bowling.domain.Player;
import bowling.domain.Score;

import static bowling.util.Const.*;

public class Output {
    public static void printFrame(Player player) {
        printContent(player);
    }

    public static void printFrame(int i, Player player) {
        System.out.println("Frame: " + (i == 11 ? "bonus" : i));
        printContent(player);
        printSubtotal(player);
    }

    private static void printSubtotal(Player player) {
        String frame = BEGIN_STR + player.name() + "  ";
        frame += player.subtotals()
                .stream()
                .map(s -> s.waiting() > 0 ? "" : s.score() + "")
                .map(s -> format(s))
                .reduce("", (acc, cur) -> acc + DELIMITER_STR + cur);
        System.out.println(frame + "|");
    }

    private static void printContent(Player player) {
        String frame = HEADER_STR + "\n" + BEGIN_STR + player.name() + "  ";
        frame += player.scores()
                .stream()
                .map(v -> Score.scoreBoard(v))
                .reduce("", (acc, cur) -> acc + DELIMITER_STR + cur);
        System.out.println(frame + "|");
    }

    public static String format(String string) {
        return String.format("%-5s", string);
    }

    public static void printFrames(Player player) {
        player.frames();
    }
}
