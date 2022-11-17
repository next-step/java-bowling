package bowling.domain.pin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.domain.exception.OutOfBoundFallenPinsBucket;
import org.junit.jupiter.api.Test;

class FallenPinsBucketTest {

    @Test
    void 일반_프레임용_핀_버켓_만들기() {
        assertThat(FallenPinsBucket.ofNormalSize().getSize()).isEqualTo(FallenPinsBucket.NORMAL_FRAME_SIZE);
    }

    @Test
    void 마지막_프레임용_핀_버켓_만들기() {
        assertThat(FallenPinsBucket.ofFinalSize().getSize()).isEqualTo(FallenPinsBucket.FINAL_FRAME_SIZE);
    }

    @Test
    void 핀_업데이트_후_가져오기() {
        FallenPinsBucket fallenPinsBucket = FallenPinsBucket.ofNormalSize();
        fallenPinsBucket.saveFallenPins(FallenPins.of(3), 0);

        assertThat(fallenPinsBucket.getFallenPins(0).getCountOfPin()).isEqualTo(3);
    }

    @Test
    void 버켓_사이즈를_넘어가는_인덱스로_핀_업데이트_시도_시_예외_발생() {
        FallenPinsBucket fallenPinsBucket = FallenPinsBucket.ofNormalSize();

        assertThatExceptionOfType(OutOfBoundFallenPinsBucket.class)
                .isThrownBy(() -> fallenPinsBucket.saveFallenPins(FallenPins.of(3), 2));
    }

    @Test
    void 버켓_사이즈를_넘어가는_인덱스로_핀_조회_시도_시_예외_발생() {
        FallenPinsBucket fallenPinsBucket = FallenPinsBucket.ofNormalSize();

        assertThatExceptionOfType(OutOfBoundFallenPinsBucket.class)
                .isThrownBy(() -> fallenPinsBucket.getFallenPins(2));
    }

}
