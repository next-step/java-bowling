package bowling.ui;

import java.util.List;

public interface Output {

    void print(String line);

    void printLine(String line);

    void printRow(String tabName, List<String> cells);
}
