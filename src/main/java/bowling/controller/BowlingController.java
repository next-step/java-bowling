package bowling.controller;

import bowling.domain.Bowling;

public class BowlingController {
    private Bowling bowling;

    public BowlingController(){
        bowling = new Bowling();
    }

    public void play(){
        while(!bowling.isEnd()){
            bowling.play(1);
        }
    }
}
