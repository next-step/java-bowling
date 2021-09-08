package bowling;

import bowling.domain.Bowling;
import bowling.view.BowlingConsoleInputView;
import bowling.view.BowlingConsoleOutputView;

import java.util.Scanner;

public class ApplicationRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BowlingConsoleInputView inputView = new BowlingConsoleInputView(scanner);
        BowlingConsoleOutputView outputView = new BowlingConsoleOutputView();

        String playerText = inputView.enterPlayer();

        Bowling bowling = new Bowling(playerText);

        int order = 1;

        while (!bowling.isFinish()) {
            bowling.roll(inputView.enterScore(order));

//            for(Frame frame:bowling.getFrames().elements()){
//                System.out.println(frame);
//            }
            outputView.print(bowling);
            order++;
        }
    }
}
