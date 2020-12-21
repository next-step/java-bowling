package bowling.controller;

import bowling.domain.Bowling;
import bowling.domain.Name;
import bowling.domain.score.Pitch;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.Scanner;

/**
 * Created By mand2 on 2020-12-21.
 */
public class Controller {

    public static Scanner SCANNER = new Scanner(System.in);


    public static void start() {

        Name player = InputView.askPlayerName();

        Bowling bowling = Bowling.of(player);

        ResultView.printScoreBoard(bowling);

        while (!bowling.isGameEnd()) {
            Pitch pitch = InputView.askPitch(bowling);
            bowling.pitch(pitch);
            ResultView.printScoreBoard(bowling);
        }

    }

}
