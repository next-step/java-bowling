package bowling.domain.frame;

import bowling.domain.bowl.NormalBowl;
import bowling.domain.bowl.NormalBowlResult;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame extends AbstractFrame {

    public static final String DELIMITER = "|";

    private final List<NormalBowl> normalBowls = new ArrayList<>();

    public FinalFrame() {
        super(LAST_FRAME_NUMBER);
        normalBowls.add(new NormalBowl());
    }

    @Override
    public Frame bowl(int numberOfPins) {
        normalBowls.get(0).bowl(numberOfPins);
        return isCompleted() ? null : this;
    }

    public boolean isCompleted() {
        return normalBowls.get(0).isCompleted();
    }

    @Override
    public Iterator<Frame> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return String.join(DELIMITER, normalBowls.stream()
                .map(normalBowl -> NormalBowlResult.getType(normalBowl).format(normalBowl))
                .collect(Collectors.toList()));
    }

}
