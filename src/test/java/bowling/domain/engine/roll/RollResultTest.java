package bowling.domain.engine.roll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RollResultTest {

    @Test
    @DisplayName("10 핀을 한 번에 쓰러트리면 true 를 반환한다.")
    void returnTrueIfTnePinsKnockedDown() {
        for (int i = 0; i < 10; i++) {
            assertThat(new NormalResult(i).isClear()).isFalse();
        }

        assertThat(new NormalResult(10).isClear()).isTrue();
    }

    @Test
    @DisplayName("두 결과를 합쳤을 때 모두 10 핀을 쓰러트렸다면 true 를 반환한다.")
    void returnTrueIfTwoResultsAreKnockedDownTenPins() {
        NormalResult firstResult = new NormalResult(7);
        NormalResult secondResult = new NormalResult(3);
        NormalResult anotherResult = new NormalResult(2);
        GutterResult gutterResult = new GutterResult();

        assertAll(
            () -> assertThat(firstResult.isClearWith(secondResult)).isTrue(),
            () -> assertThat(firstResult.isClearWith(anotherResult)).isFalse(),
            () -> assertThat(firstResult.isClearWith(gutterResult)).isFalse()
        );
    }
    
}
