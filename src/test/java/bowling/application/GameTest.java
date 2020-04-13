package bowling.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    @Test
    @DisplayName("동일한 이름 요청시 같은 Game 객체 반환")
    void equalsToGameObject() {
        // give
        Request request = new Request("KSJ");
        Game game = new Game(request);
        Game game1 = new Game(request);
        // when
        boolean same = game.equals(game1);
        // then
        assertThat(same).isTrue();
    }

    @Test
    @DisplayName("Game 객체에서 Response 객체 값 확인 - Game 의 객체가 Response 객체를 가지고 있다")
    void checkFrameNumberByGameHasResponse() {
        // give
        Request request = new Request("KSJ");
        Game game = new Game(request);
        int actual = game.getFrameNumber();
        int expected = 1;
        // when
        boolean same = actual == expected;
        // then
        assertThat(same).isTrue();
    }
}
