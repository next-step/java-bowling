package bowling;

import bowling.domain.state.*;

import java.util.Scanner;

public class View {

    private Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
    }

    public int getHitCount(int playCount) {
        showText(String.format("%d 프레임 투구", playCount));
        return getNumber();
    }

    public void showFrameSetResult(State state) {
        showText(getFrameStateText(state));
    }

    private String getFrameStateText(State state) {
        if (state instanceof Strike) {
            return "X";
        }

        if (state instanceof Hit || state instanceof Miss) {
            return String.valueOf(state.getHitCount());
        }

        if (state instanceof Gutter) {
            return "-";
        }

        if (state instanceof Spare) {
            return "| /";
        }

        throw new IllegalArgumentException();
    }

    private void showText(String text) {
        System.out.println(text);
    }

    private int getNumber() {
        return Integer.parseInt(getLine());
    }
    private String getLine() {
        return scanner.nextLine();
    }
}
