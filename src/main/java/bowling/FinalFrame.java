package bowling;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame{

    private final List<Integer> downPins = new ArrayList<>();

    @Override
    public void play(int downPin) {
        if(!hasTurn()){
            throw new IllegalStateException("can not play");
        }

        downPins.add(downPin);
    }

    @Override
    public boolean hasTurn() {
        if(this.downPins.size() > 2){
            return false;
        }

        if(this.downPins.size()  < 2){
            return true;
        }

        if(this.downPins.size()  == 2 && sumDownPin() >= 10){
            return true;
        }

        return false;
    }

    @Override
    public FrameResult getResult() {
        return new FrameResult(new ArrayList<>(this.downPins));
    }

    private int sumDownPin() {
        return this.downPins.stream().reduce(0, Integer::sum);
    }
}
