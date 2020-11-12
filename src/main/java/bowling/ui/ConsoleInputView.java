package bowling.ui;

import java.util.Scanner;

public abstract class ConsoleInputView<T> implements View {

    Scanner SCANNER = new Scanner(System.in);

    public abstract T getConsoleInput();
}