package camp.nextstep.edu.rebellion.bowling;

import camp.nextstep.edu.rebellion.bowling.domain.game.BowlingGames;
import camp.nextstep.edu.rebellion.bowling.domain.player.Players;

import java.util.List;

import static camp.nextstep.edu.rebellion.bowling.view.InputView.getNumberOfPlayers;
import static camp.nextstep.edu.rebellion.bowling.view.InputView.getPlayers;
import static camp.nextstep.edu.rebellion.bowling.view.InputView.getHitScore;
import static camp.nextstep.edu.rebellion.bowling.view.ResultView.print;

public class BowlingGameRunner {
    public static void main(String[] args) {
        int numberOfPlayers = getNumberOfPlayers();
        List<String> participants = getPlayers(numberOfPlayers);

        Players players = Players.join(participants);
        BowlingGames bowlingGames = BowlingGames.start(players);

        while (bowlingGames.hasNext()) {
            print(bowlingGames.getScoreBoard());
            bowlingGames.record(getHitScore(bowlingGames.currentPlayerName()));
        }

        print(bowlingGames.getScoreBoard());
    }
}
