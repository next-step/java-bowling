package bowling.domain;

import java.util.HashMap;
import java.util.Map;

public class BowlingGame {

    private Map matchMap = new HashMap();
    private Frames frames;
    private Name name;

    public BowlingGame(String paramName){
        name = new Name(paramName);
        this.frames = new Frames();
    }

    public void run(){
        Print print = new Print(this.frames, this.name);
        print.printScore();
    }
}
