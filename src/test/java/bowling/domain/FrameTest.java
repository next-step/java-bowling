package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class FrameTest {

    @Test
    @DisplayName("한 프레임의 첫 번째 투구 생성 테스트")
    void firstBowl() {
        assertThatCode(() -> Frame.newInstance().firstBowl(3))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("다음 투구 생성 테스트 ")
    void nextBowl() {
        assertThatCode(() -> Frame.newInstance().firstBowl(3)
                .nextBowl(5))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("마지막 투구인지 알려준다")
    void isLast() {
        Frame frame = Frame.newInstance().firstBowl(3);
        assertThat(frame.isLast(2)).isFalse();

        frame = frame.nextBowl(3);
        assertThat(frame.isLast(2)).isTrue();
    }
}
