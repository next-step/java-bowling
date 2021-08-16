package bowling.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomBowlGenerateTest {

    @DisplayName("랜덤으로 생성되는 숫자는 0부터 10까지의 숫자여야 한다.")
    @Test
    void random_number_generate() {
        RandomBowlGenerate randomBowlGenerate = new RandomBowlGenerate();
        for (int i = 0; i < 100; i++) {
            assertThat(randomBowlGenerate.generate()).isBetween(0, 10);
        }
    }
}