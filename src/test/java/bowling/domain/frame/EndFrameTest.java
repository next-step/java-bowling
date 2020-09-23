package bowling.domain.frame;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EndFrameTest {

    @Test
    @DisplayName("두번 SPARE 를 기록할 경우 보너스 1번의 투구 기회를 가지는 테스트")
    void spare() {
        // given
        Frame frame = EndFrame.of();

        // when
        frame.bowl(Pin.of(9));
        frame.bowl(Pin.of(1));
        frame.bowl(Pin.of(10));

        // then
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("두번 STRIKE 를 기록할 경우 보너스 1번의 투구 기회를 가지는 테스트")
    void strike() {
        // given
        Frame frame = EndFrame.of();

        // when
        frame.bowl(Pin.of(10));
        frame.bowl(Pin.of(10));
        frame.bowl(Pin.of(10));

        // then
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("MISS 기록할 경우 최대 보너스 투구는 주어지지 않는 테스트")
    void miss() {
        // given
        Frame frame = EndFrame.of();

        // when
        frame.bowl(Pin.of(8));
        frame.bowl(Pin.of(1));

        // then
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("모두 STRIKE 를 기록할 경우 점수 계산")
    void strikeGetScore() {
        // given
        Frames frames = Frames.init();
        Frame frame = EndFrame.of();
        frames.getFrames().add(frame);

        // when
        frame.bowl(Pin.of(10));
        frame.bowl(Pin.of(10));
        frame.bowl(Pin.of(10));

        // then
        assertThat(frame.getScore(frames)).isEqualTo(Score.of(30));
    }

    @Test
    @DisplayName("SPARE 를 기록할 경우 점수 계산")
    void spareGetScore() {
        // given
        Frames frames = Frames.init();
        Frame frame = EndFrame.of();
        frames.getFrames().add(frame);

        // when
        frame.bowl(Pin.of(9));
        frame.bowl(Pin.of(1));
        frame.bowl(Pin.of(10));

        // then
        assertThat(frame.getScore(frames)).isEqualTo(Score.of(20));
    }

    @Test
    @DisplayName("MISS 기록할 경우 점수 계산")
    void missGetScore() {
        // given
        Frames frames = Frames.init();
        Frame frame = EndFrame.of();
        frames.getFrames().add(frame);

        // when
        frame.bowl(Pin.of(6));
        frame.bowl(Pin.of(1));

        // then
        assertThat(frame.getScore(frames)).isEqualTo(Score.of(7));
    }
}
