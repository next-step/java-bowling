package bowling;

import bowling.view.InputView;
import bowling.view.printer.ConsolePrinter;
import bowling.view.printer.Printer;
import bowling.view.reader.ConsoleReader;
import bowling.view.reader.Reader;

public class Application {
    public static void main(String[] args) {
        Reader reader = new ConsoleReader();
        Printer printer = new ConsolePrinter();
        InputView inputView = InputView.of(reader, printer);
    }
}
