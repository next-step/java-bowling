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

    public int getHitCount(int playCount) {
        showTextLine(String.format("%d 프레임 투구", playCount));
        return getNumber();
    }

    public void showFrameSetResult(List<FrameSet> results) {
        showText("|   ");

        for (FrameSet set : results) {
            showText(getFrameStateText(set.getState()));

            if (set.isEnd()) {
                showText("   |");
            } else if (set.isEndedState()) {
                showText("   |   ");
            }
        }

        nextLine();
    }

    private String getFrameStateText(State state) {
        StringBuilder sb = new StringBuilder();

        if (state.getPlayCount() == 2) {
            sb.append(" | ");
        }

        if (state instanceof Strike) {
            sb.append("  X ");
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

        return sb.toString();
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
