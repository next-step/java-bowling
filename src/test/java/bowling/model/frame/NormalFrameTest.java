package bowling.model.frame;

import bowling.model.DownPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static bowling.model.DownPin.MAX;
import static bowling.model.DownPin.MIN;
import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    @DisplayName("첫번째 볼의 결과가 히트 또는 거터일 경우 현 프레임을 반환한다")
    @ParameterizedTest
    @ValueSource(ints = {MIN, MIN + 1})
    void bowl_hit_thanCurrentFrame(int downPins) {
        // given
        DownPin first = DownPin.valueOf(downPins);

        // when
        Frame frame = NormalFrame.ofFirst();
        Frame resultFrame = frame.bowl(first);

        // then
        assertThat(resultFrame).isEqualTo(frame);
    }

    @DisplayName("스트라이크 일 시 다음 프레임으로 이동한다")
    @Test
    void bowl_strikeFirstPinsTen_thenHasNextFrame() {
        // given
        DownPin first = DownPin.valueOf(MAX);

        // when
        Frame frame = NormalFrame.ofFirst();
        Frame resultFrame = frame.bowl(first);

        // then
        assertThat(resultFrame).isNotEqualTo(frame);
    }

    @DisplayName("스페어일 경우 다음 프레임으로 이동한다")
    @Test
    void bowl_pinsOneAndNine_thenHasNextFrame() {
        // given
        DownPin first = DownPin.valueOf(MIN + 1);
        DownPin second = DownPin.valueOf(MAX - 1);

        // when
        Frame frame = NormalFrame.ofFirst();
        Frame resultFrame = frame.bowl(first).bowl(second);

        // then
        assertThat(resultFrame).isNotEqualTo(frame);
    }
}