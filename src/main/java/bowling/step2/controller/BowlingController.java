package bowling.step2.controller;

import bowling.step2.domain.frame.Frame;
import bowling.step2.domain.frame.NormalFrame;
import bowling.step2.domain.scores.NormalScores;
import bowling.step2.domain.scores.Scores;
import bowling.step2.view.*;
import bowling.step2.domain.*;

import java.util.Collections;
import java.util.stream.IntStream;

public class BowlingController {
    private static final InputView inputView = InputView.getInstance();
    private static final ResultView resultView = ResultView.getInstance();

    public static void main(String[] args) {
        Player player = inputView.inputName();
        Players players = Players.of(Collections.singletonList(player));
        PlayerFrames playerFrames = PlayerFrames.init(players);

        resultView.printFrames(playerFrames, players);

    }
}
