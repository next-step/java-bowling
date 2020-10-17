package bowling.frame.state;

import bowling.score.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NextTest {

    private Pin firstPins;
    private Pin secondPins;
    private State secondState;

    @Test
    @DisplayName("투구 종료 여부 확인 - 종료")
    void isFinished() {
        firstPins = Pin.bowl("3");
        secondPins = Pin.bowl("1");
        secondState = Next.of(firstPins).bowl(secondPins);
        assertThat(secondState.isFinish()).isTrue();
    }

    @Test
    @DisplayName("두번째 볼링 결과 - 스페어")
    void secondBowlIsSpare() {
        firstPins = Pin.bowl("3");
        secondPins = Pin.bowl("7");
        secondState = Next.of(firstPins).bowl(secondPins);
        assertThat(secondState instanceof Spare).isTrue();
    }

    @Test
    @DisplayName("두번째 볼링 결과 - 미스")
    void secondBowlIsMiss() {
        firstPins = Pin.bowl("5");
        secondPins = Pin.bowl("2");
        secondState = Next.of(firstPins).bowl(secondPins);
        assertThat(secondState instanceof Miss).isTrue();
    }

}
