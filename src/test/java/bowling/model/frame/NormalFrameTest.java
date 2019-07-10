package bowling.model.frame;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.Pins.MAX;
import static bowling.model.Pins.MIN;
import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    @DisplayName("첫번째 볼의 결과가 힛일 경우 현 프레임을 반환한다")
    @Test
    void bowl_hit_pinsZero() {
        // given
        Pins first = Pins.valueOf(MIN);

        // when
        Frame frame = NormalFrame.ofFirst();
        Frame resultFrame = frame.bowl(first);

        // then
        assertThat(resultFrame).isEqualTo(frame);
    }

    @DisplayName("첫번째 볼이 스트라이크 일 시 다음 프레임으로 이동한다")
    @Test
    void bowl_strikeFirstPinsTen_thenHasNextFrame() {
        // given
        Pins first = Pins.valueOf(MAX);

        // when
        NormalFrame frame = NormalFrame.ofFirst();
        Frame resultFrame = frame.bowl(first);

        // then
        assertThat(resultFrame).isNotEqualTo(frame);
    }

    @DisplayName("스페어일 경우 다음 프레임으로 이동한다")
    @Test
    void bowl_pinsOneAndNine_thenHasNextFrame() {
        // given
        Pins first = Pins.valueOf(1);
        Pins second = Pins.valueOf(9);

        // when
        NormalFrame frame = NormalFrame.ofFirst();
        Frame resultFrame = frame.bowl(first).bowl(second);

        // then
        assertThat(resultFrame).isNotEqualTo(frame);
    }
}