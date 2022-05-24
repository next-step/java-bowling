package bowling.domain;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HitStateTest {

    @Test
    void createTest() {
        assertEquals(HitState.STRIKE.hasOneMoreChance(), TRUE);
        assertEquals(HitState.SPARE.hasOneMoreChance(), TRUE);
        assertEquals(HitState.MISS.hasOneMoreChance(), FALSE);
        assertEquals(HitState.GUTTER.hasOneMoreChance(), FALSE);
        assertEquals(HitState.SPLIT.hasOneMoreChance(), FALSE);
    }

}
