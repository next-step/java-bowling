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
            BowlingGame game = new BowlingGame(name);
            game.play();
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + " 게임을 종료하겠습니다.");
        }
    }
}
