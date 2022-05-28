package bowling.controller;

import bowling.domain.Frame;
import bowling.domain.NormalFrame;
import bowling.domain.User;
import bowling.domain.state.Miss;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {
    private static final int FIRST_ROUND = 1;
    private static final int FINAL_ROUND = 10;
    private static final int FINAL_MAX_ROUND = 3;

    public static void main(String[] args) {
        User user = InputView.inputLetters();
        Frame frame = new NormalFrame(FIRST_ROUND);

        ResultView.printFirstRoundFrame(user);
        int i = 1;
        while (i < FINAL_ROUND) {
            int pin = InputView.inputBowl(i).getPins();
            Frame currentFrame = frame.bowl(pin);
            if(frame.getState().isFinish()) {
                ResultView.printState(user, frame.expression(), i);
                frame = currentFrame;
                i++;
                continue;
            }
            ResultView.printState(user, frame.expression(), i);
        }

        int count = 1;
        while (true) {
            int pin = InputView.inputBowl(FINAL_ROUND).getPins();
            frame.bowl(pin);
            ResultView.printState(user, frame.expression(), i);
            if(count == FINAL_MAX_ROUND || frame.getState() instanceof Miss) {
                break;
            }
            i++;
            count++;
        }
    }
}
