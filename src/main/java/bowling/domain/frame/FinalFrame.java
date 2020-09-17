package bowling.domain.frame;

import bowling.domain.bowl.NormalBowl;
import bowling.domain.bowl.NormalBowlResult;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame extends AbstractFrame {

    private final List<NormalBowl> normalBowls = new ArrayList<>();

    public FinalFrame() {
        super(LAST_FRAME_NUMBER);
        normalBowls.add(new NormalBowl());
    }

    @Override
    public Frame bowl(int numberOfPins) {
        NormalBowlResult normalBowlResult = normalBowls.get(0).bowl(numberOfPins);
        return isCompleted(normalBowlResult) ? null : this;
    }

    public boolean isCompleted(NormalBowlResult normalBowlResult) {
        return !normalBowlResult.equals(NormalBowlResult.DEFAULT);
    }

    @Override
    public Iterator<Frame> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return String.join("|", normalBowls.stream()
                .map(normalBowl -> NormalBowlResult.getType(normalBowl).format(normalBowl))
                .collect(Collectors.toList()));
    }

}
