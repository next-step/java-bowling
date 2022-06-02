package bowling.controller;

import bowling.domain.*;
import bowling.domain.state.Miss;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {
    private static final int FIRST_ROUND = 1;
    private static final int FINAL_ROUND = 10;
    private static final int FINAL_MAX_ROUND = 3;

    public static void main(String[] args) {
        User user = InputView.inputLetters();
        Frames frames = new Frames(new NormalFrame(FIRST_ROUND));

        ResultView.printFirstRoundFrame(user);
        int i = 1;
        while (i < FINAL_ROUND) {
            int pin = InputView.inputBowl(i).getPins();
            Frame nextFrame = frames.getFrame(i).bowl(pin);

            if(frames.getFrame(i).getState().isFinish()) {
                ResultView.printState(user, frames.getFrame(i).expression(), i);
                ResultView.printScore(frames.calculateTotalScore(i));
                frames.add(nextFrame);
                i++;
                continue;
            }
            ResultView.printState(user, frames.getFrame(i).expression(), i);
        }

        int count = 1;
        while (true) {
            int pin = InputView.inputBowl(FINAL_ROUND).getPins();
            frames.getFrame(FINAL_ROUND).bowl(pin);
            ResultView.printState(user, frames.getFrame(FINAL_ROUND).expression(), i);
            ResultView.printScore(frames.calculateTotalScore(i));
            if(count == FINAL_MAX_ROUND || frames.getFrame(FINAL_ROUND).getState() instanceof Miss) {
                break;
            }
            count++;
        }
    }
}
