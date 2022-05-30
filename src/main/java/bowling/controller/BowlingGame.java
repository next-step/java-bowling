package bowling.controller;

import bowling.domain.*;
import bowling.domain.state.Miss;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private static final int FIRST_ROUND = 1;
    private static final int FINAL_ROUND = 10;
    private static final int FINAL_MAX_ROUND = 3;

    public static void main(String[] args) {
        User user = InputView.inputLetters();
        Frame frame = new NormalFrame(FIRST_ROUND);
        FrameScores frameScores = new FrameScores();
        List<Frame> frames = new ArrayList<>();
        frames.add(frame);

        ResultView.printFirstRoundFrame(user);
        int i = 1;
        while (i < FINAL_ROUND) {
            int pin = InputView.inputBowl(i).getPins();
            Frame nextFrame = frames.get(i - 1).bowl(pin);

            if(frames.get(i - 1).getState().isFinish()) {
                FrameScore frameScore = new FrameScore(frames.get(i - 1).getState().getScore().getScore());
                frameScore.addTotalScore(frame.getFrameScore().getScore());
                frameScores.addScore(frameScore);

                ResultView.printState(user, frames.get(i - 1).expression(), i);
                System.out.println(frameScores);
                frames.add(nextFrame);
                i++;
                continue;
            }
            ResultView.printState(user, frames.get(i - 1).expression(), i);
            System.out.println(frameScores);
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
