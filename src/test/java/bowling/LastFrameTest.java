package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static bowling.Scoring.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * 마지막 프레임
 * <p>
 * * 마지막 프래임은 1..3 개의 투구를 가진다
 * [ ] ~마지막 프레임은 아홉번째 프레임이 스트라이크 인 경우 한번 더 투구 할 수 있다~
 * * 마지막 프레임은 첫번째 투구가 스트라이크 인 경우 두번 더 투구할 수 있다.
 * * 마지막 프레임은 더이상 투구할 수 없으면 종료한다.
 */
public class LastFrameTest {

    @DisplayName("9번째 프레임이 끝나면 LastFrame 을 리턴한다")
    @Test
    void lastFrame() {
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
        Frame lastFrame = new NormalFrame(9).throwBall(10);
        Frame first = lastFrame.throwBall(10);
        assertThat(first.getScoring()).isEqualTo(STRIKE.asOptional());
        Frame second = first.throwBall(10);
        assertThat(second.getScoring()).isEqualTo(STRIKE.asOptional());
        Frame third = first.throwBall(10);
        assertThat(third.getScoring()).isEqualTo(STRIKE.asOptional());
    }


    static class LastFrame implements Frame {
        @Override
        public Frame throwBall(int fallingPins) {
            return null;
        }

        @Override
        public Optional<Scoring> getScoring() {
            return Optional.empty();
        }

        @Override
        public int getNumber() {
            return 0;
        }

        @Override
        public int sumOfFallingPins() {
            return 0;
        }
    }
}
