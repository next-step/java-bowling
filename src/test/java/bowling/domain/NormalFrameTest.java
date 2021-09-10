package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class NormalFrameTest {
    @DisplayName("남은 핀보다 많이 넘어뜨리는 값 넘어올 시 테스트")
    @Test
    public void exceedRemainPinsShotTest() {
        //given
        Frame frame = new NormalFrame();

        //when
        frame.playShot(new Shot(6));

        //then
        assertThatIllegalStateException().isThrownBy(() -> frame.playShot(new Shot(6)));
    }

    @DisplayName("이미 끝난 프레임에 계속 샷을 던질 시 테스트")
    @Test
    public void alreadyFinishFrameTest() {
        //given
        Frame frame = new NormalFrame();

        //when
        frame.playShot(new Shot(3));
        frame.playShot(new Shot(3));

        //then
        assertThatIllegalStateException().isThrownBy(() -> frame.playShot(new Shot(3)));
    }

    @DisplayName("남은핀 갯수 테스트")
    @Test
    public void checkRemainPinTest() {
        //given
        Frame frame = new NormalFrame();

        //when
        frame.playShot(new Shot(3));
        frame.playShot(new Shot(3));
        int remain = frame.remainPins();

        //then
        assertThat(remain).isEqualTo(4);
    }

    @DisplayName("스트라이크 테스트")
    @Test
    public void strikeTest() {
        //given
        Frame frame = new NormalFrame();

        //when
        frame.playShot(new Shot(10));
        int remain = frame.remainPins();

        //then
        assertThat(remain).isEqualTo(0);
        assertThat(frame.isFinished()).isEqualTo(true);
    }
}
