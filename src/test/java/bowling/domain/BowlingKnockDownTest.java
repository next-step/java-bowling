package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingKnockDownTest {

    @Test
    public void 볼링핀_쓰러뜨린수() {
        int countOfKnockDown = new TestBowlingGenerator().countOfKnockDown();
        BowlingKnockDown bowlingKnockDown = new BowlingKnockDown(countOfKnockDown);
        assertEquals(bowlingKnockDown.getCount(), countOfKnockDown);
    }
}
