package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.domain.pin.Pins;
import bowling.view.Input;
import bowling.view.Output;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int playerCount = Input.readPlayerCount();
        BowlingGames bowlingGames = getBowlingGames(playerCount);

        Output.printBowlingGameResults(bowlingGames);
        while (!bowlingGames.hashNext()){
            bowlingGames.getBowlingGames()
                    .forEach(bowlingGame -> playGame(bowlingGames, bowlingGame));
        }
    }

    private static void playGame(BowlingGames bowlingGames, BowlingGame bowlingGame) {
        if(bowlingGame.hasNext()){
            Pins pins = Input.readHitPin(bowlingGame);
            bowlingGame.pitch(pins);
            Output.printBowlingGameResults(bowlingGames);
        }
    }

    private static BowlingGames getBowlingGames(int count) {
        List<BowlingGame> bowlingGames = new ArrayList<>();
        for(int i=0; i<count; i++){
            BowlingGame bowlingGame = new BowlingGame(Input.readPlayerName());
            bowlingGames.add(bowlingGame);
        }
        return new BowlingGames(bowlingGames);
    }
}
