package retry;

import bowling.retry.Bowling;
import bowling.retry.Frame;
import bowling.view.ResultView2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingTest {

    @Test
    @DisplayName("볼링 프레임 사이즈 테스트")
    void checkSizeFrame() {
        Bowling bowling = new Bowling();
        bowling.go(5);

        assertThat(bowling.getFrames().size()).isEqualTo(1);

        bowling.go(5);

        assertThat(bowling.getFrames().size()).isEqualTo(1);

        bowling.go(3);
        assertThat(bowling.getFrames().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("볼링 프레임 프린트 테스트")
    void getFrameInBowling() {
        Bowling bowling = new Bowling();
        bowling.go(5);
        ResultView2.printFrames(bowling.getFrames());
        bowling.go(4);
        ResultView2.printFrames(bowling.getFrames());
        bowling.go(3);
        ResultView2.printFrames(bowling.getFrames());
        bowling.go(2);
        ResultView2.printFrames(bowling.getFrames());
        bowling.go(10);
        ResultView2.printFrames(bowling.getFrames());
        bowling.go(0);
        ResultView2.printFrames(bowling.getFrames());
        bowling.go(4);
        ResultView2.printFrames(bowling.getFrames());
        bowling.go(3);
        ResultView2.printFrames(bowling.getFrames());
        bowling.go(7);
        ResultView2.printFrames(bowling.getFrames());
        bowling.go(2);
        ResultView2.printFrames(bowling.getFrames());

    }
}
