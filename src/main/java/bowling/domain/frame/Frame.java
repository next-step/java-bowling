package bowling.domain.frame;

import bowling.domain.state.PinsState;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Frame {

    private final int position;
    private final Pins pins;

    private Frame nextFrame;

    private Frame(int position, Pins pins) {
        this.position = position;
        this.pins = pins;
    }

    static Frame first() {
        return new Frame(0, new NormalPins());
    }

    Frame last() {
        this.nextFrame = new Frame(9, FinalPins.newInstance());
        return this.nextFrame;
    }

    Frame next() {
        this.nextFrame = new Frame(this.position + 1, new NormalPins());
        return this.nextFrame;
    }

    public void play(int numberOfDownPin) {
        this.pins.down(numberOfDownPin);
    }

    public boolean hasTurn() {
        return this.pins.hasTurn();
    }

    public PinsState createPinState() {
        return this.pins.getPinsState();
    }

    public Optional<Integer> getScore() {
        if (this.pins.hasTurn()) {
            return Optional.empty();
        }

        PinsState pinsState = createPinState();
        if (pinsState.hasMiss()) {
            return Optional.ofNullable(this.pins.sum());
        }

        if(this.nextFrame == null && !this.pins.hasTurn()){
            return Optional.ofNullable(this.pins.sum());
        }

        int nextBowlCount = 0;
        if (pinsState.hasStrike()) {
            nextBowlCount = 2;
        } else if (pinsState.hasSpare()) {
            nextBowlCount = 1;
        }

        List<Integer> downPins = this.getNextDownPins(nextBowlCount);
        if (downPins.size() < nextBowlCount) {
            return Optional.empty();
        }

        int score = this.pins.sum() + downPins.stream().reduce(0, Integer::sum);
        return Optional.of(score);
    }

    private List<Integer> getNextDownPins(int count) {
        if(this.nextFrame == null){
            return new ArrayList<>();
        }

        List<Integer> nextDownPins = this.nextFrame.createPinState().getDownPins();
        if(nextDownPins.isEmpty()){
            return new ArrayList<>();
        }

        if(nextDownPins.size() >= count){
            return nextDownPins.subList(0, count);
        }

        nextDownPins.addAll(this.nextFrame.getNextDownPins(count - nextDownPins.size()));
        return nextDownPins;
    }
}
