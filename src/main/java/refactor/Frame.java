package refactor;

import java.util.List;

public interface Frame {
    void pitchManual(int i, Frames frames);

    void pitchRandom(Frames frames);

    boolean done();

    Subtotal subtotal();

    void updateNextSubtotal(Subtotal subtotal);

    List<Integer> scores();
}
