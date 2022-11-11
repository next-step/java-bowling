package bowling.controller;

import bowling.domain.Player;
import bowling.domain.PlayerName;
import bowling.domain.Players;
import bowling.domain.scorestrategy.RandomScoreStrategy;
import bowling.dto.BowlingRecord;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BowlingGameController {

    public static void main(String[] args) {
        int numberOfPeople = InputView.askHowManyPeople();

        List<Player> waitingPlayers = new ArrayList<>();
        for (int i = 1; i <= numberOfPeople; i++) {
            PlayerName playerName = new PlayerName(InputView.askPlayerName(i));
            waitingPlayers.add(new Player(playerName));
        }

        Players players = new Players(waitingPlayers);

        OutputView.printStart(BowlingRecord.ofList(players));

        while (players.isGaming()) {
            Player nowPlayer = players.play(new RandomScoreStrategy());
            OutputView.printBowlNow(nowPlayer.getPlayerName(), nowPlayer.getNow());
            OutputView.print(BowlingRecord.ofList(players));
        }
    }

}
