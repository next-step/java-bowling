package step3.view;

import step3.domain.GameHistory;
import step3.domain.Player;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingResultView implements ResultView {
    public static final String lineHeaderString = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    public static final String lineEmptyBodyString = "|%6.6s|      |      |      |      |      |      |      |      |      |      |";
    private static final StringBuilder sb = new StringBuilder();
    public static final String WALL_STR = "|";


    private void printEmptyBody(String name) {
        System.out.printf((lineEmptyBodyString) + "%n", name);
        System.out.println();
    }

    @Override
    public void drawHeaderFrame(int frameNo, int pitchesCount) {
        clearStringBuilder();
        printHeader();
    }

    @Override
    public void drawFrame(Player player, GameHistory gameHistory) {
        clearStringBuilder();
        printHeader();
        printBody(player, gameHistory.getMarks());
        printBody(player, gameHistory.getPoints());
        System.out.println();
    }

    private void printBody(Player player, List<String> strings) {
        clearStringBuilder();
        appendWall();
        appendFrame(formatted(player.getName()));
        appendWall();
        appendFrame(strings.stream()
                .map(this::formatted)
                .collect(Collectors.joining(WALL_STR)));
        appendWall();

        sb.append(System.lineSeparator());
        System.out.print(sb.toString());
    }

    private void appendWall() {
        sb.append(WALL_STR);
    }

    @Override
    public void drawEmptyLine(String name) {
        printHeader();
        printEmptyBody(name);
    }

    private String formatted(String content) {
        return String.format("%6.6s", content);
    }

    private void appendFrame(String content) {
        sb.append(content);
    }

    private void clearStringBuilder() {
        sb.delete(0, sb.length());
    }

    private void printHeader() {
        System.out.println(lineHeaderString);
    }
}
