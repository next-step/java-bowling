package bowling;

import bowling.domain.Frame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    @DisplayName("같은 값일 때 생성자 테스트")
    void equalConstructorTest() {
        // give
        Frame frame = Frame.first(1, 10);
        Frame frame2 = Frame.first(1, 10);
        // when
        boolean isSame = frame.equals(frame2);
        // then
        assertThat(isSame).isTrue();
    }

    @Test
    @DisplayName("프레임에서 스코어 점수 확인")
    void getPointByScoreTest() {
        // give
        Frame frame = Frame.first(0, 5).second(4);
        // when
        int firstPoint = frame.getFirstScore();
        int secondPoint = frame.getSecondScore();
        // then
        assertThat(firstPoint).isEqualTo(5);
        assertThat(secondPoint).isEqualTo(4);
    }
}
