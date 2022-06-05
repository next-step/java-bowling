package bowling.controller;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingController {

    public static void start(Users users) {
        int round = 1;
        FramesList userFrames = new FramesList(users);

        ResultView.printInit(users);
        while(round < 10) {
            for (int i = 0; i< users.size(); i++) {
                if(userFrames.isFramesFinish(i, round)) {
                    continue;
                }
                Pins pins = InputView.inputBowl(users.getUsers().get(i).getLetters());
                Frame nextFrame = userFrames.getUserFrames().get(i).getFrame(round).bowl(pins.getPins());

                if(!userFrames.getUserFrames().get(i).getFrame(round).equals(nextFrame)) {
                    userFrames.getUserFrames().get(i).add(nextFrame);
                }
            }
            if(userFrames.isFinishAllFromFrame(round)) {
                round++;
            }
        }
    }
}
