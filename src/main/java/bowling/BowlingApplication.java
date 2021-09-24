package bowling;

import bowling.domain.frame.Pitch;
import bowling.domain.player.Name;
import bowling.domain.player.Player;
import bowling.domain.player.Players;
import bowling.exception.NameException;
import bowling.exception.PitchException;
import bowling.view.InputView;

import java.util.ArrayList;
import java.util.List;

import static bowling.view.InputView.getPlayerCount;
import static bowling.view.InputView.playerName;
import static bowling.view.ResultView.*;

public class BowlingApplication {
    public static void main(String[] args) {
        Players players = players();
        printPlayers(players);

        while (!players.gameOver()) {
            players.getPlayers()
                    .forEach(player -> bowl(players, player));
        }
        printfinalScore(players);
    }

    private static void bowl(Players players, Player player) {
        if (player.gameOver()) {
            return;
        }
        Pitch first = pitch(player.name());
        if (player.isLastFrame()) {
            player.bowl(first);
            printResult(players);
            return;
        }
        player.bowl(first);
        printResult(players);
        while (!player.turnOver()) {
            Pitch second = pitch(player.name());
            player.bowl(second);
            printResult(players);
        }
    }

    private static Players players() {
        List<Player> players = new ArrayList<>();
        int count = playerCount();
        for (int i = 0; i < count; i++) {
            Name name = name(i + 1);
            players.add(new Player(name));
        }
        return new Players(players);
    }

    private static int playerCount() {
        try {
            return Integer.parseInt(getPlayerCount());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해 주세요.");
            return playerCount();
        }
    }

    private static Pitch pitch(String name) {
        try {
            String pitch = InputView.pitch(name);
            return Pitch.from(Integer.parseInt(pitch));
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요");
            return pitch(name);
        } catch (PitchException e) {
            System.out.println("0 ~ 10 사이 숫자를 입력해 주세요.  ");
            return pitch(name);
        }
    }

    private static Name name(int playerCount) {
        String name = playerName(playerCount);
        try {
            return new Name(name);
        } catch (NameException e) {
            System.out.printf("이름(%s)을 잘못 입력했습니다. \n", name);
            return name(playerCount);
        }
    }

}
