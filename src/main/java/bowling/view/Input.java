package bowling.view;

import bowling.bowl.BowlingGame;
import bowling.pin.Pins;
import bowling.player.Player;

import java.util.Scanner;

public class Input {
    private static final String PLAYER_NAME_MSG = "플레이어 이름은(3 english letters)?: ";
    private static final String PIN_HIT_CNT_MSG = "%d 프레임 투구 : ";

    private static final Scanner sc = new Scanner(System.in);

    private Input() {
    }

    public static Player readPlayerName() {
        System.out.print(PLAYER_NAME_MSG);
        return new Player(sc.nextLine());
    }

    public static Pins readHitPin(BowlingGame bowlingGame) {
        System.out.printf(PIN_HIT_CNT_MSG, bowlingGame.getFameIndex());
        return new Pins(Integer.parseInt(sc.nextLine()));
    }
}
