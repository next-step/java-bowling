package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.BDDAssertions.then;

public class NormalFrameTest {


    @Test
    @DisplayName("초구를 던지지 않았을 경우, 초구를 던진다.")
    public void throwBowl_first() throws Exception {
        Frame normalFrame = new NormalFrame(0).throwBowl("1");
        assertThat(normalFrame.pinCounts().pinCounts().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("초구를 던졌을 경우, 2구를 던진다.")
    public void throwBowl_second() throws Exception {
        Frame firstThrown = new NormalFrame(0).throwBowl("1");
        Frame secondThrown = firstThrown.throwBowl("2");
        assertThat(secondThrown.pinCounts().pinCounts().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("3구를 던지려 할 경우, 예외가 발생한다.")
    public void throwBowl_exception() throws Exception {
        Frame secondThrown = new NormalFrame(0).throwBowl("1").throwBowl("2");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> secondThrown.throwBowl("1"))
                .withMessage("2번을 초과하여 던질 수 없습니다.");
    }

    @Test
    @DisplayName("해당 프레임이 완료되었을 경우, 다음 프레임을 반환한다.")
    public void next() throws Exception {
        //given
        Frame lastBeforeTheFinal = new NormalFrame(8).throwBowl("1");
        Frame normal = new NormalFrame(7).throwBowl("1");

        //when
        Frame finalFrame = lastBeforeTheFinal.throwBowl("2").next();
        Frame normalFrame = normal.throwBowl("2").next();

        then(finalFrame).isInstanceOf(FinalFrame.class);
        then(normalFrame).isInstanceOf(NormalFrame.class);
    }

    @Test
    @DisplayName("해당 프레임이 완료되지 않았을 경우, 현재 프레임을 반환한다.")
    public void next_same_frame() throws Exception {
        Frame frame = new NormalFrame(8).throwBowl("1").next();
        assertThat(frame.index()).isEqualTo(8);
    }

    @Test
    @DisplayName("해당 프레임이 완료됐을 경우, 참을 반환한다.")
    public void isFinished() throws Exception {
        Frame firstThrown = new NormalFrame(0).throwBowl("1");
        Frame secondThrown = firstThrown.throwBowl("2");
        assertThat(secondThrown.isFinished()).isTrue();
    }
}
