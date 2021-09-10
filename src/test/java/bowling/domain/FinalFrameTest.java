package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {

    @DisplayName("bowl test")
    @Test
    void bowl() {
        Frame frame = new FinalFrame();

        frame.bowl(Pins.of(4));
        assertThat(frame.getFirstPin()).isEqualTo(Pins.of(4));
    }

    @DisplayName("안쳤을 때는 False")
    @Test
    void isFirstFitchNotFinish() {
        Frame frame = new FinalFrame();
        assertThat(frame.isFinish()).isFalse();
    }

    @DisplayName("스페어나 스트라이크 아니고, 두번 다 쳤을 때는 정상 종료  테스트")
    @Test
    void isNormalFinish() {
        Frame frame = new FinalFrame();

        frame.bowl(Pins.of(4));
        frame.bowl(Pins.of(3));
        assertThat(frame.isFinish()).isTrue();
    }

    @DisplayName("마지막 frame 은 첫번째 구에 스트라이크 쳤을 때 False")
    @Test
    void isStrikeFinish() {
        Frame frame = new FinalFrame();

        frame.bowl(Pins.of(10));
        assertThat(frame.isFinish()).isFalse();
    }

    @DisplayName("마지막 frame 은 두번째 구에 스트라이크 이면 False")
    @Test
    void isSecondStrikeFinish() {
        Frame frame = new FinalFrame();

        frame.bowl(Pins.of(10));
        frame.bowl(Pins.of(10));

        assertThat(frame.isFinish()).isFalse();

    }

    @DisplayName("마지막 frame 은 스페어면 종료 안함")
    @Test
    void isSpareFinish() {
        Frame frame = new FinalFrame();

        frame.bowl(Pins.of(6));
        frame.bowl(Pins.of(4));

        assertThat(frame.isFinish()).isFalse();

    }

    @DisplayName("첫번째구 0 이고 두번째구 10 이면 스페어 이고 종료 안함")
    @Test
    void isSpareFinish2() {
        Frame frame = new FinalFrame();

        frame.bowl(Pins.of(0));
        frame.bowl(Pins.of(10));
        assertThat(frame.isFinish()).isFalse();
    }

    @DisplayName("첫번째구 10 이고 스트라이크, 두번째구 10 이면 스트라이크 이고 종료 안함")
    @Test
    void isStrikeFinish2() {
        Frame frame = new FinalFrame();

        frame.bowl(Pins.of(10));
        frame.bowl(Pins.of(10));
        assertThat(frame.isFinish()).isFalse();

    }

    @DisplayName("첫번째구 10 이고 스트라이크, 두번째구 10 이고 보너스볼 10 이면  종료")
    @Test
    void isBonusBowlFinish() {
        Frame frame = new FinalFrame();

        frame.bowl(Pins.of(10));
        frame.bowl(Pins.of(10));
        frame.bowl(Pins.of(10));
        assertThat(frame.isFinish()).isTrue();
    }

}


