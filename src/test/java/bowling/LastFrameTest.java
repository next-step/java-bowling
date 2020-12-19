package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 마지막 프레임
 *
 * * 마지막 프래임은 1..3 개의 투구를 가진다
 * [ ] ~마지막 프레임은 아홉번째 프레임이 스트라이크 인 경우 한번 더 투구 할 수 있다~
 * * 마지막 프레임은 첫번째 투구가 스트라이크 인 경우 두번 더 투구할 수 있다.
 * * 마지막 프레임은 더이상 투구할 수 없으면 종료한다.
 */
public class LastFrameTest {

    @DisplayName("9번째 프레임이 끝나면 LastFrame 을 리턴한다")
    @Test
    void lastFrame() {
        assertThat(new NormalFrame(9).throwBall(10))
                .isInstanceOf(LastFrame.class);
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
