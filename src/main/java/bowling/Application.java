package bowling;


import bowling.application.Games;
import bowling.ui.Request;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        int count = InputView.inputPeopleCount();

        Games games = new Games(createRequests(count));
        ResultView.view(games.getResponses());

        while (!games.isEnd()) {
            games.bowl();
            ResultView.view(games.getResponses());
        }
    }

    private static List<Request> createRequests(int count) {
        List<Request> requests = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            requests.add(new Request(InputView.inputName(i + 1)));
        }
        return requests;
    }
}
