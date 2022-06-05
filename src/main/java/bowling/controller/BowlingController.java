package bowling.controller;

import bowling.domain.Frame;
import bowling.domain.FramesList;
import bowling.domain.Pins;
import bowling.domain.Users;
import bowling.view.InputView;
import bowling.view.ResultView;


public class BowlingController {
    private static final int FINAL_ROUND = 10;

    public static void start(Users users) {
        int round = 1;
        FramesList userFrames = new FramesList(users);

        ResultView.printInit(users);
        while (round < FINAL_ROUND) {
            for (int i = 0; i < users.size(); i++) {
                playRound(userFrames, users, i, round);
            }
            round++;
        }

    }



    public static void playRound(FramesList framesList, Users users, int i, int round) {
        while (!framesList.isFramesFinish(i, round)) {
            Pins pins = InputView.inputBowl(users.getUsers().get(i).getLetters());
            Frame nextFrame = framesList.getUserFrames().get(i).getFrame(round).bowl(pins.getPins());
            if (!framesList.getFrame(i, round).equals(nextFrame)) {
                framesList.getUserFrames().get(i).add(nextFrame);
            }
            ResultView.printState(users ,framesList, round);
        }
    }

}
