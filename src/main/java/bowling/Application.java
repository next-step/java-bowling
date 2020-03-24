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

        Response response1 = bowlingController.bowl(bowling, 2);
        ResultView.print(response1);

        Response response2 = bowlingController.bowl(bowling, 4);
        ResultView.print(response2);

        Response response3 = bowlingController.bowl(bowling, 6);
        ResultView.print(response3);

        Response response4 = bowlingController.bowl(bowling, 3);
        ResultView.print(response4);

        Response response5 = bowlingController.bowl(bowling, 7);
        ResultView.print(response5);

        Response response6 = bowlingController.bowl(bowling, 3);
        ResultView.print(response6);

        Response response7 = bowlingController.bowl(bowling, 7);
        ResultView.print(response7);

        Response response8 = bowlingController.bowl(bowling, 2);
        ResultView.print(response8);

        Response response9 = bowlingController.bowl(bowling, 0);
        ResultView.print(response9);

        Response response10 = bowlingController.bowl(bowling, 2);
        ResultView.print(response10);

        Response response11 = bowlingController.bowl(bowling, 5);
        ResultView.print(response11);

        Response response12 = bowlingController.bowl(bowling, 5);
        ResultView.print(response12);

        Response response13 = bowlingController.bowl(bowling, 0);
        ResultView.print(response13);
    }
}
