package bowling.view;

import bowling.domain.Player;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_NAME_STRING = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_DONWPIN_STRING = "%d프레임 투구 : ";

    public static Player inputPlayer(){
        System.out.println(INPUT_NAME_STRING);
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        return new Player(name);
    }

    public static int inputDownPinCount(int index){
        System.out.printf(INPUT_DONWPIN_STRING, index + 1);
        Scanner scanner = new Scanner(System.in);
        String downPinCount = scanner.nextLine();
        return Integer.parseInt(downPinCount);
    }
}
