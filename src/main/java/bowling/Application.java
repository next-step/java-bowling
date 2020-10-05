package bowling;

import bowling.controller.BowlingController;
import bowling.ui.Input;
import bowling.ui.Output;

import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        BowlingController controller = new BowlingController(new StandardInput(), new SystemOutput());
        controller.inputNames();
        controller.playGame();
    }

    public static class StandardInput implements Input {

        Scanner scanner = new Scanner(System.in);

        @Override
        public int nextInt() {
            return scanner.nextInt();
        }

        @Override
        public String nextLine() {
            return scanner.nextLine();
        }
    }

    public static class SystemOutput implements Output {

        private static final String PIPE = "|";
        private static final int CELL_WIDTH = 6;

        @Override
        public void print(String line) {
            System.out.print(line);
        }

        @Override
        public void printRow(String tabName, List<String> cells) {

            System.out.print(PIPE);
            printCell(tabName);

            for (String cell : cells) {
                printCell(cell);
            }

            System.out.print("\n");
        }

        private void printCell(String cell) {
            System.out.print(centerAlign(cell) + PIPE);
        }

        private String centerAlign(String s) {
            return String.format("%-" + CELL_WIDTH + "s", String.format("%" + (s.length() + (CELL_WIDTH - s.length()) / 2) + "s", s));
        }
    }
}
