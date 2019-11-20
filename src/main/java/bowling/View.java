package bowling;

import bowling.domain.FrameSet;
import bowling.domain.state.*;

import java.util.List;
import java.util.Scanner;

public class View {

    private Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
    }

    public String getName() {
        showTextLine("플레이어 이름은(3 english letters)?");
        return getLine();
    }
    public int getHitCount(int playCount) {
        showTextLine(String.format("%d 프레임 투구", playCount));
        return getNumber();
    }

    public void showFrameSetResult(String playerName, List<FrameSet> results) {
        showBoardTitle();
        showScore(playerName, results);
    }

    private void showScore(String playerName, List<FrameSet> results) {
        showText(String.format("|  %s |  ", playerName));

        for (FrameSet set : results) {
            showFrameScore(set.getState());
            showDividerIfPossible(set);
        }

        nextLine();
    }

    private void showFrameScore(State state) {
        StringBuilder sb = new StringBuilder();

        if (state.getPlayCount() == 2) {
            sb.append("|");
        }

        if (state instanceof Strike) {
            sb.append(" X ");
        }

        if (state instanceof Hit || state instanceof Miss) {
            sb.append(state.getHitCount());
        }

        if (state instanceof Gutter) {
            sb.append("-");
        }

        if (state instanceof Spare) {
            sb.append("/");
        }

        showText(sb.toString());
    }

    private void showDividerIfPossible(FrameSet set) {
        if (set.isEnd()) {
            showText(" |");
        } else if (set.isEndedState()) {
            showText(" |  ");
        }
    }

    private void showBoardTitle() {
        showTextLine("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    private void nextLine() {
        showText(System.lineSeparator());
    }

    private void showText(String text) {
        System.out.print(text);
    }

    private void showTextLine(String text) {
        System.out.println(text);
    }

    private int getNumber() {
        return Integer.parseInt(getLine());
    }

    private String getLine() {
        return scanner.nextLine();
    }
}
