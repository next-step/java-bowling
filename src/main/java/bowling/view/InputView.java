package bowling.view;

import bowling.Frames;
import bowling.Player;
import bowling.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class InputView {

    private Scanner scanner = new Scanner(System.in);
    private final int PLUS_SIZE_ONE = 1;

    public Player player() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        return new Player(scanner.nextLine());
    }

    public int playerSize() {
        System.out.print("How many people? ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int score(Frames frames) {
        System.out.printf("%d프레임 투구 :", framesSize(frames));
        return scanner.nextInt();
    }

    private int framesSize(Frames frames) {
        if (frames.isPitch()) {
            return frames.frames().size() + PLUS_SIZE_ONE;
        }
        ;
        return frames.frames().size();
    }

    public Players players() {
        List<Player> players = new ArrayList<>();
        int userSize = playerSize();

        IntStream.range(0, userSize)
                .forEach(i -> players.add(player()));

        return Players.of(players);
    }


    public int score(Player player) {
        System.out.printf("%s, %d프레임 투구 :"
                , player.getName()
                , framesSize(player.frames()));
        return scanner.nextInt();
    }
}
