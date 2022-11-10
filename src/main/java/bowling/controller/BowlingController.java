package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.Name;
import bowling.domain.Score;
import bowling.domain.Scoreboard;
import bowling.view.Input;
import bowling.view.Output;

public class BowlingController {

    public void start() {
        Name name = new Name(Input.inputName());
        Scoreboard scoreboard = new Scoreboard(name);
        BowlingGame bowlingGame = new BowlingGame(scoreboard);
        play(bowlingGame);
    }

    private void play(BowlingGame bowlingGame) {
        if (!bowlingGame.isEnd()) {
            int round = bowlingGame.round();
            Score score = Score.of(Input.inputScore(round));
            Scoreboard scoreboard = bowlingGame.play(score);
            Output.printScoreboard(scoreboard);
            play(bowlingGame);
        }
    }
}
