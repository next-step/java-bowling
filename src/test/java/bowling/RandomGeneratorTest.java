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
                        .isLessThanOrEqualTo(10));
    }

    @DisplayName("두 번째 포인트는 '10-첫번째 포인트' 값 보다 같거나 작아야 한다.")
    @Test
    void generateSecondPoint() {
        RandomGenerator randomGenerator = new RandomGenerator();

        IntStream.range(0, 1000)
                .forEach(it -> assertThat(randomGenerator.getSecondPoint())
                        .isLessThanOrEqualTo(10 - randomGenerator.getFirstPoint()));
    }
}