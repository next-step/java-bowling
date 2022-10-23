package bowling.controller;

import bowling.domain.Pin;
import bowling.domain.PlayerName;
import bowling.domain.frame.Frames;
import bowling.domain.scorestrategy.RandomScoreStrategy;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGameController {

    public static void main(String[] args) {
        PlayerName playerName = new PlayerName(InputView.askPlayerName());
        Frames frames = new Frames();
        OutputView.printStart(frames, playerName);

        while (!frames.isFinished()) {
            Pin now = frames.bowl(new RandomScoreStrategy());
            OutputView.print(frames, playerName, now);
        }

    }


}
