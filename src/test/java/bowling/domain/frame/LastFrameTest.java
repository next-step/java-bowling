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
        Frame miss = first.bowl(new Pins(3)).bowl(new Pins(2));

        // when
        assertThatThrownBy(() -> miss.bowl(new Pins(1)))
                // then
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("프레임이 끝난 상태는 투구할 수 없습니다.");
    }

    @Test
    void 스페어이면_한_번_더_투구() {
        // given
        Frame first = LastFrame.first();
        Frame spare = first.bowl(new Pins(3)).bowl(new Pins(7));

        // when
        Frame bonusBowl = spare.bowl(new Pins(4));

        assertThat(bonusBowl.getIndex()).isEqualTo(FrameIndex.MAX_INDEX);
        assertThat(bonusBowl.isEnd()).isTrue();
        assertThat(bonusBowl.symbol()).isEqualTo("3|7|4");
    }

    @Test
    void 스트라이크_한_번_더_투구() {
        // given
        Frame first = LastFrame.first();

        // when
        Frame strike = first.bowl(new Pins(10)).bowl(new Pins(10)).bowl(new Pins(3));

        // then
        assertThat(strike.getIndex()).isEqualTo(FrameIndex.MAX_INDEX);
        assertThat(strike.isEnd()).isTrue();
        assertThat(strike.symbol()).isEqualTo("X|X|3");
    }

    @DisplayName("점수 계산이 불가한 상태일 경우 -1 반환")
    @Test
    void 점수_계산_불가() {
        Frame frame = LastFrame.first();
        frame.bowl(new Pins(10));

        assertThat(frame.score()).isEqualTo(Score.UN_SCORE_STATE);
    }

    @Test
    void 점수_계산_올_스트라이크() {
        Frame frame = LastFrame.first();
        frame.bowl(new Pins(10)).bowl(new Pins(10)).bowl(new Pins(10));

        assertThat(frame.score()).isEqualTo(30);
    }

    @Test
    void 점수_계산_스페어() {
        Frame frame = LastFrame.first();
        frame.bowl(new Pins(9)).bowl(new Pins(1)).bowl(new Pins(9));

        assertThat(frame.score()).isEqualTo(19);
    }

    @Test
    void 점수_계산_올_스트라이크_후_스페어() {
        Frame frame = LastFrame.first();
        frame.bowl(new Pins(10)).bowl(new Pins(1)).bowl(new Pins(9));

        assertThat(frame.score()).isEqualTo(20);
    }

    @Test
    void 점수_계산_올_스트라이크_2번() {
        Frame frame = LastFrame.first();
        frame.bowl(new Pins(10)).bowl(new Pins(10)).bowl(new Pins(9));

        assertThat(frame.score()).isEqualTo(29);
    }
}