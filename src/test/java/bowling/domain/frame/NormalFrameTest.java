package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NormalFrameTest {

    @DisplayName("NormalFrame 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        Frame normalFrame = NormalFrame.from(1);

        // then
        assertAll(
                () -> assertThat(normalFrame).isNotNull(),
                () -> assertThat(normalFrame).isInstanceOf(NormalFrame.class)
        );
    }

    @DisplayName("NormalFrame 인스턴스가 bowl 실행(제자리) 테스트")
    @Test
    void 투구_bowl_제자리() {
        // given
        Pins pins = Pins.valueOf(9);

        // when
        Frame normalFrame = NormalFrame.from(1);
        Frame returnedFrame = normalFrame.bowl(pins);
        // then
        assertAll(
                () -> assertThat(normalFrame.isFinish()).isFalse(),
                () -> assertThat(returnedFrame).isSameAs(normalFrame)
        );
    }

    @DisplayName("NormalFrame 인스턴스가 bowl 실행(다음으로 이동) 테스트")
    @Test
    void 투구_bowl_다음으로_이동() {
        // given
        Pins pins = Pins.full();

        // when
        Frame normalFrame = NormalFrame.from(1);
        Frame nextFrame = normalFrame.bowl(pins);

        // then
        assertAll(
                () -> assertThat(normalFrame.isFinish()).isTrue(),
                () -> assertThat(nextFrame).isNotSameAs(normalFrame)
        );
    }

    @DisplayName("NormalFrame 인스턴스가 알맞은 Score를 반환하는지 테스트")
    @Test
    void 반환_score() {
        // given
        Pins pins = Pins.full();

        // when
        Frame normalFrame = NormalFrame.from(1);
        normalFrame.bowl(pins);

        // then
        assertAll(
                () -> assertThat(normalFrame.score()).isNotNull(),
                () -> assertThat(normalFrame.score()).isInstanceOf(Score.class),
                () -> assertThat(normalFrame.score().score()).isEqualTo(10)
        );
    }

    @DisplayName("NormalFrame 인스턴스가 sequence 를 반환하는지 테스트")
    @Test
    void 반환_sequence() {
        // given
        Pins pins = Pins.full();

        // when
        Frame normalFrame = NormalFrame.from(1);
        Frame nextFrame = normalFrame.bowl(pins);

        // then
        assertAll(
                () -> assertThat(normalFrame.sequence()).isEqualTo(1),
                () -> assertThat(nextFrame.sequence()).isEqualTo(2)
        );

    }
}