package bowling_step3.domain;

import java.util.List;

public interface Frame {
    void playManual(int i, Frames frames);

    void playRandom(Frames frames);

    boolean done();

    Subtotal subtotal();

    void updateNextSubtotal(Subtotal subtotal);

    List<Integer> scores();

    void updateSubtotal(Frames frames);
}
