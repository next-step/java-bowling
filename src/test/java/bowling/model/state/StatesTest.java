package bowling.model.state;

import bowling.model.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatesTest {

    States states;

    @BeforeEach
    void setUp() {
        states = new States(new Ready());
        states.add(new Ready());
    }

    @Test
    @DisplayName("마지막 데이터가 isFinish가 true면 true 반환 테스트")
    void isFinishTest() {
        assertFalse(states.isFinish());
        states.add(new Strike());
        assertTrue(states.isFinish());
    }

    @Test
    @DisplayName("마지막 데이터가 Strike면 isStrike true 반환 테스트")
    void isStrikeTest() {
        assertFalse(states.isStrike());
        states.add(new Strike());
        assertTrue(states.isStrike());
    }

    @Test
    @DisplayName("마지막 데이터가 Spare isSpare true 반환 테스트")
    void isSpareTest() {
        assertFalse(states.isSpare());
        states.add(new Spare(Pins.knockedDown(2), Pins.knockedDown(8)));
        assertTrue(states.isSpare());
    }

    @Test
    @DisplayName("getDesc 표시하는 것 테스트")
    void getDescTest() {
        states.add(new FirstBowl(Pins.knockedDown(2)));
        states.add(new Spare(Pins.knockedDown(2), Pins.knockedDown(8)));
        assertThat(states.getDesc()).isEqualTo("2|/");
    }
}
