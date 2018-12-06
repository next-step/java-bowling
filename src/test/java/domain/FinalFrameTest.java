package domain;

import exception.DontTryException;
import org.junit.Before;
import org.junit.Test;
import utils.StringUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class FinalFrameTest {
    private FinalFrame finalFrame;

    @Before
    public void setUp() throws Exception {
        finalFrame = new FinalFrame();
    }

    @Test
    public void 일반() {
        Pin pin = Pin.of(4);
        finalFrame.addPin(pin);
        finalFrame.addPin(pin);
        assertThat(finalFrame.toString()).isEqualTo("4|4");
    }

    @Test
    public void 스페어() {
        Pin pin = Pin.of(5);
        finalFrame.addPin(pin);
        finalFrame.addPin(pin);
        finalFrame.addPin(pin);

        assertThat(finalFrame.toString()).isEqualTo("5|/|5");
    }

    @Test
    public void 스트라이크1() {
        finalFrame.addPin(Pin.of(10));

        assertThat(finalFrame.toString()).isEqualTo("X");
    }

    @Test
    public void 스트라이크2() {
        finalFrame.addPin(Pin.of(10));
        finalFrame.addPin(Pin.of(10));

        assertThat(finalFrame.toString()).isEqualTo("X|X");
    }
    @Test
    public void 스트라이크3() {
        finalFrame.addPin(Pin.of(10));
        finalFrame.addPin(Pin.of(10));
        finalFrame.addPin(Pin.of(10));

        assertThat(finalFrame.toString()).isEqualTo("X|X|X");
    }

    @Test
    public void 스페어0_10() {
        finalFrame.addPin(Pin.of(0));
        finalFrame.addPin(Pin.of(10));
        finalFrame.addPin(Pin.of(10));

        assertThat(finalFrame.toString()).isEqualTo("-|/|X");
    }

    @Test
    public void 스트라이크이후던짐() {
        Pin pin = Pin.of(10);
        finalFrame.addPin(pin);
        finalFrame.addPin(pin);
        finalFrame.addPin(pin);

        assertThat(finalFrame.toString()).isEqualTo("X|X|X");
    }


    @Test(expected = DontTryException.class)
    public void 총합스페어이상() {
        Pin pin = Pin.of(6);
        Pin pin1 = Pin.of(5);
        finalFrame.addPin(pin);
        finalFrame.addPin(pin1);
    }

    @Test
    public void 스트라이크패스여부() {
        Pin pin = Pin.of(10);
        finalFrame.addPin(pin);
        finalFrame.addPin(pin);
        finalFrame.addPin(pin);

        assertThat(finalFrame.isStrikePass()).isFalse();
    }

    @Test
    public void isStrikePass() {
        Pin pin = Pin.of(10);
        finalFrame.addPin(pin);
        finalFrame.addPin(pin);
        finalFrame.addPin(pin);

        Frame nextFrame = finalFrame.nextFrame();
        assertThat(finalFrame.isFinal()).isTrue();
        assertThat(nextFrame).isNull();
    }
}