package bowling.view;

import bowling.domain.Pin;

import java.util.Scanner;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);

    public static Pin nextPin(int currentFrameNumber) {
        System.out.print(currentFrameNumber + "프레임 투구 : ");
        return new Pin(sc.nextInt());
    }

}
