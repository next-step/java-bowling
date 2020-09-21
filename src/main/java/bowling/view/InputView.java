package bowling.view;

import bowling.domain.Player;
import bowling.domain.Players;

import java.util.Scanner;

public class InputView {

    private Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public Players inputPlayers() {
        int participatePlayer = inputHowManyPlayers();
        Players players = new Players(participatePlayer);
        for(int i = 0; i < participatePlayer; i++) {
            players.addPlayers(new Player(inputPlayerName()));
        }
        return players;
    }
    public int inputHowManyPlayers() {
        System.out.print("How many people? : ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String inputPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)? : ");
        return scanner.nextLine();
    }

    public String inputFrameShoot(String playerName) {
        System.out.printf("%s's turn : ", playerName);
        return scanner.nextLine();
    }
}
