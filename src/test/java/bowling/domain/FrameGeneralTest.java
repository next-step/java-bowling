package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameGeneralTest {
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
    void done인경우다음Frame으로Subtotal전달하지만WATING이여야함() {
        Frames frames = Frames.create();
        Frame first = frames.first();
        first.pitchManual(10, frames);
        Frame second = frames.next(first);
        second.updateNextSubtotal(first.subtotal());
        assertThat(second.subtotal()).isEqualTo(new Subtotal(State.INIT, 10));
    }

    @Test
    void Strike인경우다음번두번을WAIT() {
        Frames frames = Frames.create();
        Frame first = frames.first();
        first.pitchManual(10, frames);
        assertThat(first.subtotal().state()).isEqualTo(State.WAIT_TWICE);
        Frame second = frames.next(first);
        second.updateNextSubtotal(first.subtotal());
        second.pitchManual(1, frames);
        assertThat(first.subtotal()).isEqualTo(new Subtotal(State.WAIT_ONCE, 11));
        second.pitchManual(3, frames);
        assertThat(first.subtotal()).isEqualTo(new Subtotal(State.DONE, 14));
    }

    @Test
    void 연속Strike인경우다음프레임의첫턴에DONE() {
        Frames frames = Frames.create();
        Frame first = frames.first();
        first.pitchManual(10, frames);
        assertThat(first.subtotal().state()).isEqualTo(State.WAIT_TWICE);
        Frame second = frames.next(first);
        second.updateNextSubtotal(first.subtotal());
        second.pitchManual(10, frames);
        assertThat(first.subtotal()).isEqualTo(new Subtotal(State.DONE, 20));
    }

    @Test
    void Spare_Pitch() {
        Frames frames = Frames.create();
        Frame first = frames.first();
        first.pitchManual(1, frames);
        first.pitchManual(9, frames);
        assertThat(first.subtotal().state()).isEqualTo(State.WAIT_ONCE);
        Frame second = frames.next(first);
        second.updateNextSubtotal(first.subtotal());
        second.pitchManual(1, frames);
        assertThat(first.subtotal()).isEqualTo(new Subtotal(State.DONE, 11));
    }
    @Test
    void Spare_Strike() {
        Frames frames = Frames.create();
        Frame first = frames.first();
        first.pitchManual(1, frames);
        first.pitchManual(9, frames);
        assertThat(first.subtotal().state()).isEqualTo(State.WAIT_ONCE);
        Frame second = frames.next(first);
        second.updateNextSubtotal(first.subtotal());
        second.pitchManual(10, frames);
        assertThat(first.subtotal()).isEqualTo(new Subtotal(State.DONE, 20));
    }

    @Test
    void SPARE_STRIKE_1() {
        Frames frames = Frames.create();
        Frame first = frames.first();
        first.pitchManual(6, frames);
        first.pitchManual(4, frames);
        assertThat(first.subtotal().state()).isEqualTo(State.WAIT_ONCE);
        Frame second = frames.next(first);
        second.updateNextSubtotal(first.subtotal());
        second.pitchManual(10, frames);
        assertThat(second.subtotal()).isEqualTo(new Subtotal(State.WAIT_TWICE, 30));
        Frame third = frames.next(second);
        third.updateNextSubtotal(second.subtotal());
        third.pitchManual(8, frames);
        assertThat(second.subtotal()).isEqualTo(new Subtotal(State.WAIT_ONCE, 38));
        third.pitchManual(1, frames);
        assertThat(second.subtotal()).isEqualTo(new Subtotal(State.DONE, 39));
    }

    @Test
    void Miss_Strike_Spare_Pitch() {
        Frames frames = Frames.create();
        Frame first = frames.first();
        first.pitchManual(9, frames);
        first.pitchManual(0, frames);
        assertThat(first.subtotal()).isEqualTo(new Subtotal(State.DONE, 9));
        Frame second = frames.next(first);
        second.updateNextSubtotal(first.subtotal());
        second.pitchManual(10, frames);
        assertThat(second.subtotal()).isEqualTo(new Subtotal(State.WAIT_TWICE, 19));
        Frame third = frames.next(second);
        third.updateNextSubtotal(second.subtotal());
        assertThat(third.subtotal()).isEqualTo(new Subtotal(State.INIT,19));
        third.pitchManual(1, frames);
        System.out.println(third);
        third.pitchManual(9, frames);
        System.out.println(third);
        assertThat(second.subtotal()).isEqualTo(new Subtotal(State.DONE, 29));
        assertThat(third.subtotal()).isEqualTo(new Subtotal(State.WAIT_ONCE,39));
        Frame fourth = frames.next(third);
        fourth.updateNextSubtotal(third.subtotal());
        fourth.pitchManual(8, frames);
        assertThat(fourth.subtotal()).isEqualTo(new Subtotal(State.INIT, 47));
    }
}


