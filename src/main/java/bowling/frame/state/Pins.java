package bowling.frame.state;

import bowling.frame.Score;

class Pins {
    private static final int MIN_PINS = 0;
    public static final int MAX_PINS = 10;
    
    private int falledPins;

    private Pins(int falledPins) {
        this.falledPins = falledPins;
    }
    
    private static void validPins(int falledPins) {
        if (falledPins > MAX_PINS) {
            throw new IllegalArgumentException(
                    String.format("볼링 핀은 최대 10을 넘을 수 없습니다. 현재 쓰러진 핀 수는 %d", falledPins));
        }
        
        if (falledPins < MIN_PINS) {
            throw new IllegalArgumentException(
                    String.format("볼링 핀은 최초 0 미만이 될 수 없습니다. 현재 쓰러진 핀 수는 %d", falledPins));
        }
    }

    static Pins bowl(int falledPins) {
        validPins(falledPins);
        
        return new Pins(falledPins);
    }
      
    boolean isStrike() {
        return this.falledPins == MAX_PINS;
    }
    
    int totalPins(Pins secondPins) {
        int totalPins = this.falledPins + secondPins.falledPins;
        validPins(totalPins);
        return totalPins;
    }

    boolean isSpare(Pins secondPins) {
        return totalPins(secondPins) == MAX_PINS;
    }

    boolean isMiss(Pins secondPins) {
        return totalPins(secondPins) < MAX_PINS;
    }

    Score sumScore(Score score) {
        return score.bowl(this.falledPins);
    }

    String getDesc() {
        if (isStrike()) {
            return "X";
        }
        
        return falledPins + " | ";
    }

    String getDesc(Pins secondPins) {
        if (isSpare(secondPins)) {
            return falledPins + " | /";
        }
        
        return falledPins + " | " + secondPins.falledPins;
    }
}
