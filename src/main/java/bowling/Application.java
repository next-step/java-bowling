package bowling;


import bowling.application.Response;
import bowling.domain.Bowling;
import bowling.domain.player.Player;
import bowling.ui.BowlingController;
import bowling.view.ResultView;

public class Application {

    public static void main(String[] args) {

        BowlingController bowlingController = new BowlingController();
        Player player = new Player("KSJ");
        Bowling bowling = new Bowling(player);

        for (int i = 0; i < 6; i++) {
            Response response = bowlingController.bowl(bowling, i);
            ResultView.print(response);
        }
        Response response = bowlingController.bowl(bowling, 10);
        ResultView.print(response);

        Response response1 = bowlingController.bowl(bowling, 10);
        ResultView.print(response1);

        Response response2 = bowlingController.bowl(bowling, 10);
        ResultView.print(response2);

        Response response3 = bowlingController.bowl(bowling, 10);
        ResultView.print(response3);

        Response response4 = bowlingController.bowl(bowling, 10);
        ResultView.print(response4);

        Response response5 = bowlingController.bowl(bowling, 10);
        ResultView.print(response5);

        Response response6 = bowlingController.bowl(bowling, 10);
        ResultView.print(response6);

        Response response7 = bowlingController.bowl(bowling, 10);
        ResultView.print(response7);

        Response response8 = bowlingController.bowl(bowling, 10);
        ResultView.print(response8);
    }
}
