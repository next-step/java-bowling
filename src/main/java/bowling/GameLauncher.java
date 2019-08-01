package bowling;

import bowling.view.InputView;
import bowling.view.OutputView;

public class GameLauncher {

  public static void main(String[] args) {
    GameLauncher.start();
  }

  private static void start() {
    Players players = playersInit();

    BowlingGames bowlingGames = new BowlingGames(players.names());
    OutputView.initialBoardPrint(bowlingGames.result());

    while (!bowlingGames.isAllGamesEnd()) {
      players.names().stream()
          .forEach(name -> onePlayerBowl(bowlingGames, name));
    }
  }

  private static Players playersInit() {
    int peopleCount = InputView.askHowManyPeople();
    Players players = new Players();
    for (int i = 0; i < peopleCount; i++) {
      players.addPlayer(InputView.askPlayerName());
    }
    return players;
  }

  private static void onePlayerBowl(BowlingGames bowlingGames, String name) {
    BowlingGame myGame = bowlingGames.findBowlingGameByName(name);
    int currentFrameNo = myGame.currentFrameNo();
    while (!myGame.isOneFrameEnd(currentFrameNo)) {
      myGame.bowl(InputView.askFallDownCount(name));
      OutputView.initialBoardPrint(bowlingGames.result());
    }
  }
}
