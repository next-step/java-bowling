package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.player.Player;
import bowling.domain.player.Players;
import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class OutputView {
    private static final String TABLE_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String COLUMN_INIT = "|";
    private static final String COLUMN_SEPARATOR = "  %3s |";
    private static final String EMPTY = "  ";
    private static final int MAX_FRAME_NO = 10;

    public void printBoard(Players players) {
        System.out.println(TABLE_HEADER);
        players.getPlayers().stream().forEach(player -> {
            printPin(player);
            printScore(player);
        });
        System.out.println();
    }

    private void printPin(Player player) {
        System.out.printf(COLUMN_INIT + COLUMN_SEPARATOR, player.getPlayerName());

        player.getFrames()
                .forEach(frame ->
                        System.out.printf(COLUMN_SEPARATOR, displays(frame))
                );

        IntStream.range(0, MAX_FRAME_NO - player.getFrames().size())
                .forEach(i ->
                        System.out.printf(COLUMN_SEPARATOR, EMPTY)
                );
        System.out.println();
    }

    private void printScore(Player player) {
        System.out.printf(COLUMN_INIT + COLUMN_SEPARATOR, EMPTY);

        int total = 0;
        for(Frame frame: player.getFrames()){
            total += frame.total();
            System.out.printf(COLUMN_SEPARATOR, displayTotal(total, frame.total()));
        }


        IntStream.range(0, MAX_FRAME_NO - player.getFrames().size())
                .forEach(i ->
                        System.out.printf(COLUMN_SEPARATOR, EMPTY)
                );
        System.out.println();
    }

    private String displayTotal(int savedTotal, int currentTotal){
        if(currentTotal == 0){
            return EMPTY;
        }
        return String.valueOf(savedTotal);
    }

    private String displays(Frame frame) {

        List<String> scores = new ArrayList<>();
        if (frame.hasFirstPin()) {
            scores.add(display(frame.firstCount()));
        }
        if (frame.hasSecondPin()) {
            scores.add(display(frame.firstCount(), frame.secondCount()));
        }
        if (frame.hasBonus()) {
            scores.add(display(frame.bonusFirstCount()));
        }
        if (frame.hasBonusSecond()) {
            scores.add(display(frame.bonusSecondCount()));
        }

        if (scores.isEmpty()) {
            return EMPTY;
        }
        return StringUtils.join(scores, "|");
    }

    private String display(int firstCount) {
        if (firstCount == 10) {
            return "x";
        }
        if (firstCount == 0) {
            return "-";
        }
        return String.valueOf(firstCount);
    }

    private String display(int firstCount, int secondCount) {
        if (firstCount + secondCount == 10) {
            return "/";
        }
        return display(secondCount);
    }
}
