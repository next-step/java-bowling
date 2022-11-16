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
        game(bowlingGame, names);
    }

    private Names createNames(PeopleSize peopleSize) {
        return new Names(IntStream.range(0, peopleSize.size())
                .mapToObj(i -> new Name(Input.inputName()))
                .collect(Collectors.toList()));
    }

    private void game(BowlingGame bowlingGame, Names names) {
        if (!bowlingGame.isEnd()) {
            round(bowlingGame, names);
            game(bowlingGame, names);
        }
    }

    private void round(BowlingGame bowlingGame, Names names) {
        for (Name name : names.names()) {
            turn(bowlingGame, name);
        }
        bowlingGame.setNextRound();
    }

    private void turn(BowlingGame bowlingGame, Name name) {
        if (!bowlingGame.isEndTurn(name)) {
            int round = bowlingGame.round();
            Score score = Score.of(Input.inputScore(round));
            Scoreboards scoreboards = bowlingGame.play(score, name);
            Output.printScoreboard(scoreboards);
            turn(bowlingGame, name);
        }
    }
}
