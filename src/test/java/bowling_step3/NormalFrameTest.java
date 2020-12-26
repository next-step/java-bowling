package bowling_step3;

import bowling_step3.domain.Frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

public class NormalFrameTest {

    @ParameterizedTest
    @DisplayName("첫번째 프레임 1번 쓰러뜨린 볼링핀 수 확인")
    @CsvSource(value = {"10, X", "9, 9", "7, 7"})
    public void 첫번째_프레임_쓰러뜨린_볼링핀수(int countOfKnockDown, String expression) {
        NormalFrame normalFrame = NormalFrame.init();
        normalFrame.pitch(countOfKnockDown);
        assertEquals(normalFrame.getKnockDownExpression(), expression);
    }

    @Test
    @DisplayName("첫번째 프레임에 모두 던졌을때 쓰러뜨린 볼링핀 수 확인")
    public void 프레임_모두_쓰러뜨린_볼링핀수() {
        NormalFrame normalFrame = NormalFrame.init();
        normalFrame.pitch(3);
        normalFrame.pitch(4);
        assertEquals(normalFrame.getKnockDownExpression(), "3|4");
    }

    @ParameterizedTest
    @DisplayName("쓰러뜨린 볼링 투구 값 10이상인 경우 예외")
    @ValueSource(ints = {11, 15, 21})
    public void 볼링핀_경계값_테스트(int countOfKnockDown) {
        NormalFrame normalFrame = NormalFrame.init();

        assertThatThrownBy(() -> normalFrame.pitch(countOfKnockDown))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("볼링 핀은 최대 10개입니다.");
    }

    @Test
    @DisplayName("다음 프레임 생성")
    public void 다음_프레임_생성() {
        NormalFrame frame = NormalFrame.init();
        NormalFrame nextFrame = frame.next();
        assertThat(nextFrame).isEqualTo(new NormalFrame(1));
    }

    @Test
    @DisplayName("스트라이크 투구시 스트라이크 처리 확인")
    public void 스트라이크_투구_확인() {
        NormalFrame frame = NormalFrame.init();
        frame.pitch(10);

        assertThat(frame.getKnockDownExpression()).isEqualTo("X");
    }

    @Test
    @DisplayName("스페어 투구시 스페어 처리 확인")
    public void 스페어_투구_확인() {
        NormalFrame frame = NormalFrame.init();
        frame.pitch(1);
        frame.pitch(9);

        assertThat(frame.getKnockDownExpression()).isEqualTo("1|/");
    }

    @ParameterizedTest
    @DisplayName("각 프레임 점수 계산")
    @CsvSource(value = {"0, 0, 0", "4, 5, 9", "3, 3, 6", "7, 0, 7"})
    public void 프레임_점수_계산(int firstOfKnockDown, int secondOfKnockDown, int sum) {
        NormalFrame frame = NormalFrame.init();
        frame.pitch(firstOfKnockDown);
        frame.pitch(secondOfKnockDown);

        assertThat(frame.getScore()).isEqualTo(sum);
    }
}
