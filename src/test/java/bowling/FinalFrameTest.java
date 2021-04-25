package bowling;

import bowling.domain.exception.CannotBowlException;
import bowling.domain.exception.NoRemainingFrameException;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalFrameTest {
    private Frame finalFrame;

    @BeforeEach
    void setUp() {
        finalFrame = FinalFrame.of();
    }

    @Test
    @DisplayName("Miss 발생시 종료 확인 테스트")
    void isFinishedWhenMissTest() {
        finalFrame = finalFrame.bowl(3);
        finalFrame = finalFrame.bowl(5);
        assertThat(finalFrame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("Spare 발생시 종료 확인 테스트")
    void isFinishedWhenSpareTest() {
        finalFrame = finalFrame.bowl(6);
        finalFrame = finalFrame.bowl(4);
        assertThat(finalFrame.isFinished()).isFalse();
    }

    @Test
    @DisplayName("Spare-Continue 발생시 종료 확인 테스트")
    void isFinishedWhenSpareContinueTest() {
        finalFrame = finalFrame.bowl(6);
        finalFrame = finalFrame.bowl(4);
        finalFrame = finalFrame.bowl(5);
        assertThat(finalFrame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("Strike 발생시 종료 확인 테스트")
    void isFinishedWhenStrikeTest() {
        finalFrame = finalFrame.bowl(10);
        assertThat(finalFrame.isFinished()).isFalse();
    }

    @Test
    @DisplayName("Double 발생시 종료 확인 테스트")
    void isFinishedWhenDoubleTest() {
        finalFrame = finalFrame.bowl(10);
        finalFrame = finalFrame.bowl(10);
        assertThat(finalFrame.isFinished()).isFalse();
    }

    @Test
    @DisplayName("Turkey 발생시 종료 확인 테스트")
    void isFinishedWhenTurkeyTest() {
        finalFrame = finalFrame.bowl(10);
        finalFrame = finalFrame.bowl(10);
        finalFrame = finalFrame.bowl(10);
        assertThat(finalFrame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("bowl 예외 테스트")
    void cannotBowlExceptionTest() {
        finalFrame = finalFrame.bowl(3);
        finalFrame = finalFrame.bowl(5);
        assertThatThrownBy(() -> finalFrame.bowl(3))
                .isInstanceOf(CannotBowlException.class)
                .hasMessage("더 이상 투구할 수 없습니다.");
    }

    @Test
    @DisplayName("NoRemainingFrame 예외 테스트")
    void noRemainingFrameExceptionTest() {
        assertThatThrownBy(() -> finalFrame.getNext())
                .isInstanceOf(NoRemainingFrameException.class)
                .hasMessage("다음 프레임이 존재하지 않습니다.");
    }
}
