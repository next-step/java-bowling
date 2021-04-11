package bowling.domain;

public class NormalFrame extends Frame {

   public boolean hasSecond() {
        return !pinNumbers.index(0).isStrike();
   }
}
