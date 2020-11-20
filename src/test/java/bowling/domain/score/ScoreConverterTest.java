package bowling.domain.score;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class ScoreConverterTest {

    @Test
    void OneStrike() {
        List<Pin> pins = Arrays.asList(new Pin(10), new Pin(5));
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("X|5");
    }

    @Test
    void Miss() {
        List<Pin> pins = Arrays.asList(new Pin(0), new Pin(0));
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.normalFrameConvert(expect)).isEqualTo("-");
    }

    @Test
    void Normal() {
        List<Pin> pins = Arrays.asList(new Pin(5), new Pin(2));
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.normalFrameConvert(expect)).isEqualTo("5|2");
    }

    @Test
    void Spare() {
        List<Pin> pins = Arrays.asList(new Pin(5), new Pin(5));
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.normalFrameConvert(expect)).isEqualTo("5|/");
    }

    @Test
    void Strike() {
        List<Pin> pins = Arrays.asList(new Pin(10));
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.normalFrameConvert(expect)).isEqualTo("X");
    }

    @Test
    void Gutter() {
        List<Pin> pins = Arrays.asList(new Pin(1), new Pin(0));
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.normalFrameConvert(expect)).isEqualTo("1|G");
    }

    @Test
    void OneGutter() {
        List<Pin> pins = Arrays.asList(new Pin(0));
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.normalFrameConvert(expect)).isEqualTo("G");
    }

    @Test
    void OneNormal() {
        List<Pin> pins = Arrays.asList(new Pin(5));
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.normalFrameConvert(expect)).isEqualTo("5");
    }

    @Test
    void Double() {
        List<Pin> pins = Arrays.asList(new Pin(10), new Pin(10));
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("X|X");
    }

    @Test
    void RolTwice() {
        List<Pin> pins = Arrays.asList(new Pin(5), new Pin(2));
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("5|2");
    }

    @Test
    void OneStrikeAndNormal() {
        List<Pin> pins = Arrays.asList(new Pin(10), new Pin(5));
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("X|5");
    }

    @Test
    void OneAndGutter() {
        List<Pin> pins = Arrays.asList(new Pin(10), new Pin(0));
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("X|G");
    }

    @Test
    void OneStrikeAndSpare() {
        List<Pin> pins = Arrays.asList(new Pin(10), new Pin(5), new Pin(5));
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("X|5|/");
    }

    @Test
    void Turkey() {
        List<Pin> pins = Arrays.asList(new Pin(10), new Pin(10), new Pin(10));
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("X|X|X");
    }

    @Test
    void DoubleAndNormal() {
        List<Pin> pins = Arrays.asList(new Pin(10), new Pin(10), new Pin(5));
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("X|X|5");
    }

    @Test
    void DoubleAndGutter() {
        List<Pin> pins = Arrays.asList(new Pin(10), new Pin(10), new Pin(0));
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("X|X|G");
    }

    @Test
    void SpareAndBonus() {
        List<Pin> pins = Arrays.asList(new Pin(5), new Pin(5), new Pin(5));
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("5|/|5");
    }

    @Test
    void SpareAndGutter() {
        List<Pin> pins = Arrays.asList(new Pin(5), new Pin(5), new Pin(0));
        Pins expect = new Pins(pins);

        assertThat(ScoreConverter.finalFrameConvert(expect)).isEqualTo("5|/|G");
    }
}
