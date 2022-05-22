package bowling.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HitStateTest {

    @Test
    void createTest() {
        assertEquals(HitState.STRIKE.hasOneMoreChance(), true);
        assertEquals(HitState.SPARE.hasOneMoreChance(), true);
        assertEquals(HitState.MISS.hasOneMoreChance(), false);
        assertEquals(HitState.GUTTER.hasOneMoreChance(), false);
        assertEquals(HitState.SPLIT.hasOneMoreChance(), false);
    }

}
