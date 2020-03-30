package bowling;


import bowling.application.BowlingService;
import bowling.ui.BowlingController;
import bowling.view.ResultView;

public class Application {

    public static void main(String[] args) {

        BowlingController bowlingController = new BowlingController(new BowlingService());


        ResultView.view(bowlingController.bowl(1));
        ResultView.view(bowlingController.bowl(2));

        ResultView.view(bowlingController.bowl(3));
        ResultView.view(bowlingController.bowl(4));

        ResultView.view(bowlingController.bowl(6));
        ResultView.view(bowlingController.bowl(4));

        ResultView.view(bowlingController.bowl(10));

        ResultView.view(bowlingController.bowl(10));

        ResultView.view(bowlingController.bowl(10));

        ResultView.view(bowlingController.bowl(10));

        ResultView.view(bowlingController.bowl(10));

        ResultView.view(bowlingController.bowl(3));
        ResultView.view(bowlingController.bowl(2));

        // 10
        ResultView.view(bowlingController.bowl(10));

        ResultView.view(bowlingController.bowl(10));
//
        ResultView.view(bowlingController.bowl(2));

    }
}
