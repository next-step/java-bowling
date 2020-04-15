package bowling;


import bowling.application.Games;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Application {

    public static void main(String[] args) {
        int count = InputView.inputPeopleCount();
        Games games = new Games(count);
        ResultView.view(games.getResponses());

        while (!games.isEnd()) {
            games.bowl();
            ResultView.view(games.getResponses());
        }
    }
}
