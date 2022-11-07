package bowling.controller;

import bowling.domain.Player;
import bowling.domain.PlayerName;
import bowling.domain.scorestrategy.RandomScoreStrategy;
import bowling.dto.BowlingRecord;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGameController {

    public static void main(String[] args) {

        List<Player> players = new ArrayList<>();

        // 참가자 수 묻기
        int numberOfPeople = InputView.askHowManyPeople();
        // 참가자 이름 얻기
        for (int i = 1; i <= numberOfPeople; i++) {
            PlayerName playerName = new PlayerName(InputView.askPlayerName(i));
            players.add(new Player(playerName));
        }
        // 최초 출력
        OutputView.printStart(makeRecord(players));
        // 게임 진행
        while (!isFinished(players)) {
            playAndPrint(players);
        }
    }

    private static List<BowlingRecord> makeRecord(List<Player> players){
        return players.stream().map(BowlingRecord::of)
                .collect(Collectors.toUnmodifiableList());
    }
    
    private static boolean isFinished(List<Player> players){
        return players.stream().allMatch(Player::isFinished);
    }
    
    private static void playAndPrint(List<Player> players){

        if(players.stream().allMatch(Player::endOfTurn)){
            players.forEach(Player::next);
        }

        players.stream().filter(Player::turnInProgress)
                .forEach(player -> {
                    player.play(new RandomScoreStrategy());
                    OutputView.printBowlNow(player.getPlayerName(), player.getNow());
                    OutputView.print(makeRecord(players));
                });
    }

}
