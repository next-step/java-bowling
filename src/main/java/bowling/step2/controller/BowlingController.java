package bowling.step2.controller;

import bowling.step2.domain.frame.Frame;
import bowling.step2.domain.frame.NormalFrame;
import bowling.step2.domain.scores.NormalScores;
import bowling.step2.domain.scores.Scores;
import bowling.step2.view.*;
import bowling.step2.domain.*;

import java.util.Collections;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BowlingController {
    private static final InputView inputView = InputView.getInstance();
    private static final ResultView resultView = ResultView.getInstance();

    private BowlingController () {}

    public static void newPlayerFrame (Player player, Frame frame) {

    }

    public static void main(String[] args) {
        // Player player = inputView.inputName();
        Player player = Player.valueOf("hji");
        PlayerFrames playerFrames = PlayerFrames.init(player);

        resultView.printFrames(playerFrames);



    }
}
