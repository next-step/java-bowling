package bowling.view;

import bowling.domain.Board;
import bowling.domain.Player;

public class Output {
    public static void outputBoard(Board board, Player player) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        StringBuilder sb = new StringBuilder();
        System.out.print("|  " + player.getName() + " |");
        for (int index = 1; index < 11; index++) {
            if (index < board.getFrames().size()+1) {
                if (index == 10) {
                    sb.append(" " + board.getFrames().get(index-1).convert());
                }
                if (index != 10) {
                    sb.append("  " + board.getFrames().get(index-1).convert());
                }
            }
            for (int i = sb.length(); i < 7 * index -1; i++) {
                sb.append(" ");
            }
            sb.append("|");
        }
        System.out.print(sb);
        sb.setLength(0);


//        for (Frame frame : board.getFrames()) {
//            if (frame.isLastFrame()) {
//                sb.append(" ");
//                sb.append(frame.convert());
//            }
//            if (!frame.isLastFrame()) {
//                sb.append("  ");
//                sb.append(frame.convert());
//                for (int i = sb.length(); i < 6; i++) {
//                    sb.append(" ");
//                }
//            }
//            sb.append("|");
//            System.out.print(sb);
//            sb.setLength(0);
//        }
        System.out.println();
    }

    public static void outputLastFrame(Board board, Player player) {

    }

}
