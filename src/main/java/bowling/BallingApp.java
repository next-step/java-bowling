package bowling;

import bowling.domain.Balling;
import bowling.domain.BallingRound;
import bowling.domain.Username;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BallingApp {

    public static void main(String[] args) {

        try(InputView inputView = new InputView(new BufferedReader(new InputStreamReader(System.in)))) {
            OutputView.printUsernameAskQst();
            Username username = new Username(inputView.getUsername());

            Balling balling = new Balling();
            while (!balling.isFinish()){
                BallingRound round = balling.currentRound();
                OutputView.printPinAskQst(round);
                Integer knockDownPinNumber = inputView.getKnockDownPinNumber();
                OutputView.printScore(balling,username);
                balling.play(knockDownPinNumber);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
