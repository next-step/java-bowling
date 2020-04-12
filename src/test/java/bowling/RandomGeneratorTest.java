package bowling;

import bowling.domain.RandomGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomGeneratorTest {
    @DisplayName("첫 번째 포인트는 0 이상 10 이하의 숫자로 발생된다.")
    @Test
    void generateFirstPoint() {
        RandomGenerator randomGenerator = new RandomGenerator();

        IntStream.range(0, 1000)
                .forEach(it -> assertThat(randomGenerator.getFirstPoint())
                        .isLessThanOrEqualTo(10)
                        .isGreaterThanOrEqualTo(0));
    }

    @DisplayName("첫 번째 포인트와 두 번째 포인트의 합은 10이거나 10보다 작아야 한다.")
    @Test
    void generateSecondPoint() {
        RandomGenerator randomGenerator = new RandomGenerator();

        IntStream.range(0, 1000)
                .forEach(it -> assertThat(randomGenerator.getFirstPoint() + randomGenerator.getSecondPoint())
                        .isLessThanOrEqualTo(10));
    }

    @DisplayName("10회에 발생한 스페어를 위한 세 번째 포인트는 0 이상 10 이하의 숫자로 발생된다.")
    @Test
    void generateThirdPoint() {
        RandomGenerator randomGenerator = new RandomGenerator();

        IntStream.range(0, 1000)
                .forEach(it -> assertThat(randomGenerator.getThirdPoint())
                        .isLessThanOrEqualTo(10)
                        .isGreaterThanOrEqualTo(0));
    }

    /*
    각 프레임의 첫 투구가 스트라이크면 두 번째 투구는 0으로 설정했기 때문에,
    본 코드에서는 10프레임의 첫 투구가 스트라이크일 경우,
    코드 상으로는 총 4회의 투구가 진행되며, 외관상으로는 3회의 투구가 진행되는 것처럼 보인다.
     */
    @DisplayName("10회에 발생한 스트라이크를 위한 세 번 포인트와 네 번째 포인트의 합은 10이거나 10보다 작아야 한다.")
    @Test
    void generateThirdAndFourthPointWhenStrike() {
        RandomGenerator randomGenerator = new RandomGenerator();

        IntStream.range(0, 1000)
                .forEach(it -> assertThat(
                        randomGenerator.getThirdPointForStrike() + randomGenerator.getFourthPointForStrike())
                        .isLessThanOrEqualTo(10));
    }
}