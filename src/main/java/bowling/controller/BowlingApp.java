package bowling.controller;

import bowling.domain.Score;
import bowling.view.Input;
import bowling.view.Output;

public class BowlingApp {
    public static void main (String[] args) {
        String name = Input.scan("put your nick name(3 charactors): ");
        Score score = new Score(name);
        Output.print("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }
}
