package domain;

import exception.DontTryException;
import org.junit.Test;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class PinsTest {

    @Test
    public void 스페어5_5() {
        List<Pin> pinList = new ArrayList<>();
        Pin pin = Pin.of(5);
        pinList.add(pin);
        pinList.add(pin);
        Pins pins = new Pins(pinList);
        assertThat(pins.toString()).isEqualTo("5|5");
    }

    @Test
    public void 스페어0_10() {
        List<Pin> pinList = new ArrayList<>();
        pinList.add(Pin.of(0));
        pinList.add(Pin.of(10));
        Pins pins = new Pins(pinList);
        assertThat(pins.toString()).isEqualTo(StringUtils.GUTTER+ StringUtils.VERTICAL_BAR+StringUtils.STRIKE);
    }

    @Test(expected = DontTryException.class)
    public void 스트라이크이후던짐() {
        List<Pin> pinList = new ArrayList<>();
        Pin pin = Pin.of(10);
        pinList.add(pin);
        pinList.add(pin);
        Pins pins = new Pins(pinList);
    }


    @Test(expected = DontTryException.class)
    public void 총합스페어이상() {
        List<Pin> pinList = new ArrayList<>();
        pinList.add(Pin.of(5));
        pinList.add(Pin.of(6));
        Pins pins = new Pins(pinList);
    }
}