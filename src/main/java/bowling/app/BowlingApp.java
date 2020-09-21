package bowling.app;

import bowling.domain.Player;
import bowling.view.InputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingApp {

    public static void main(String[] args) {
        int numberOfPlayer = InputView.getNumberOfPlayer();
        List<Player> players = IntStream.range(0, numberOfPlayer)
                .mapToObj(i -> new Player(InputView.getPlayerName(i + 1)))
                .collect(Collectors.toList());
        BowlingExecutor.execute(players);
    }

}
