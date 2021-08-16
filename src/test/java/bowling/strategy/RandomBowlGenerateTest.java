package bowling.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class RandomBowlGenerateTest {

    @DisplayName("랜덤으로 생성되는 숫자는 0부터 입력한 숫자 범위까지여야 한다. 숫자여야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {10, 3, 5})
    void random_number_generate(int range) {
        RandomBowlGenerate randomBowlGenerate = new RandomBowlGenerate();
        for (int i = 0; i < 100; i++) {
            assertThat(randomBowlGenerate.generate(range)).isBetween(0, range);
        }
    }
}