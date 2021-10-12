package bowling.domain;

import bowling.exception.CannotCreateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {
    Frame normalFrame;

    @Test
    @BeforeEach
    void initFrame() {
        normalFrame = new NormalFrame();
    }

    @Test
    @DisplayName("생성 테스트")
    void createTest() {
        assertThat(normalFrame.status()).isEqualTo(FrameStatus.READY);
    }

    @Test
    @DisplayName("두번째 스코어 합산후 해당 프레임 스코어가 10이 넘어가면 Exception 발생")
    void overScoreThrowExceptionTest() throws CannotCreateException {
        normalFrame.firstShot(new PinScore(8));
        assertThatThrownBy(
                () -> normalFrame.secondShot(new PinScore(3))
        ).isInstanceOf(CannotCreateException.class);
    }

    @Test
    @DisplayName("프레임 상태체크 STRIKE")
    void checkFrameStatus1() throws CannotCreateException {
        normalFrame.firstShot(new PinScore(10));

        assertThat(normalFrame.status()).isEqualTo(FrameStatus.STRIKE);
    }

    @Test
    @DisplayName("프레임 상태체크 SPARE")
    void checkFrameStatus2() throws CannotCreateException {
        normalFrame.firstShot(new PinScore(8));
        normalFrame.secondShot(new PinScore(2));

        assertThat(normalFrame.status()).isEqualTo(FrameStatus.SPARE);
    }

    @Test
    @DisplayName("프레임 상태체크 MISS")
    void checkFrameStatus3() throws CannotCreateException {
        normalFrame.firstShot(new PinScore(9));
        normalFrame.secondShot(new PinScore(0));

        assertThat(normalFrame.status()).isEqualTo(FrameStatus.MISS);
    }

    @Test
    @DisplayName("프레임 상태체크 GUTTER")
    void checkFrameStatus4() throws CannotCreateException {
        normalFrame.firstShot(new PinScore(0));
        normalFrame.secondShot(new PinScore(0));

        assertThat(normalFrame.status()).isEqualTo(FrameStatus.GUTTER);
    }

    @Test
    @DisplayName("스코어 합산")
    void checkPointTest() throws CannotCreateException {
        normalFrame.firstShot(new PinScore(6));
        normalFrame.secondShot(new PinScore(3));

        assertThat(normalFrame.score()).isEqualTo(9);
    }
}