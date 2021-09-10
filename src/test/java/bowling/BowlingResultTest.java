package bowling;

import bowling.model.BowlingResult;
import bowling.model.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BowlingResultTest {
    @Test
    public void 스트라이크_반환() {
        BowlingResult bowlingResult = BowlingResult.findBowlingResult(new Point(10), 1);
        assertThat(bowlingResult).isEqualTo(BowlingResult.STRIKE);
    }

    @Test
    public void 스페어_반환() {
        BowlingResult bowlingResult = BowlingResult.findBowlingResult(new Point(10), 2);
        assertThat(bowlingResult).isEqualTo(BowlingResult.SPARE);
    }

    @Test
    public void 미스_반환() {
        BowlingResult bowlingResult = BowlingResult.findBowlingResult(new Point(9), 2);
        assertThat(bowlingResult).isEqualTo(BowlingResult.MISS);
    }

    @Test
    public void 거터_반환() {
        BowlingResult bowlingResult1 = BowlingResult.findBowlingResult(new Point(0), 1);
        assertThat(bowlingResult1).isEqualTo(BowlingResult.GUTTER);

        BowlingResult bowlingResult2 = BowlingResult.findBowlingResult(new Point(0), 1);
        assertThat(bowlingResult2).isEqualTo(BowlingResult.GUTTER);
    }

}
