package bowling.view;

import bowling.domain.Player;
import bowling.domain.Players;

/**
 * Created : 2020-12-16 오전 11:23
 * Developer : Seo
 */
public class ResultView {

    public static void init() {
        header();
        blank();
        blank();
    }

    public static void print(Players players) {
        header();
        scores(players);
    }

    private static void header() {
        println(Grid.header());
    }

    private static void blank() {
        println(Grid.blank());
    }

    private static void scores(Players players) {
        players.getPlayers().forEach(player -> {
            score(player);
            sum(player);
        });
    }

    private static void score(Player player) {
        println(Grid.score(player));
    }

    private static void sum(Player player) {
        println(Grid.sum(player));
    }

    private static void println(String args) {
        System.out.println(args);
    }

    private ResultView() {
    }
}
