package bowling.frame.state;

import bowling.frame.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinsTest {
    @Test
    public void createWhenOverMaxPins() throws Exception {
        assertThatThrownBy(() -> {
            Pins.bowl(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    public void createWhenUnderMinPins() throws Exception {
        assertThatThrownBy(() -> {
            Pins.bowl(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    public void strike() throws Exception {
        Pins pins = Pins.bowl(10);
        assertThat(pins.isStrike()).isTrue();
    }
    
    @Test
    public void spare() throws Exception {
        Pins pins = Pins.bowl(8);
        assertThat(pins.isSpare(Pins.bowl(2))).isTrue();
    }
    
    @Test
    public void spareWhenOverMaxPins() throws Exception {
        assertThatThrownBy(() -> {
            Pins pins = Pins.bowl(8);
            pins.isSpare(Pins.bowl(3));
        }).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    public void miss() throws Exception {
        Pins pins = Pins.bowl(8);
        assertThat(pins.isMiss(Pins.bowl(1))).isTrue();
    }
    
    @Test
    public void totalPins() throws Exception {
        Pins pins = Pins.bowl(8);
        assertThat(pins.totalPins(Pins.bowl(1))).isEqualTo(9);
    }
    
    @Test
    public void sumScore() throws Exception {
        Score score = new Score(10, 1);
        Pins pins = Pins.bowl(8);
        assertThat(pins.sumScore(score)).isEqualTo(new Score(18, 0));
    }
    
    @Test
    public void getDesc() throws Exception {
        assertThat(Pins.bowl(10).getDesc()).isEqualTo("X");
        assertThat(Pins.bowl(9).getDesc(Pins.bowl(1))).isEqualTo("9 | /");
        assertThat(Pins.bowl(9).getDesc()).isEqualTo("9 | ");
        assertThat(Pins.bowl(8).getDesc(Pins.bowl(1))).isEqualTo("8 | 1");
    }
}
