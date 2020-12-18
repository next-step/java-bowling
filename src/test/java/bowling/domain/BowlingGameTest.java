package bowling.domain;

import bowling.NotPlayableException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class BowlingGameTest {
    @Test
    void create() {
        // given
        final User user = User.of("HSM");

        // when
        final BowlingGame bowlingGame = BowlingGame.of(user);

        // then
        assertThat(bowlingGame).isNotNull();
    }

    @DisplayName("게임이 종료되지 않은 경우 false 반환")
    @Test
    void should_return_false_when_game_is_not_finished() {
        // given
        final User user = User.of("HSM");
        final BowlingGame bowlingGame = BowlingGame.of(user);

        // when
        final boolean gameFinished = bowlingGame.isFinished();

        // then
        assertThat(gameFinished).isFalse();
    }

    @DisplayName("프레임 번호를 반환")
    @Test
    void should_return_now_round() {
        // given
        final User user = User.of("HSM");
        final BowlingGame bowlingGame = BowlingGame.of(user);

        // when
        final int nowFrameNumber = bowlingGame.nowFrameNo();

        // then
        assertThat(nowFrameNumber).isOne();
    }

    @DisplayName("쓰러뜨린 핀의 개수를 해당 Frame에 반영")
    @Test
    void pitch() {
        // given
        final User user = User.of("HSM");
        final BowlingGame bowlingGame = BowlingGame.of(user);

        // when
        bowlingGame.pitch(10);
    }

    @DisplayName("경기가 끝난 이후 공을 던진 경우 예외 반환")
    @Test
    void pitch_after_game_end() {
        // given
        final BowlingGame bowlingGame = BowlingGameMockFactory.create("HSM");
        for (int i = 0; i < 12; i++) {
            bowlingGame.pitch(10);
        }

        // when
        final Throwable thrown = catchThrowable(() -> bowlingGame.pitch(10));

        // then
        AssertionsForClassTypes.assertThat(thrown).isInstanceOf(NotPlayableException.class);
    }
}
