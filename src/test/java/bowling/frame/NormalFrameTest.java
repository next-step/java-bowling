package bowling.frame;

import bowling.domain.exception.CannotBowlException;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalFrameTest {
    private Frame normalFrame;

    @BeforeEach
    void setUp() {
        normalFrame = NormalFrame.init();
    }

    @Test
    @DisplayName("Ready, Strike 상태일 때 종료 확인 테스트")
    void readyStrikeTest() {
        assertThat(normalFrame.isFinished()).isFalse();
        normalFrame = normalFrame.bowl(10);
        assertThat(normalFrame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("Continue, Spare 상태일 때 종료 확인 테스트")
    void continueSpareTest() {
        normalFrame = normalFrame.bowl(3);
        assertThat(normalFrame.isFinished()).isFalse();
        normalFrame = normalFrame.bowl(7);
        assertThat(normalFrame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("Continue, Miss 상태일 때 종료 확인 테스트")
    void continueMissTest() {
        normalFrame = normalFrame.bowl(3);
        assertThat(normalFrame.isFinished()).isFalse();
        normalFrame = normalFrame.bowl(5);
        assertThat(normalFrame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("bowl 예외 테스트")
    void cannotBowlExceptionTest() {
        normalFrame = normalFrame.bowl(10);
        assertThatThrownBy(() -> normalFrame.bowl(3))
                .isInstanceOf(CannotBowlException.class)
                .hasMessage("더 이상 투구할 수 없습니다.");
    }
}
