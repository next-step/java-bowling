package bowling.controller;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.PlayerName;
import bowling.domain.RandomGenerator;
import bowling.view.InputView;

public class BowlingGame {
    public static void start() {
        RandomGenerator randomGenerator = new RandomGenerator();
        PlayerName playerName = PlayerName.of(InputView.getPlayerName());
        Frames tenFrames = Frame.createTenFrames(randomGenerator);
    }
}