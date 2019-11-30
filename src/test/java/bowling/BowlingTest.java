package bowling;

import bowling.domain.Bowling;
import bowling.domain.Frame;
import bowling.view.ResultView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class BowlingTest {

    @Test
    @DisplayName("볼링 객체를 통해 프레임 생성 여부 테스트")
    void containsFrameByBowlingTest() {
        Bowling bowling = new Bowling();
        bowling.go(5);
        Frame frame = Frame.firstFrame(5);
        assertThat(bowling.isContains(frame)).isTrue();
    }

    @Test
    @DisplayName("다음 프레임 생성 테스트")
    void createNextFrameTest() {
        Bowling bowling = new Bowling();
        bowling.go(10);
        ResultView.printBowling(bowling);
        bowling.go(5);
        ResultView.printBowling(bowling);
        bowling.go(3);
        ResultView.printBowling(bowling);
        bowling.go(6);
        ResultView.printBowling(bowling);
        bowling.go(4);
        ResultView.printBowling(bowling);
        bowling.go(2);
        ResultView.printBowling(bowling);
        bowling.go(0);
        ResultView.printBowling(bowling);
        bowling.go(0);
        ResultView.printBowling(bowling);
        bowling.go(0);
        ResultView.printBowling(bowling);
        assertThat(bowling.getFrameSize()).isEqualTo(9);
    }

    @Test
    @DisplayName("두번째 투구에서 점수가 10보다 클 때 예외 처리")
    void validateCreateFrameByOverHitPinTest() {
        Bowling bowling = new Bowling();
        bowling.go(7);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            bowling.go(4);
        });
    }
}
