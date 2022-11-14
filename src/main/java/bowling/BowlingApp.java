package bowling;

import bowling.domain.Bowlings;
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

            Bowlings bowlings = new Bowlings(usernames);
            List<ScoreResult> results = new ArrayList<>();
            while (!bowlings.isFinish()) {
                Username username = bowlings.currentPlayerName();
                OutputView.printPinAskQst(username);
                ScoreResult scoreResult = getKnockedDownPins(inputView, bowlings);
                results.add(scoreResult);
                OutputView.printScore(bowlings, results);
            }
        } catch (IllegalArgumentException e) {
            OutputView.printConsole(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static ScoreResult getKnockedDownPins(InputView inputView, Bowlings bowlings) {
        try {
            Integer knockDownPinNumber = inputView.getNumber();
            return bowlings.play(knockDownPinNumber);
        } catch (IllegalArgumentException e) {
            OutputView.printConsole(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        throw new AssertionError();
    }
}
