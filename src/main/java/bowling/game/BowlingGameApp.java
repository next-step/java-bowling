package bowling.game;
import bowling.view.NameView;
import bowling.model.Name;
import bowling.view.ResultView;

public class BowlingGameApp {
    public static void main(String[] args) {
        try {
            Name name = NameView.getPlayerName();
            BowlingGame game = new BowlingGame(name);
            game.play();
        } catch (Exception ex) {
            ResultView.printExitGameMessage(ex.getMessage());
        }
    }
}
