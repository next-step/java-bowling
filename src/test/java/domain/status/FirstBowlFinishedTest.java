package domain.status;

import domain.pin.Pin;
import org.junit.Test;

import static domain.pin.Pin.MAXIMUM_PINS;
import static domain.pin.Pin.MINIMUM_PINS;
import static domain.status.Spare.SPARE_DISPLAY_STRING;
import static org.assertj.core.api.Assertions.assertThat;

public class FirstBowlFinishedTest {

    @Test
    public void getNext_for_spare() {
        for(int i = MINIMUM_PINS; i < MAXIMUM_PINS; ++i) {
            Pin firstTrial = Pin.of(i);
            Pin secondTrial = Pin.of(MAXIMUM_PINS-i);

            Status status = new FirstBowlFinished(firstTrial).getNext(secondTrial);
            assertThat(status).isInstanceOf(Spare.class);
            assertThat(status.isClear()).isEqualTo(true);
            assertThat(status.isNormalFrameFinished()).isEqualTo(true);
            assertThat(status.toString()).isEqualTo(SPARE_DISPLAY_STRING);
        }
    }

    @Test
    public void getNext_for_open() {
        for(int i = MINIMUM_PINS; i < MAXIMUM_PINS; ++i) {
            for(int j = MINIMUM_PINS+1; j <MAXIMUM_PINS-i-1; ++j) {
                Pin firstTrial = Pin.of(i);
                Pin secondTrial = Pin.of(j);

                Status status = new FirstBowlFinished(firstTrial).getNext(secondTrial);
                assertThat(status).isInstanceOf(Open.class);
                assertThat(status.isClear()).isEqualTo(false);
                assertThat(status.isNormalFrameFinished()).isEqualTo(true);
                assertThat(status.toString()).isEqualTo(secondTrial.getPin() + "");
            }
        }
    }
}