package bowling.application;

import bowling.ui.Request;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GamesTest {

    @Test
    @DisplayName("여러명 게임 시 보여줘야할 Response 사이즈 확인")
    void getSizeByMultiRequest() {
        // give
        List<Request> requests = Arrays.asList(new Request("KSC"), new Request("SSJ"));
        List<Game> games = new ArrayList<>();

        for (Request request : requests) {
            Game game = new Game(request);
            games.add(game);
        }
        Games games1 = new Games(games);
        // when
        boolean same = games1.getResponses().size() == requests.size();
        // then
        assertThat(same).isTrue();
    }

}
