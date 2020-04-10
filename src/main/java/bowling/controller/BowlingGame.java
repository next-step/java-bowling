package bowling.controller;

import bowling.domain.Frame;
import bowling.domain.PlayerName;
import bowling.domain.RandomGenerator;
import bowling.view.InputView;

import java.util.List;

public class BowlingGame {
    public static void start(){
        RandomGenerator randomGenerator = new RandomGenerator();
        PlayerName playerName = PlayerName.of(InputView.getPlayerName());
        List<Frame> tenFrames = Frame.createTenFrames(randomGenerator);
    }
}
