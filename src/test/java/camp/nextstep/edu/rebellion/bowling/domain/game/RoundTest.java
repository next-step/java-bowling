package camp.nextstep.edu.rebellion.bowling.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    @DisplayName("Round 가 잘 생성되는지 확인")
    @Test
    public void roundTest() {
        // given
        Round round = Round.reset();

        // when & then
        assertAll(
                () -> assertThat(round.hasNext()).isTrue(),
                () -> assertThat(round.hasPrev()).isFalse(),
                () -> assertThat(round.meetFirst()).isTrue(),
                () -> assertThat(round.meetFinal()).isFalse()
        );
    }

    @DisplayName("Round 가 잘 증가되는지 확인")
    @Test
    public void nextRoundTest() {
        // given
        Round round = goAndGet(9);

        // then
        assertThat(round.hasNext()).isTrue();
        assertThat(round.hasPrev()).isTrue();

        // and
        round.next();

        // then
        assertThat(round.hasNext()).isFalse();
        assertThat(round.meetFinal()).isTrue();
    }

    @DisplayName("Round 가 잘 감소되는지 확인")
    @Test
    public void prevRoundTest() {
        // given
        Round round = goAndGet(2);
        Round currentOf = Round.currentOf(round);

        // when
        currentOf.prev();

        // then
        assertAll(
                () -> assertThat(currentOf.hasPrev()).isTrue(),
                () -> assertThat(currentOf.meetFirst()).isFalse(),
                () -> assertThat(currentOf.meetFinal()).isFalse()
        );

        // and
        currentOf.prev();

        // then
        assertAll(
                () -> assertThat(currentOf.hasPrev()).isFalse(),
                () -> assertThat(currentOf.meetFirst()).isTrue(),
                () -> assertThat(currentOf.meetFinal()).isFalse(),
                () -> assertThat(currentOf.getCurrent()).isEqualTo(0),
                () -> assertThat(round.getCurrent()).isEqualTo(2)
        );
    }

    @DisplayName("최대 Round 값이 잘 증가 되는지 확인 ")
    @Test
    public void addLastRoundTest() {
        // given
        Round round = goAndGet(10);

        // then
        assertThat(round.hasNext()).isFalse();
        assertThat(round.meetFinal()).isTrue();

        // when
        round.addLastRound();

        // then
        assertThat(round.hasNext()).isTrue();
        assertThat(round.meetFinal()).isFalse();

        // and & then
        assertThatThrownBy(() -> round.addLastRound())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("최대 라운드는 허용 범위를 벗어 났습니다 (최대 11) 12");
    }

    private Round goAndGet(int go) {
        Round round = Round.reset();
        for (int i = 0; i < go; i++) {
            round.next();
        }
        return round;
    }

}
