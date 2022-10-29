package bowling;

import bowling.domain.Bowling;
import bowling.domain.BowlingRound;
import bowling.domain.Username;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BowlingApp {

    public static void main(String[] args) {

        try (InputView inputView = new InputView(new BufferedReader(new InputStreamReader(System.in)))) {
            OutputView.printUsernameAskQst();
            Username username = new Username(inputView.getUsername());

            Bowling bowling = new Bowling();
            while (!bowling.isFinish()) {
                BowlingRound round = bowling.currentRound();
                OutputView.printPinAskQst(round);
                getKnockedDownPins(inputView, bowling);
                OutputView.printScore(bowling, username);
            }
        } catch (IllegalArgumentException e) {
            OutputView.printConsole(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static void getKnockedDownPins(InputView inputView, Bowling bowling) throws IOException {
        try {
            Integer knockDownPinNumber = inputView.getKnockDownPinNumber();
            bowling.play(knockDownPinNumber);
        } catch (IllegalArgumentException e) {
            OutputView.printConsole(e.getMessage());
        }
    }

}
