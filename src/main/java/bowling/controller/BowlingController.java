package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.Name;
import bowling.domain.Names;
import bowling.domain.PeopleSize;
import bowling.domain.Scoreboards;
import bowling.domain.score.Score;
import bowling.view.Input;
import bowling.view.Output;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingController {

    public void start() {
        Names names = createNames(new PeopleSize(Input.inputPeopleSize()));
        Scoreboards scoreboards = new Scoreboards(names);
        BowlingGame bowlingGame = new BowlingGame(scoreboards);
        play(bowlingGame, names);
    }

    private Names createNames(PeopleSize peopleSize) {
        return new Names(IntStream.range(0, peopleSize.size())
                .mapToObj(i -> new Name(Input.inputName()))
                .collect(Collectors.toList()));
    }

    private void play(BowlingGame bowlingGame, Names names) {
        if (!bowlingGame.isEnd()) {
            playRound(bowlingGame, names);
            play(bowlingGame, names);
        }
    }

    private void playRound(BowlingGame bowlingGame, Names names) {
        for (Name name : names.names()) {
            playTurn(bowlingGame, name);
        }
        bowlingGame.setNextRound();
    }

    private void playTurn(BowlingGame bowlingGame, Name name) {
        if (!bowlingGame.isEndTurn(name)) {
            int round = bowlingGame.round();
            Score score = Score.of(Input.inputScore(round));
            Scoreboards scoreboards = bowlingGame.play(score, name);
            Output.printScoreboard(scoreboards);
            playTurn(bowlingGame, name);
        }
    }
}
