package view;

import domain.frames.NormalFrame;
import domain.Player;

import java.util.Scanner;

public class InputView {

    public static Player getPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("플레이어 이름(3 english letters)?: ");
        return new Player(scanner.nextLine());
    }

    public static int getFrameThrow(NormalFrame normalFrame) {
        System.out.println(normalFrame.getFrameNumber() + "프레임 투구 : ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
