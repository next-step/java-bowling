package domain.status;

import domain.base.BaseTest;
import domain.pin.Pin;
import domain.score.Score;
import org.junit.Test;

import static domain.pin.Pin.MAXIMUM_PINS;
import static domain.pin.Pin.MINIMUM_PINS;
import static domain.status.FirstBowlFinished.ZERO_PIN_DISPLAY_STRING;
import static domain.status.Spare.SPARE_DISPLAY_STRING;
import static org.assertj.core.api.Assertions.assertThat;

public class FirstBowlFinishedTest extends BaseTest {

    @Test
    public void getNext_for_spare() {
        for (Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS)) {
            Pin secondBowl = Pin.of(MAXIMUM_PINS - firstBowl.getPin());

            Status status = new FirstBowlFinished(firstBowl).getNext(secondBowl);
            assertThat(status).isInstanceOf(Spare.class);
            assertThat(status.isClear()).isEqualTo(true);
            assertThat(status.isNormalFrameFinished()).isEqualTo(true);
            assertThat(status.toString()).isEqualTo(SPARE_DISPLAY_STRING);
            assertThat(status.getCurrentPin()).isEqualTo(secondBowl.getPin());
            assertThat(status.getScore()).isEqualTo(Score.ofSpare());
        }
    }

    @Test
    public void getNext_for_gutter() {
        for (Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            Pin secondBowl = Pin.of(MINIMUM_PINS);

            Status status = new FirstBowlFinished(firstBowl).getNext(secondBowl);
            assertThat(status).isInstanceOf(Open.class);
            assertThat(status.isClear()).isEqualTo(false);
            assertThat(status.isNormalFrameFinished()).isEqualTo(true);
            assertThat(status.toString()).isEqualTo(ZERO_PIN_DISPLAY_STRING);
            assertThat(status.getCurrentPin()).isEqualTo(secondBowl.getPin());
            assertThat(status.getScore()).isEqualTo(Score.of(firstBowl.getPin() + secondBowl.getPin()));
        }
    }

    @Test
    public void getNext_for_open() {
        for (Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            for (Pin secondBowl : getPins(MINIMUM_PINS + 1, MAXIMUM_PINS - firstBowl.getPin() - 1)) {
                Status status = new FirstBowlFinished(firstBowl).getNext(secondBowl);

                assertThat(status).isInstanceOf(Open.class);
                assertThat(status.isClear()).isEqualTo(false);
                assertThat(status.isNormalFrameFinished()).isEqualTo(true);
                assertThat(status.toString()).isEqualTo(secondBowl.getPin() + "");
                assertThat(status.getCurrentPin()).isEqualTo(secondBowl.getPin());
                assertThat(status.getScore()).isEqualTo(Score.of(firstBowl.getPin() + secondBowl.getPin()));
            }
        }
    }
}