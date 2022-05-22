package bowling.view;

import bowling.domain.Frame;
import bowling.domain.User;

public class ResultView {
    private static final String
            MENU = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    private final User user;
    private final Frame frame;

    public ResultView(User user, Frame frame) {
        this.user = user;
        this.frame = frame;
    }

    public void print() {
        System.out.println(MENU);
        for(int i = 1; i<= 9; i++) {
            this.frame.bowl(InputView.inputBowl(i).getPins());
        }
    }

}
