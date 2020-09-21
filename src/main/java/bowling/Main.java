package bowling;

import java.util.Scanner;

import bowling.view.InputView;
import bowling.view.OutputView;

public class Main {
    public static void main(String[] args){

        String userName = InputView.inputName();
        Bowling bowling = Bowling.start();


        OutputView outputView = OutputView.make(userName, bowling);
        while(!bowling.isTerminated()){
            outputView.printFrame();

            int bowl = InputView.inputBowl();
            bowling.bowl(bowl);

            outputView.printResult();
        }
    }
}
