package bowling.view;

public class BowlingConsoleOutputView {

    public void print(final String player) {
        header();
        score(player);
    }

    private void header() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    private void score(final String player) {
        System.out.printf("|  %s |      |      |      |      |      |      |      |      |      |      |", player);
        System.out.println();
        System.out.println();
    }
}
