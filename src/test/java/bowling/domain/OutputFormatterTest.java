package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.view.formatter.OutputFomatter;
import org.junit.jupiter.api.Test;

public class OutputFormatterTest {
    @Test
    void 빈프레임_출력() {
        OutputFomatter outputFormatter = new OutputFomatter();
        Ball ball1 = Ball.init();
        Ball ball2 = Ball.init();
        Frame rawFrame = Frame.of(ball1, ball2);

        String frame = outputFormatter.toFrame(rawFrame);

        assertThat(frame).isEqualTo("   ");
    }
    @Test
    void 스페어_출력() {
        OutputFomatter outputFormatter = new OutputFomatter();
        Ball ball1 = Ball.of(1, BallStatus.OPEN);
        Ball ball_spare = Ball.of(9, BallStatus.SPARE);
        Frame rawFrame = Frame.of(ball1, ball_spare);

        String frame = outputFormatter.toFrame(rawFrame);

        assertThat(frame).isEqualTo("1|/");
    }

    @Test
    void 스트라이크_출력() {
        OutputFomatter outputFormatter = new OutputFomatter();
        Ball ball_strike = Ball.from(10);
        Frame rawFrame = Frame.fromFirstBall(ball_strike);

        String frame = outputFormatter.toFrame(rawFrame);

        assertThat(frame).isEqualTo("X  ");
    }

    @Test
    void 거터_출력1() {
        OutputFomatter outputFormatter = new OutputFomatter();
        Ball ball1 = Ball.of(1, BallStatus.OPEN);
        Ball ball_gutter = Ball.of(0, BallStatus.GUTTER);
        Frame rawFrame = Frame.of(ball1, ball_gutter);

        String frame = outputFormatter.toFrame(rawFrame);

        assertThat(frame).isEqualTo("1|-");
    }
    @Test
    void 거터_출력2() {
        OutputFomatter outputFormatter = new OutputFomatter();
        Ball ball_gutter = Ball.of(0, BallStatus.GUTTER);
        Ball ball1 = Ball.of(1, BallStatus.OPEN);
        Frame rawFrame = Frame.of(ball_gutter, ball1);

        String frame = outputFormatter.toFrame(rawFrame);

        assertThat(frame).isEqualTo("-|1");
    }

    @Test
    void 오픈_출력() {
        OutputFomatter outputFormatter = new OutputFomatter();
        Ball ball1 = Ball.of(1, BallStatus.OPEN);
        Ball ball2 = Ball.of(2, BallStatus.OPEN);
        Frame rawFrame = Frame.of(ball1, ball2);

        String frame = outputFormatter.toFrame(rawFrame);

        assertThat(frame).isEqualTo("1|2");
    }
}
