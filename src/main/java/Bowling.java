import bowling.BowlingGame;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Bowling {

    public static void main(String[] args) {
        new BowlingGame(new InputView(), new ResultView()).run();
    }
}
