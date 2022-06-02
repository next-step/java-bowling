package bowling.controller;


import bowling.domain.Bowling;
import bowling.domain.RandomScoreStrategy;
import bowling.engine.ScoreStrategy;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {

    public static void start() {
        ScoreStrategy scoreStrategy = new RandomScoreStrategy();
        Bowling bowling = new Bowling(scoreStrategy);

        String name = InputView.inputNames();

        ResultView.drawBowling(name, bowling);
    }
}
