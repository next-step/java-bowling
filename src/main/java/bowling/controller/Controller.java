package bowling.controller;

import bowling.domain.Bowling;
import bowling.domain.Name;
import bowling.domain.score.Pin;
import bowling.domain.score.ScoreResult;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;
import java.util.Scanner;

/**
 * Created By mand2 on 2020-12-21.
 */
public class Controller {



    public static void start() {

        Name player = InputView.askPlayerName();

        Bowling bowling = Bowling.of(player);

        ResultView.printScoreBoard(bowling.getPlayerName(), bowling.getScoreBoard());

        while (!bowling.isGameEnd()) {
            Pin pin = InputView.askPitch(bowling);
            bowling.pitch(pin);
            ResultView.printScoreBoard(bowling.getPlayerName(), bowling.getScoreBoard());
        }
    }

}
