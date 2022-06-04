package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.pin.Pins;
import bowling.domain.player.Player;

import java.util.Scanner;

public class Input {
    private static final String PLAYER_NAME_MSG = "플레이어 이름은(3 english letters)?: ";
    private static final String PIN_HIT_CNT_MSG = "%s's turn : ";
    private static final String PLAYER_COUNT_MSG = "How many people?";

    private static final Scanner sc = new Scanner(System.in);

    private Input() {
    }

    public static Player readPlayerName() {
        System.out.print(PLAYER_NAME_MSG);
        return new Player(sc.nextLine());
    }

    public static Pins readHitPin(BowlingGame bowlingGame) {
        System.out.printf(PIN_HIT_CNT_MSG, bowlingGame.getPlayer().getName());
        return new Pins(Integer.parseInt(sc.nextLine()));
    }

    public static int readPlayerCount(){
        System.out.println(PLAYER_COUNT_MSG);
        return Integer.parseInt(sc.nextLine());
    }
}
