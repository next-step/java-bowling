package step2.view;

import step2.domain.Frame;
import step2.domain.Frames;
import step2.domain.GameHistories;
import step2.domain.Player;
import step2.domain.dto.PlayerDTO;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingResultView implements ResultView {
    public static final String lineHeaderString = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    public static final String lineEmptyBodyString = "|%6.6s|      |      |      |      |      |      |      |      |      |      |";
    private static final StringBuilder sb = new StringBuilder();
    public static final String WALL_STR = "|";
    public static final String MSG_PITCHES_FRAME = "%d 프레임 투구: %s";


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
    public void drawFrames(PlayerDTO playerDTO) {
        clearStringBuilder();
        printHeader();

        Player player = playerDTO.getPlayer();
        appendFrame(player.getName());

        Frames frames = playerDTO.getFrames();
        Frame frame = frames.getHead();
        while (frame.hasNext()) {
            appendFrame(frame.getResultString());
            frame = frame.next();
        }

        System.out.println(sb.toString());
    }

    @Override
    public void drawFrames(GameHistories histories) {
        histories.forEach(history -> {
            clearStringBuilder();

            System.out.printf((MSG_PITCHES_FRAME) + "%n", history.getCurrentFrameNo(), history.getPitchesPoint());
            printHeader();
            printBody(history.getPlayer(), history.getMarks());
            System.out.println();
        });
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
