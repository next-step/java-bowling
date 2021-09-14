package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FrameStateRendererTest {

    @ParameterizedTest(name = "첫번째 투구 후 상태 렌더링 테스트")
    @CsvSource({"SIX, 6", "ZERO, -"})
    public void proceedingFrameRenderingTest(PinCount firstFallenPinCount, String expected) {
        Renderer renderer = FrameStateRenderer.of(firstFallenPinCount);
        assertThat(renderer.render()).contains(expected);
    }

    @DisplayName("스트라이크 렌더링 테스트")
    @Test
    public void strikeRenderingTest() {
        Renderer renderer = FrameStateRenderer.of(PinCount.TEN);
        assertThat(renderer.render()).contains("X");
    }

    @DisplayName("스페어 렌더링 테스트")
    @Test
    public void spareRenderingTest() {
        Renderer renderer = FrameStateRenderer.of(PinCount.EIGHT, PinCount.TWO);
        assertThat(renderer.render()).contains("8|/");
    }

    @ParameterizedTest(name = "미스 렌더링 테스트")
    @CsvSource({"EIGHT, ONE, 8|1", "SEVEN, ZERO, 7|-"})
    public void missRenderingTest(PinCount firstFallenPinCount, PinCount secondFallenPinCount, String expected) {
        Renderer renderer = FrameStateRenderer.of(firstFallenPinCount, secondFallenPinCount);
        assertThat(renderer.render()).contains(expected);
    }

}