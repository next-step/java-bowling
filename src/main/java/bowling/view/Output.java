package bowling.view;

import bowling.domain.frame.Frames;
import bowling.domain.Game;
import bowling.domain.Player;

import java.util.stream.IntStream;

public class Output {
    public static void outputBoard(Game game, Player player) {
        Frames frames = game.getBoard();
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        StringBuilder sb = new StringBuilder();
        System.out.print("|  " + player.getName() + " |");
        frames.getFrames().stream().forEach(frame -> {
            if (!frame.isLastFrame()) {
                sb.append("  " + frame.convert());
                IntStream.rangeClosed(frame.convert().length() + 2, 5).forEach(i -> sb.append(" "));
                sb.append("|");
            }
            if (frame.isLastFrame()) {
                sb.append(" " + frame.convert());
                IntStream.rangeClosed(frame.convert().length() + 1, 5).forEach(i -> sb.append(" "));
                sb.append("|");
            }
        });


//        IntStream.rangeClosed(0, 9).forEach(index -> {
//            if (game.isRecorded(index) && game.isLastFrame()) {
//                sb.append(" " + frames.getFrames().get(index).convert());
//            }
//            if (game.isRecorded(index) && !game.isLastFrame()) {
//                sb.append("  " + frames.getFrames().get(index).convert());
//            }
//
//            IntStream.rangeClosed(sb.length(), 7 * (index + 1) - 2).forEach(i -> {
//                        sb.append(" ");
//                    }
//            );
//            sb.append("|");
//        });

        System.out.print(sb);
        sb.setLength(0);
        System.out.println();
    }
}
