package bowling.controller;

import bowling.domain.Game;

public class Main {
    private Main() {}

    public static void main(String[] args) {
        Game game = ModelMapper.getGame();
        game.play();
    }
}
