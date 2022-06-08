package refactor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {
    @Test
    void subtotal은두번다쳤을때기록() {
        Frames frames = Frames.create();
        Frame first = frames.first();
        first.pitchManual(5, frames);
        assertThat(first.subtotal().value()).isZero();
        first.pitchManual(4, frames);
        assertThat(first.subtotal().value()).isEqualTo(9);
    }

    @Test
    void strike인경우바로subtotal기록() {
        Frames frames = Frames.create();
        Frame first = frames.first();
        first.pitchManual(10, frames);
        assertThat(first.subtotal().value()).isEqualTo(10);
    }

    @Test
    void Strike인경우다음번두번을WAIT() {
        Frames frames = Frames.create();
        Frame first = frames.first();
        first.pitchManual(10, frames);
        assertThat(first.subtotal().state()).isEqualTo(State.WAIT_TWICE);
        Frame second = frames.next(first);
        second.updateSubtotal(first.subtotal());
        second.pitchManual(1, frames);
        assertThat(first.subtotal()).isEqualTo(new Subtotal(State.WAIT_ONCE, 11));
        second.pitchManual(3, frames);
        assertThat(first.subtotal()).isEqualTo(new Subtotal(State.DONE, 14));
    }
}
