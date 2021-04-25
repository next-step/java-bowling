package bowling;

import bowling.domain.exception.CannotBowlException;
import bowling.domain.exception.NoRemainingFrameException;
import bowling.domain.frame.Frame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameTest {
    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = Frame.init();
    }

    @Test
    @DisplayName("Ready, Strike 상태일 때 종료 확인 테스트")
    void readyStrikeTest() {
        assertThat(frame.isFinished()).isFalse();
        frame = frame.bowl(10);
        assertThat(frame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("Continue, Spare 상태일 때 종료 확인 테스트")
    void continueSpareTest() {
        frame = frame.bowl(3);
        assertThat(frame.isFinished()).isFalse();
        frame = frame.bowl(7);
        assertThat(frame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("Continue, Miss 상태일 때 종료 확인 테스트")
    void continueMissTest() {
        frame = frame.bowl(3);
        assertThat(frame.isFinished()).isFalse();
        frame = frame.bowl(5);
        assertThat(frame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("bowl 예외 테스트")
    void cannotBowlExceptionTest() {
        frame = frame.bowl(10);
        assertThatThrownBy(() -> frame.bowl(3))
                .isInstanceOf(CannotBowlException.class)
                .hasMessage("더 이상 투구할 수 없습니다.");
    }

    @Test
    @DisplayName("NoRemainingFrame 예외 테스트")
    void noRemainingFrameExceptionTest() {
        frame = Frame.of(10);
        assertThatThrownBy(() -> frame.getNext())
                .isInstanceOf(NoRemainingFrameException.class)
                .hasMessage("다음 프레임이 존재하지 않습니다.");
    }
}
