package bowling;

public class BowlingApplication {

    public static void main(String[] args) {
        new BowlingController(new InputView(), new ResultView()).run();
    }
}
