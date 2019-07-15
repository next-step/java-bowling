package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-13 17:41
 */
public class NormalFrameTest {
    private Frame currentFrame;

    @BeforeEach
    void setUp() {
        currentFrame = new NormalFrame();
    }

    @DisplayName("현재 몇번째 Frame인지 확인")
    @Test
    void getIndex() {
        NormalFrame originFrame = new NormalFrame();

        Frame bowl1 = originFrame.bowl(10);
        Frame bowl2 = bowl1.bowl(3);
        Frame bowl3 = bowl2.bowl(2);

        assertAll(
                () -> assertThat(bowl1.nowFrameNumber()).isEqualTo(1),
                () -> assertThat(bowl2.nowFrameNumber()).isEqualTo(2),
                () -> assertThat(bowl3.nowFrameNumber()).isEqualTo(2),
                () -> assertThat(bowl3.bowl(10).nowFrameNumber()).isEqualTo(3)
        );
    }

    @DisplayName("Frame 종료 후 새로운 Frame 생성되는지 확인")
    @Test
    void new_confirm_Frame() {
        NormalFrame originFrame = new NormalFrame();
        originFrame.bowl(10);
        assertThat(originFrame.bowl(10)).isNotSameAs(originFrame);
    }

    @DisplayName("NormalFrame이 종료 되었는지 확인")
    @Test
    void isGameOver() {
        currentFrame = currentFrame.bowl(10);
        currentFrame = currentFrame.bowl(10);
        currentFrame = currentFrame.bowl(10);
        currentFrame = currentFrame.bowl(10);
        currentFrame = currentFrame.bowl(10);
        currentFrame = currentFrame.bowl(10);
        currentFrame = currentFrame.bowl(10);
        currentFrame = currentFrame.bowl(10);
        currentFrame = currentFrame.bowl(10);

        assertThat(currentFrame.isGameOver()).isTrue();
    }

    @DisplayName("스트라이크 상태 메시지 비교")
    @Test
    void convertToStrike() {
        currentFrame = currentFrame.bowl(10);
        assertThat(currentFrame.convertScoreToBowl()).isEqualTo("X");
    }

    @DisplayName("거터 상태 메시지 비교 - 1번 투구")
    @Test
    void convertToGutter_1() {
        currentFrame = currentFrame.bowl(0);
        assertThat(currentFrame.convertScoreToBowl()).isEqualTo("-");
    }

    @DisplayName("거터 상태 메시지 비교 - 2번 투구")
    @Test
    void convertToGutter_2() {
        currentFrame = currentFrame.bowl(0);
        currentFrame = currentFrame.bowl(0);
        assertThat(currentFrame.convertScoreToBowl()).isEqualTo("-|-");
    }

    @DisplayName("미스 상태 메시지 비교")
    @Test
    void convertToMiss() {
        currentFrame = currentFrame.bowl(3);
        currentFrame = currentFrame.bowl(6);
        assertThat(currentFrame.convertScoreToBowl()).isEqualTo("3|6");
    }

    @DisplayName("스페어 상태 메시지 비교")
    @Test
    void convertToSpare() {
        currentFrame = currentFrame.bowl(3);
        currentFrame = currentFrame.bowl(7);
        assertThat(currentFrame.convertScoreToBowl()).isEqualTo("3|/");
    }
}
