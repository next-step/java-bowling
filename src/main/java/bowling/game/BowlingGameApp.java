package bowling.game;
import bowling.model.gameresult.GameResults;
import bowling.view.InputView;
import bowling.view.ResultView;
import bowling.model.Name;

public class BowlingGameApp {
    public static void main(String[] args) {
        Name name = new Name(InputView.getStringValue("플레이어 이름은 (3 english letters)? : "));
        GameResults gameResults = new GameResults();
        ResultView.printResults(name,gameResults);
        BowlingGame game = new BowlingGame(name);
        while(game.canPlay()){
            gameResults.set(game.play());
            ResultView.printResults(name,gameResults);
        }
        if(game.bonusGame()) {
            gameResults.set(game.play());
            ResultView.printResults(name,gameResults);
        }
    }
}
