package bowling.view;

import bowling.controller.Controller;
import bowling.domain.Bowling;
import bowling.domain.Name;
import bowling.domain.score.Pin;

import java.util.Scanner;

/**
 * Created By mand2 on 2020-12-21.
 */
public class InputView {

    private static Scanner SCANNER = new Scanner(System.in);

    public static final String ASK_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    public static final String ASK_PITCH_OF_INDEX = "%d프레임 투구 : ";

    public static Name askPlayerName() {
        System.out.print(ASK_PLAYER_NAME);
        return Name.from(SCANNER.nextLine());
    }

    public static Pin askPitch(Bowling bowling) {
        System.out.print(String.format(ASK_PITCH_OF_INDEX, bowling.currentIndex()));
        return Pin.from(SCANNER.nextInt());
    }

}
