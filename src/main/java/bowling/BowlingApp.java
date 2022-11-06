package bowling;

import bowling.domain.Bowling;
import bowling.domain.BowlingRound;
import bowling.domain.ScoreResult;
import bowling.domain.Username;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BowlingApp {

    public static void main(String[] args) {

        try (InputView inputView = new InputView(new BufferedReader(new InputStreamReader(System.in)))) {
            OutputView.printUsernameAskQst();
            Username username = new Username(inputView.getUsername());

            Bowling bowling = new Bowling();
            List<ScoreResult> results = new ArrayList<>();
            while (!bowling.isFinish()) {
                BowlingRound round = bowling.currentRound();
                OutputView.printPinAskQst(round);
                ScoreResult scoreResult = getKnockedDownPins(inputView, bowling);
                results.add(scoreResult);
                OutputView.printScore(bowling, username, results);
            }
        } catch (IllegalArgumentException e) {
            OutputView.printConsole(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static ScoreResult getKnockedDownPins(InputView inputView, Bowling bowling) {
        try {
            Integer knockDownPinNumber = inputView.getKnockDownPinNumber();
            return bowling.play(knockDownPinNumber);
        } catch (IllegalArgumentException e) {
            OutputView.printConsole(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        throw new AssertionError();
    }
}
