package bowling.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.frame.ConstShootScore.FIVE_SCORE;
import static bowling.frame.ConstShootScore.STRIKE;
import static org.assertj.core.api.Assertions.*;

class NormalFrameTest {

    @Test
    @DisplayName("처음 frame 이 생성되면 종료되지 않은 상태여야 합니다")
    void create() {
        assertThat(NormalFrame.create().isEnd()).isFalse();
    }

    @Test
    @DisplayName("첫 번째 투구에서 스트라이크가 아닌 스코어를 획득해도 종료되지 않은 상태여야 합니다.")
    void isNotEndWhenFirstShootIsNotStrike() {
        Frame fiveShootFrame = NormalFrame.create().shoot(FIVE_SCORE);
        assertThat(fiveShootFrame.isEnd()).isFalse();
    }

    @Test
    @DisplayName("첫 번째 투구에서 스트라이크를 치는 순간 종료된 상태가 됩니다")
    void isEndWhenStrike() {
        Frame strikeFrame = NormalFrame.create().shoot(STRIKE);
        assertThat(strikeFrame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("스트라이크, 스페어 등의 사유로 종료된 프레임에서 더 투구할 경우 예외 발생")
    void failIsEndFrame() {
        Frame strikeFrame = NormalFrame.create().shoot(STRIKE);
        assertThatThrownBy(() -> strikeFrame.shoot(STRIKE))
                .isInstanceOf(IllegalArgumentException.class);
    }
}