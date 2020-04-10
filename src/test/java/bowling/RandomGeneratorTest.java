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
        IntStream.range(0, 1000)
                .forEach(it -> assertThat(RandomGenerator.getFirstPoint()).isLessThanOrEqualTo(10));
    }
}
