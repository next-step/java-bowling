package bowling.step2.controller;

import bowling.step2.view.*;
import bowling.step2.domain.*;

import java.util.Collections;

public class BowlingController {
    private static final InputView inputView = InputView.getInstance();
    private static final ResultView resultView = ResultView.getInstance();

    public static void main(String[] args) {
        PlayerName playerName = inputView.inputName();
        Players players = Players.of(Collections.singletonList(playerName));
        Frames frames = Frames.init(players);
        resultView.printFrames(frames, players);
        frames.stream()
              .forEach(frame -> addScores(frame, players));
    }

    public static void addScores (Frame frame, Players players) {
        players.stream().forEach(player -> {
            if (addScore(frame, player) != Score.getStrike()) {
                addScore(frame, player);
            }
        });
    }

    public static Score addScore (Frame frame, PlayerName player) {
        Score score = inputView.inputScore(frame);
        frame.addScore(player, score);
        return score;
    }
}
