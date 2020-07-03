package bowling.game.domain;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.pin.domain.Pin;
import bowling.user.domain.User;
import java.util.Arrays;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BowlingGameTest {

    @Test
    void of() {
    }

    @Test
    @DisplayName("차례대로 공을 투구 할 수 있다. - Cover 의 경우 계속 차례가 유지된다.")
    void rollCover() {
        BowlingGame game = BowlingGame.of(Arrays.asList("LJS", "JHJ"));
        game.roll(Pin.of(3));
        assertThat(game.getCurrentUser()).isEqualTo(User.of("LJS"));
    }


    @Test
    @DisplayName("차례대로 공을 투구 할 수 있다. - SPARE 의 경우 다음 사람 차례가 된다.")
    void rollSpare() {
        BowlingGame game = BowlingGame.of(Arrays.asList("LJS", "JHJ"));
        game.roll(Pin.of(3));
        game.roll(Pin.of(7));
        assertThat(game.getCurrentUser()).isEqualTo(User.of("JHJ"));
    }

    @Test
    @DisplayName("차례대로 공을 투구 할 수 있다. - STRIKE 의 경우 다음 사람 차례가 된다.")
    void rollStrike() {
        BowlingGame game = BowlingGame.of(Arrays.asList("LJS", "JHJ"));
        game.roll(Pin.of(10));
        assertThat(game.getCurrentUser()).isEqualTo(User.of("JHJ"));
    }

    @Test
    @DisplayName("현재 순서의 보드를 리턴한다. 동일한 순서일 경우 리스트 순서가 빠른 보드를 리턴")
    void getCurrent() {
        BowlingGame game = BowlingGame.of(Arrays.asList("LJS", "JHJ"));
        assertThat(game.getCurrentUser()).isEqualTo(User.of("LJS"));
    }

    @Test
    @DisplayName("모든 투구가 끝나야 경기가 종료 된다. (2인이 모두 스트라이크를 칠 경우 24번의 기회가 주어진다.)")
    void isGameOver() {
        BowlingGame game = BowlingGame.of(Arrays.asList("LJS", "JHJ"));
        IntStream.rangeClosed(1, 24).forEach(i -> game.roll(Pin.of(10)));
        assertThat(game.isGameOver()).isTrue();
    }
}