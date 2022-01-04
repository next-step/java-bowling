package bowling.domain.frame;

import bowling.domain.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LastFrameTest {

    @Test
    void 프레임_생성() {
        // given
        Frame first = LastFrame.first();

        // when, then
        assertThat(first.getIndex()).isEqualTo(FrameIndex.MAX_INDEX);
        assertThat(first.isEnd()).isFalse();
        assertThat(first.symbol()).isBlank();
    }

    @DisplayName("두 번 투구 후 MISS인데 투구하면 안된다.")
    @Test
    void 예외_미스_상태에서_투구() {
        // given
        Frame first = LastFrame.first();
        Frame miss = first.bowl(Pins.create(3)).bowl(Pins.create(2));

        // when
        assertThatThrownBy(() -> miss.bowl(Pins.create(1)))
                // then
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("프레임이 끝난 상태는 투구할 수 없습니다.");
    }

    @Test
    void 스페어이면_한_번_더_투구() {
        // given
        Frame first = LastFrame.first();
        Frame spare = first.bowl(Pins.create(3)).bowl(Pins.create(7));

        // when
        Frame bonusBowl = spare.bowl(Pins.create(4));

        assertThat(bonusBowl.getIndex()).isEqualTo(FrameIndex.MAX_INDEX);
        assertThat(bonusBowl.isEnd()).isTrue();
        assertThat(bonusBowl.symbol()).isEqualTo("3|7|4");
    }

    @Test
    void 스트라이크_한_번_더_투구() {
        // given
        Frame first = LastFrame.first();

        // when
        Frame strike = first.bowl(Pins.create(10)).bowl(Pins.create(10)).bowl(Pins.create(3));

        // then
        assertThat(strike.getIndex()).isEqualTo(FrameIndex.MAX_INDEX);
        assertThat(strike.isEnd()).isTrue();
        assertThat(strike.symbol()).isEqualTo("X|X|3");
    }
}