package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class NumberOfHitPinTests {
    @DisplayName("맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        int hitPin = 10;
        NumberOfHitPin numberOfHitPin = new NumberOfHitPin(hitPin);
        assertThat(numberOfHitPin).isEqualTo(new NumberOfHitPin(hitPin));
    }
}
