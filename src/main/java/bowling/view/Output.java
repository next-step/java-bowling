package bowling.view;

import bowling.domain.Board;
import bowling.domain.Player;

import java.util.stream.IntStream;

public class Output {
    public static void outputBoard(Board board, Player player) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        StringBuilder sb = new StringBuilder();
        System.out.print("|  " + player.getName() + " |");
        IntStream.rangeClosed(0, 9).forEach(index -> {
            if (board.isRecorded(index) && board.getFrames().get(index).isLastFrame()) {
                sb.append(" " + board.getFrames().get(index).convert());
            }
            if (board.isRecorded(index) && !board.getFrames().get(index).isLastFrame()) {
                sb.append("  " + board.getFrames().get(index).convert());
            }

            IntStream.rangeClosed(sb.length(), 7 * (index + 1) - 2).forEach(i -> {
                        sb.append(" ");
                    }
            );
            sb.append("|");
        });

        System.out.print(sb);
        sb.setLength(0);
        System.out.println();
    }
}
