package bowling.domain.play;

import bowling.domain.Roll;
import bowling.domain.RollSubject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PlayStateTest {

    private final PlayState state = new PlayState() {
        @Override
        void playFirst(PlayContext context) {}

        @Override
        void playSecond(PlayContext context, int frameNo) {}

        @Override
        void playBonus(PlayContext context) {}
    };

    @ParameterizedTest
    @DisplayName("frameNo 가 10일 경우에만 last 이다.")
    @ValueSource(ints = {0, 1, 2, 9, 10, 11})
    void isLast(int frameNo) {
        assertThat(state.isLast(frameNo))
                .isEqualTo(frameNo == 10);
    }

    @Test
    @DisplayName("strike 인 경우 테스트")
    void strike() {
        RollSubject subject = new RollSubject(() -> Roll.of(10));
        PlayContext context = new PlayContext(subject);
        context.execute();
        context.execute();
        assertAll(
                () -> assertThat(state.isStrike(context))
                        .isTrue(),
                () -> assertThat(state.isSpare(context))
                        .isFalse()
        );
    }

    @Test
    @DisplayName("spare 인 경우 테스트")
    void spare() {
        RollSubject subject = new RollSubject(() -> Roll.of(5));
        PlayContext context = new PlayContext(subject);
        context.execute();
        context.execute();
        assertAll(
                () -> assertThat(state.isStrike(context))
                        .isFalse(),
                () -> assertThat(state.isSpare(context))
                        .isTrue()
        );
    }

    @Test
    @DisplayName("miss 인 경우 테스트")
    void miss() {
        RollSubject subject = new RollSubject(() -> Roll.of(1));
        PlayContext context = new PlayContext(subject);
        context.execute();
        context.execute();
        assertAll(
                () -> assertThat(state.isStrike(context))
                        .isFalse(),
                () -> assertThat(state.isSpare(context))
                        .isFalse()
        );
    }
}
