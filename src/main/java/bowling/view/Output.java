package bowling.view;

import bowling.domain.Board;
import bowling.domain.Frame;
import bowling.domain.Player;

public class Output {
    public static void outputBoard(Board board, Player player) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        StringBuilder sb = new StringBuilder();
        System.out.print("|  "+player.getName()+" |");
        for (Frame frame : board.getFrames()) {
            sb.append("  ");
            sb.append(frame.convert());
            for (int i = sb.length(); i < 6; i++) {
                sb.append(" ");
            }
            sb.append("|");
            System.out.print(sb.toString());
            sb.setLength(0);
        }
        System.out.println();

    }

}
