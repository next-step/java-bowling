package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Player;

import java.util.List;
import java.util.stream.IntStream;

public final class ResultView {

    private static final String SCORE_BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    private static final class ResultViewHolder {
        private static final ResultView instance = new ResultView();
    }

    private ResultView() {
    }

    public static final ResultView getInstance() {
        return ResultViewHolder.instance;
    }

    public final void printScoreBoard(Player player, Frames frames) {
        System.out.println(SCORE_BOARD_HEADER);
        System.out.print(nameFormat(player.name()));
        List<Frame> frameList = frames.frames();
        IntStream.range(0, 9)
                .mapToObj(index -> frameList.get(index))
                .forEach(this::printFrame);
        printFinalFrame(frameList.get(9));
        System.out.println();
    }

    private final void printFrame(Frame frame) {
        if(frame.size() == 0) {
            printEmpty(frame);
        }
        if(frame.size() == 1) {
            printOneScore(frame);
        }
        if(frame.size() == 2) {
            printTwiceScore(frame);
        }
    }

    private void printFinalFrame(Frame frame) {
        if(frame.size() == 0) {
            printEmpty(frame);
            return;
        }
        if(frame.size() == 1) {
            printOneScore(frame);
            return;
        }
        if(frame.size() == 2) {
            printTwiceScore(frame);
            return;
        }
        System.out.println(frame.size());
        System.out.print(String.format("  %-3s ", (frame.firstCount()+"|"+ frame.secondCount()+"|"+ frame.thirdCount()))+"|");
    }

    private void printTwiceScore(Frame frame) {
        System.out.print(String.format("  %-3s ", (frame.firstCount()+"|"+ frame.secondCount()))+"|");
    }

    private void printOneScore(Frame frame) {
        System.out.print(normalFormat(String.valueOf(frame.firstCount())));
    }

    private void printEmpty(Frame frame) {
        System.out.print("      |");
    }

    private String nameFormat(String sentence) {
        return "|"+String.format(" %4s ", sentence)+"|";
    }
    private String normalFormat(String sentence) {
        return String.format("  %-3s ", sentence)+"|";
    }
}
