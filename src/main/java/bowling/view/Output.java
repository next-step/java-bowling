package bowling.view;

import bowling.domain.Game;
import bowling.domain.Player;
import bowling.domain.frame.Frame;

import java.util.List;
import java.util.stream.IntStream;

public class Output {
    public static void outputBoard(Game game, Player player) {
        List<Frame> frames = game.getFrames();
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        StringBuilder sb = new StringBuilder();
        System.out.print("|  " + player.getName() + " |");
        IntStream.rangeClosed(0, 9).forEach(index -> {
            if (frames.size() > index && index != 9) {
                sb.append("  " + frames.get(index).convert());
                IntStream.rangeClosed(frames.get(index).convert().length(), 3).forEach(i -> sb.append(" "));
                sb.append("|");
            }
            if (frames.size() > index && index == 9) {
                sb.append(" " + frames.get(index).convert());
                IntStream.rangeClosed(0, frames.get(index).convert().length() - 2).forEach(i -> sb.append(" "));
                sb.append("|");
            }
            if (frames.size() <= index) {
                sb.append("      |");
            }
        });

        System.out.print(sb);
        sb.setLength(0);
        System.out.println();
        System.out.print("|      |");

        IntStream.rangeClosed(0, 9).forEach(index -> {

            if (frames.size() > index) {
                String result = Integer.toString(IntStream.rangeClosed(0, index)
                        .filter(i->frames.get(i).calculateScore(game).canCalucateScore())
                        .map(i->frames.get(i).calculateScore(game).getScore()).reduce(0,Integer::sum));
                sb.append("  ");
                sb.append(result);
                IntStream.rangeClosed(result.length(),3).forEach((i)->sb.append(" "));
                sb.append("|");
            }
            if (frames.size() <= index) {
                sb.append("      |");
            }
        });
        System.out.print(sb);
        sb.setLength(0);
        System.out.println();
    }
}
