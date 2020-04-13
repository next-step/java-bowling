package bowling.application;

import bowling.domain.state.Pin;
import bowling.view.ResultView;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GamesTest {

    @Test
    void name() {

        List<Request> requests = Arrays.asList(new Request("KSC"), new Request("SSJ"));

        List<Game> games = new ArrayList<>();

        for (Request request : requests) {
            Game game = new Game(request);
            games.add(game);
        }

        Games games1 = new Games(games);

        for (Response respons : games1.getResponses()) {
            ResultView.view(respons);
        }

        games1.bowl(new Pin(10));
        for (Response respons : games1.getResponses()) {
            ResultView.view(respons);
        }

        games1.bowl(new Pin(5));
        for (Response respons : games1.getResponses()) {
            ResultView.view(respons);
        }

        games1.bowl(new Pin(2));
        for (Response respons : games1.getResponses()) {
            ResultView.view(respons);
        }

        games1.bowl(new Pin(3));
        for (Response respons : games1.getResponses()) {
            ResultView.view(respons);
        }
    }

}
