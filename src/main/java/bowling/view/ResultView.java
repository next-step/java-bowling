package bowling.view;

import bowling.domain.Frame;
import bowling.domain.User;
import java.util.ArrayList;
import java.util.List;

public class ResultView {
    private static final String
            MENU = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    private static final String EMPTY = "   ";
    private static final String BLANK = "   ";
    private static final String PIPELINE = "|";

    private final User user;
    private final Frame frame;
    private final List<String> resultState;

    public ResultView(User user, Frame frame) {
        this.user = user;
        this.frame = frame;
        this.resultState = new ArrayList<>();
        for(int i = 0; i<10; i++) {
            resultState.add(EMPTY);
        }
    }

    private void printState() {
        System.out.println(MENU);
        System.out.print(user.getName());
        this.resultState.forEach(s -> System.out.print("  "+ s + " " + PIPELINE));
        System.out.println();
    }

    public void print() {
        printState();
        Frame result = this.frame;
        int i = 1;
        while(i<=10) {
            int pin = InputView.inputBowl(i).getPins();
            String state = result.bowl(pin).expression();
            if(state !=null) {
                resultState.set(i - 1, state + BLANK.substring(state.length()));
            }
            if(result.nextFrame() != null) {
                result = result.nextFrame();
                i++;
            }
            printState();
        }
    }

}
