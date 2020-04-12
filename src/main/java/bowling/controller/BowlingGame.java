package bowling.controller;

import bowling.domain.PlayerName;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {
    public static void start() {
        PlayerName playerName = PlayerName.of(InputView.getPlayerName());
        Frames tenFrames = Frame.createTenFrames();

        ResultView.print(playerName, tenFrames);
    }
}