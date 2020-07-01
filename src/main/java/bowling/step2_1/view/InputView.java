package bowling.step2_1.view;

import bowling.step2_1.domain.process.BowlingGame;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputName(){
        System.out.println("플레이어의 이름은(3 english letters)?:");
        return SCANNER.next();
    }

    public static int inputPitch(BowlingGame bowlingGame){
        System.out.println(String.format("%d프레임 투구", bowlingGame.getFrameNo()));
        return SCANNER.nextInt();
    }
}
