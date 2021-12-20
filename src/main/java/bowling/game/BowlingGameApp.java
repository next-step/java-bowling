package bowling.game;
import bowling.model.gameresult.GameResults;
import bowling.view.InputView;
import bowling.view.NameView;
import bowling.view.ResultView;
import bowling.model.Name;

public class BowlingGameApp {
    public static void main(String[] args) {
        try {
            Name name = NameView.getPlayerName();
            GameResults gameResults = new GameResults();
            BowlingGame game = new BowlingGame(name);

            ResultView.printResults(name,gameResults);
            while(game.canPlay()){
                gameResults.set(game.play());
                ResultView.printResults(name,gameResults);
            }
            if(game.bonusGame()) {
                gameResults.set(game.play());
                ResultView.printResults(name,gameResults);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + " 게임을 종료하겠습니다.");
        }
    }
}
