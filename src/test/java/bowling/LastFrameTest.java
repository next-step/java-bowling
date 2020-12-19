package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static bowling.Scoring.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * 마지막 프레임
 * <p>
 * * 마지막 프래임은 1..3 개의 투구를 가진다
 * [ ] ~마지막 프레임은 아홉번째 프레임이 스트라이크 인 경우 한번 더 투구 할 수 있다~
 * [x] 마지막 프레임은 첫번째 투구가 스트라이크 인 경우 두번 더 투구할 수 있다.
 * * 마지막 프레임은 더이상 투구할 수 없으면 종료한다.
 */
public class LastFrameTest {

    private Frame lastFrame;

    @BeforeEach
    void setUp() {
        lastFrame = new NormalFrame(9).throwBall(10);
    }

    @DisplayName("9번째 프레임이 끝나면 LastFrame 을 리턴한다")
    @Test
    void ninthFrameNext() {
        assertAll(
                // 스트라이크
                () -> assertThat(new NormalFrame(9).throwBall(10))
                        .isInstanceOf(LastFrame.class),
                // 스페어
                () -> assertThat(new NormalFrame(9).throwBall(2).throwBall(8))
                        .isInstanceOf(LastFrame.class),
                // 미스
                () -> assertThat(new NormalFrame(9).throwBall(2).throwBall(7))
                        .isInstanceOf(LastFrame.class),
                // 거터
                () -> assertThat(new NormalFrame(9).throwBall(0).throwBall(0))
                        .isInstanceOf(LastFrame.class)
        );
    }

    @DisplayName("마지막 프레임은 첫번째 투구가 스트라이크 인 경우 두번 더 투구할 수 있다")
    @Test
    void lastFrame3TimesThrow() {
        Frame first = lastFrame.throwBall(10);
        assertThat(first.getScoring()).isEqualTo(STRIKE.asOptional());
        Frame second = first.throwBall(10);
        assertThat(second.getScoring()).isEqualTo(STRIKE.asOptional());
        Frame third = first.throwBall(10);
        assertThat(third.getScoring()).isEqualTo(STRIKE.asOptional());
    }

    @DisplayName("마지막 프레임의 미스, 스페어, 거터")
    @ParameterizedTest
    @CsvSource({"8,1,MISS", "8,2,SPARE", "0,0,GUTTER"})
    void lastFrameScoring(int firstThrow, int secondThrow, String scoringName) {
        assertThat(lastFrame.throwBall(firstThrow).throwBall(secondThrow).getScoring())
                .isEqualTo(Scoring.valueOf(scoringName).asOptional());
    }

    @DisplayName("프레임이 끝나지 않으면 반환할 상태가 없다")
    @Test
    void incomplete() {
        assertThat(lastFrame.getScoring()).isEqualTo(Optional.empty());
        lastFrame.throwBall(0);
        assertThat(lastFrame.getScoring()).isEqualTo(Optional.empty());
    }

    @DisplayName("첫번째 스트라이크인 상태에서 미스, 스페어, 거터")
    @ParameterizedTest
    @CsvSource({"8,1,MISS", "8,2,SPARE", "0,0,GUTTER"})
    void lastFrameFirstStrikeAndScoring(int firstThrow, int secondThrow, String scoringName) {
        lastFrame.throwBall(10);
        assertThat(lastFrame.throwBall(firstThrow).throwBall(secondThrow).getScoring())
                .isEqualTo(Scoring.valueOf(scoringName).asOptional());
    }

    @DisplayName("미스는 세번째 던지기를 할 수 없다")
    @Test
    void missAndLastThrow() {
        lastFrame.throwBall(8).throwBall(1);
        assertThatThrownBy(() -> lastFrame.throwBall(10))
                .isInstanceOf(IllegalBallThrownException.class);
    }

    static class LastFrame implements Frame {

        private final List<BallThrow> ballThrows = new ArrayList<>();

        @Override
        public Frame throwBall(int fallingPins) {
            if (ballThrows.isEmpty()) {
                ballThrows.add(new BallThrow(fallingPins, true));
                return this;
            }

            if (ballThrows.size() == 1) {
                ballThrows.add(getLastThrow().throwSecond(fallingPins));
                return this;
            }

            ballThrows.add(getLastThrow().throwThird(fallingPins, ballThrows.get(0)));
            return this;
        }

        @Override
        public Optional<Scoring> getScoring() {
            if (ballThrows.isEmpty()) {
                return Optional.empty();
            }

            if (getLastThrow().isStrike()) {
                return STRIKE.asOptional();
            }

            if (isNotFirstThrowStrike() && ballThrows.size() < 2) {
                return Optional.empty();
            }

            return Scoring.nonStrikeValueOf(sumOfFallingPins())
                    .asOptional();
        }

        @Override
        public int getNumber() {
            return 0;
        }

        @Override
        public int sumOfFallingPins() {
            if (!isNotFirstThrowStrike() && ballThrows.size() == 3) {
                return ballThrows.stream()
                        .skip(1)
                        .mapToInt(BallThrow::getFallingPins)
                        .sum();
            }
            return ballThrows.stream()
                    .mapToInt(BallThrow::getFallingPins)
                    .sum();
        }

        private BallThrow getLastThrow() {
            return ballThrows.get(ballThrows.size() - 1);
        }

        private boolean isNotFirstThrowStrike() {
            return !ballThrows.get(0).isStrike();
        }
    }
}
