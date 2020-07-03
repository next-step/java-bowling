package bowling.step4.view;

import bowling.step4.domain.process.BowlingGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputName(){
        System.out.println("플레이어의 이름은(3 english letters)?:");
        return SCANNER.next();
    }

    public static int inputPitch(BowlingGame bowlingGame){
        System.out.println(String.format("%s의 %d프레임 투구", bowlingGame.getPlayerName(), bowlingGame.getFrameNo()));
        return SCANNER.nextInt();
    }

    public static List<String> inputPlayers() {
        System.out.println("How many people? ");
        int playersCount = SCANNER.nextInt();
        List<String> players = new ArrayList<>();
        for (int i = 0; i < playersCount; i++){
            players.add(intputNames(i));
        }
        return players;
    }

    private static String intputNames(int order) {
        System.out.println(String.format("플레이어 %d의 이름은?(3 english letters): ", order+1));
        return SCANNER.next();
    }
}
