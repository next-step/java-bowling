package bowling;

import bowling.domain.BowlingResult;
import bowling.domain.FrameNumber;
import bowling.domain.PointSymbol;
import bowling.view.ViewFrameResult;

import java.util.List;
import java.util.stream.Collectors;

class SimpleViewFrameResult implements ViewFrameResult {
    private final BowlingResult bowlingResult;

    public SimpleViewFrameResult(BowlingResult bowlingResult) {
        this.bowlingResult = bowlingResult;
    }

    @Override
    public String playerName() {
        return bowlingResult.player().name();
    }

    @Override
    public List<String> symbols(int frameNumber) {
        return bowlingResult.result(new FrameNumber(frameNumber))
                .pointSymbols()
                .symbols()
                .stream()
                .map(PointSymbol::symbol)
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    @Override
    public String score(int frame) {
        return bowlingResult.result(new FrameNumber(frame))
                .score()
                .toString();
    }
}