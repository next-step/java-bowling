package bowling.frame;

import bowling.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LastFrameTest {

    @Test
    void 미스이면_경기_종료() {
        Frame frame = LastFrame.first();
        frame.bowl(new Pins(1)).bowl(new Pins(3));

        assertThat(frame.isEnd()).isTrue();
        assertThat(frame.symbol()).isEqualTo("1|3");
    }

    @Test
    void 스페어_이면_1번_더_기회() {
        Frame frame = LastFrame.first();
        frame.bowl(new Pins(8)).bowl(new Pins(2)).bowl(new Pins(3));

        assertThat(frame.isEnd()).isTrue();
        assertThat(frame.symbol()).isEqualTo("8|/|3");
    }

    @Test
    void 스트라이크_이면_1번_더_기회() {
        Frame frame = LastFrame.first();
        frame.bowl(new Pins(10)).bowl(new Pins(2)).bowl(new Pins(3));

        assertThat(frame.isEnd()).isTrue();
        assertThat(frame.symbol()).isEqualTo("X|2|3");
    }

    @Test
    void 스트라이크_스페어() {
        Frame frame = LastFrame.first();
        frame.bowl(new Pins(10)).bowl(new Pins(1)).bowl(new Pins(9));

        assertThat(frame.isEnd()).isTrue();
        assertThat(frame.symbol()).isEqualTo("X|1|/");
    }

    @Test
    void 예외_스페어로_세번_투구_후_더_굴릴_수_없다() {
        Frame frame = LastFrame.first().bowl(new Pins(8)).bowl(new Pins(2)).bowl(new Pins(3));

        assertThatThrownBy(() -> frame.bowl(new Pins(1)))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 끝났습니다.");
    }

    @DisplayName("화면에서 사용할 코드")
    @Test
    void 예외_기회가_있을_때_점수_계산() {
        Frame frame = LastFrame.first();
        frame.bowl(new Pins(10)).bowl(new Pins(10));

        assertThat(frame.calculateScore()).isEqualTo(-1);
    }
}