package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingKnockDownTest {

    @ParameterizedTest
    @DisplayName("쓰러뜨린 볼링핀 수 확인")
    @ValueSource(ints = {10, 9, 7, 0})
    public void 볼링핀_쓰러뜨린수(int countOfKnockDown) {
        BowlingKnockDown bowlingKnockDown = new BowlingKnockDown(countOfKnockDown);
        assertEquals(bowlingKnockDown.getCount(), countOfKnockDown);
    }
}
