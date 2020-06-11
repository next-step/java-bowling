package bowling.step2.controller;

import bowling.step2.view.*;
import bowling.step2.domain.*;

import java.util.Collections;
import java.util.function.Function;

public class BowlingController {
    private static final InputView inputView = InputView.getInstance();
    private static final ResultView resultView = ResultView.getInstance();

    public static void main(String[] args) {
        PlayerName playerName = inputView.inputName();
        Players players = Players.of(Collections.singletonList(playerName));
        Frames frames = Frames.init(players);
        resultView.printFrames(frames, players);
        frames.stream().forEach(frame -> {
            Score firstScore = inputView.inputScore(frame);
            frame.addScore(playerName, firstScore);
            resultView.printFrames(frames, players);
            if (firstScore == Score.getStrike()) {
                return;
            }
            Score secondScore = inputView.inputScore(frame);
            frame.addScore(playerName, secondScore);
            resultView.printFrames(frames, players);
        });
    }
}
