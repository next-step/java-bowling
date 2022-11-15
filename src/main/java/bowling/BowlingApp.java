package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.ScoreResult;
import bowling.domain.Username;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingApp {

    public static void main(String[] args) {

        try (InputView inputView = new InputView(new BufferedReader(new InputStreamReader(System.in)))) {
            OutputView.printPeopleNumberAskQst();
            Integer peopleNumber = inputView.getNumber();
            List<Username> usernames = IntStream.range(0, peopleNumber)
                    .mapToObj((index) -> {
                        OutputView.printUsernameAskQst(index);
                        return new Username(inputView.getUsername());
                    }).collect(Collectors.toList());

            BowlingGame bowlingGame = new BowlingGame(usernames);
            List<ScoreResult> results = new ArrayList<>();
            while (!bowlingGame.isFinish()) {
                Username username = bowlingGame.currentPlayerName();
                OutputView.printPinAskQst(username);
                ScoreResult scoreResult = getKnockedDownPins(inputView, bowlingGame);
                results.add(scoreResult);
                OutputView.printScore(bowlingGame, results);
            }
        } catch (IllegalArgumentException e) {
            OutputView.printConsole(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static ScoreResult getKnockedDownPins(InputView inputView, BowlingGame bowlingGame) {
        try {
            Integer knockDownPinNumber = inputView.getNumber();
            return bowlingGame.play(knockDownPinNumber);
        } catch (IllegalArgumentException e) {
            OutputView.printConsole(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        throw new AssertionError();
    }
}
