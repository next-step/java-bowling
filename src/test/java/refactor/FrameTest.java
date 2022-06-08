package refactor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {
    @Test
    void subtotal은두번다쳤을때기록() {
        Frame frame = new Frame();
        frame.pitch(5);
        assertThat(frame.subtotal()).isZero();
        frame.pitch(4);
        assertThat(frame.subtotal()).isEqualTo(9);
        System.out.println(frame);
//        Frame result = frame.pitches();
//        System.out.println(result);
    }

    @Test
    void strike인경우바로subtotal기록() {
        Frame frame = new Frame();
        frame.pitch(10);
        assertThat(frame.subtotal()).isEqualTo(10);
    }
}
