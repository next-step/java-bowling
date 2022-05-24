package bowling.view;

import bowling.domain.Frame;
import bowling.domain.User;
import bowling.domain.state.Miss;

import java.util.ArrayList;
import java.util.List;

public class ResultView {
    private static final String
            MENU = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10      |";

    private static final String EMPTY = "   ";
    private static final String FINAL_EMPTY = "       ";
    private static final String BLANK = "   ";
    private static final String PIPELINE = "|";
    private static final int FINAL_ROUND = 10;
    private final User user;
    private final Frame frame;
    private final List<String> resultState;

    public ResultView(User user, Frame frame) {
        this.user = user;
        this.frame = frame;
        this.resultState = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            resultState.add(EMPTY);
        }
        resultState.add(FINAL_EMPTY);
    }

    private void printState() {
        System.out.println(MENU);
        System.out.print(user.getName());
        this.resultState.forEach(s -> System.out.print("  " + s + " " + PIPELINE));
        System.out.println();
    }

    private void printFinalRound(Frame result) {
        int count = 1;
        while (true) {
            int pin = InputView.inputBowl(FINAL_ROUND).getPins();
            result.bowl(pin);
            if(count == 3 || result.getState() instanceof Miss) {
                resultState.set(resultState.size() - 1, result.expression());
                printState();
                break;
            }
            count++;
        }
    }

    public void print() {
        printState();
        Frame result = this.frame;
        int i = 1;
        while (i < FINAL_ROUND) {
            int pin = InputView.inputBowl(i).getPins();
            String state = result.bowl(pin).getState().expression();
            if (state != null) {
                resultState.set(i - 1, state + BLANK.substring(state.length()));
            }
            if (result.nextFrame() != null) {
                result = result.nextFrame();
                i++;
            }
            printState();
        }

        printFinalRound(result);
    }

}
