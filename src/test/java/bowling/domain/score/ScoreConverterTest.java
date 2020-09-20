package bowling.domain.score;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class ScoreConverterTest {

    @Test
    void OneStrike() {
        List<Pin> pins = new ArrayList<>();
        Pin pin = new Pin(10);
        Pin pin2 = new Pin(5);
        pins.add(pin);
        pins.add(pin2);
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("X|5");
    }

    @Test
    void Miss() {
        List<Pin> pins = new ArrayList<>();
        Pin pin = new Pin(0);
        Pin pin2 = new Pin(0);
        pins.add(pin);
        pins.add(pin2);
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.normalFrameConvert(expect)).isEqualTo("-");
    }

    @Test
    void Normal() {
        List<Pin> pins = new ArrayList<>();
        Pin pin = new Pin(5);
        Pin pin2 = new Pin(2);
        pins.add(pin);
        pins.add(pin2);
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.normalFrameConvert(expect)).isEqualTo("5|2");
    }

    @Test
    void Spare() {
        List<Pin> pins = new ArrayList<>();
        Pin pin = new Pin(5);
        Pin pin2 = new Pin(5);
        pins.add(pin);
        pins.add(pin2);
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.normalFrameConvert(expect)).isEqualTo("5|/");
    }

    @Test
    void Strike() {
        List<Pin> pins = new ArrayList<>();
        Pin pin = new Pin(10);
        pins.add(pin);
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.normalFrameConvert(expect)).isEqualTo("X");
    }

    @Test
    void Gutter() {
        List<Pin> pins = new ArrayList<>();
        Pin pin = new Pin(1);
        Pin pin2 = new Pin(0);
        pins.add(pin);
        pins.add(pin2);
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.normalFrameConvert(expect)).isEqualTo("1|G");
    }

    @Test
    void OneGutter() {
        List<Pin> pins = new ArrayList<>();
        Pin pin = new Pin(0);

        pins.add(pin);
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.normalFrameConvert(expect)).isEqualTo("G");
    }

    @Test
    void OneNormal() {
        List<Pin> pins = new ArrayList<>();
        Pin pin = new Pin(5);

        pins.add(pin);
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.normalFrameConvert(expect)).isEqualTo("5");
    }

    @Test
    void Double() {
        List<Pin> pins = new ArrayList<>();
        Pin pin = new Pin(10);
        Pin pin2 = new Pin(10);

        pins.add(pin);
        pins.add(pin2);
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("X|X");
    }


    @Test
    void RolTwice() {
        List<Pin> pins = new ArrayList<>();
        Pin pin = new Pin(5);
        Pin pin2 = new Pin(2);

        pins.add(pin);
        pins.add(pin2);
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("5|2");
    }

    @Test
    void OneStrikeAndNormal() {
        List<Pin> pins = new ArrayList<>();
        Pin pin = new Pin(10);
        Pin pin2 = new Pin(5);

        pins.add(pin);
        pins.add(pin2);
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("X|5");
    }

    @Test
    void OneAndGutter() {
        List<Pin> pins = new ArrayList<>();
        Pin pin = new Pin(10);
        Pin pin2 = new Pin(0);

        pins.add(pin);
        pins.add(pin2);
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("X|G");
    }

    @Test
    void OneStrikeAndSpare() {
        List<Pin> pins = new ArrayList<>();
        Pin pin = new Pin(10);
        Pin pin2 = new Pin(5);
        Pin pin3 = new Pin(5);

        pins.add(pin);
        pins.add(pin2);
        pins.add(pin3);
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("X|5|/");
    }

    @Test
    void Turkey() {
        List<Pin> pins = new ArrayList<>();
        Pin pin = new Pin(10);
        Pin pin2 = new Pin(10);
        Pin pin3 = new Pin(10);

        pins.add(pin);
        pins.add(pin2);
        pins.add(pin3);
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("X|X|X");
    }

    @Test
    void DobuleAndNormal() {
        List<Pin> pins = new ArrayList<>();
        Pin pin = new Pin(10);
        Pin pin2 = new Pin(10);
        Pin pin3 = new Pin(5);

        pins.add(pin);
        pins.add(pin2);
        pins.add(pin3);
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("X|X|5");
    }

    @Test
    void DobuleAndGutter() {
        List<Pin> pins = new ArrayList<>();
        Pin pin = new Pin(10);
        Pin pin2 = new Pin(10);
        Pin pin3 = new Pin(0);

        pins.add(pin);
        pins.add(pin2);
        pins.add(pin3);
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("X|X|G");
    }

    @Test
    void SpareAndBonus() {
        List<Pin> pins = new ArrayList<>();
        Pin pin = new Pin(5);
        Pin pin2 = new Pin(5);
        Pin pin3 = new Pin(5);

        pins.add(pin);
        pins.add(pin2);
        pins.add(pin3);
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("5|/|5");
    }

    @Test
    void SpareAndGutter() {
        List<Pin> pins = new ArrayList<>();
        Pin pin = new Pin(5);
        Pin pin2 = new Pin(5);
        Pin pin3 = new Pin(0);

        pins.add(pin);
        pins.add(pin2);
        pins.add(pin3);
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("5|/|G");
    }
}
