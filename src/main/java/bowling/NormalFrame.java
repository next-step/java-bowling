package bowling;

public class NormalFrame extends Frame {

    public boolean hasSecond() {
        return !getPinNumbers().get(0).isStrike();
   }
}
