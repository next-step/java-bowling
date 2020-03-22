package bowling;


import bowling.application.Response;
import bowling.domain.Bowling;
import bowling.ui.BowlingController;
import bowling.view.ResultView;

public class Application {

    public static void main(String[] args) {

        BowlingController bowlingController = new BowlingController();
        Bowling bowling = new Bowling();

        for (int i = 10; i > 0; i--) {
            Response response = bowlingController.bowl(bowling, i);
            ResultView.print(response);
        }
    }
}
