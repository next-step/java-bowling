package bowling.ui;

import java.util.Scanner;

import static bowling.ui.BowlingGameDisplay.display;

public final class BowlingGameInput {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputPlayerName(){
        display("플레이어 이름은(3 english letters)?");
        return scanner.next();
    }

    public static int inputFallenPins(int frameIndex){
        display(String.format("%d프레임 투구 : ", frameIndex));
        return scanner.nextInt();
    }
}
