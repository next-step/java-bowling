package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.Name;
import bowling.domain.Names;
import bowling.domain.PeopleSize;
import bowling.domain.Scoreboard;
import bowling.domain.Scoreboards;
import bowling.domain.score.Score;
import bowling.view.Input;
import bowling.view.Output;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingController {

    public void start() {
        Names names = createNames(new PeopleSize(Input.inputPeopleSize()));

        Scoreboards scoreboards = new Scoreboards();
        createScoreboards(names, scoreboards);
        BowlingGame bowlingGame = new BowlingGame(scoreboards);
        play(bowlingGame, names);
    }

    private Names createNames(PeopleSize peopleSize) {
        return new Names(IntStream.range(0, peopleSize.size())
                .mapToObj(i -> new Name(Input.inputName()))
                .collect(Collectors.toList()));
    }

    private static void createScoreboards(Names names, Scoreboards scoreboards) {
        for (Name name : names.names()) {
            scoreboards.add(new Scoreboard(name));
        }
    }

    private void play(BowlingGame bowlingGame, Names names) {
        if (!bowlingGame.isEnd()) {
            playRound(bowlingGame, names.names().size());
            play(bowlingGame, names);
        }
    }

    private void playRound(BowlingGame bowlingGame, int peopleSize) {
        for (int turn = 0; turn < peopleSize; turn++) {
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
