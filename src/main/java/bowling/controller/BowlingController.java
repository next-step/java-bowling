package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.Name;
import bowling.domain.PeopleSize;
import bowling.domain.Scoreboard;
import bowling.domain.Scoreboards;
import bowling.domain.score.Score;
import bowling.view.Input;
import bowling.view.Output;

public class BowlingController {

    public void start() {
        PeopleSize peopleSize = new PeopleSize(Input.inputPeopleSize());
        Scoreboards scoreboards = new Scoreboards();
        createScoreboards(peopleSize, scoreboards);
        BowlingGame bowlingGame = new BowlingGame(scoreboards);
        play(bowlingGame, peopleSize);
    }

    private static void createScoreboards(PeopleSize peopleSize, Scoreboards scoreboards) {
        for (int i = 0; i < peopleSize.size(); i++) {
            Name name = new Name(Input.inputName());
            scoreboards.add(new Scoreboard(name));
        }
    }

    private void play(BowlingGame bowlingGame, PeopleSize peopleSize) {
        if (!bowlingGame.isEnd()) {
            playRound(bowlingGame, peopleSize);
            play(bowlingGame, peopleSize);
        }
    }

    private void playRound(BowlingGame bowlingGame, PeopleSize peopleSize) {
        for (int turn = 0; turn < peopleSize.size(); turn++) {
            playTurn(bowlingGame, turn);
        }
        bowlingGame.setNextRound();
    }

    private void playTurn(BowlingGame bowlingGame, int turn) {
        if (!bowlingGame.isEndTurn(turn)) {
            int round = bowlingGame.round();
            Score score = Score.of(Input.inputScore(round));
            Scoreboards scoreboards = bowlingGame.play(score, turn);
            Output.printScoreboard(scoreboards);
            playTurn(bowlingGame, turn);
        }
    }
}
