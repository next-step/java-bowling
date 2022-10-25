package bowling.view;

import java.io.BufferedReader;
import java.io.IOException;

public class InputView implements AutoCloseable{

    private final BufferedReader bufferedReader;

    public InputView(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public String getUsername() throws IOException {
        return bufferedReader.readLine();
    }

    public Integer getKnockDownPinNumber() throws IOException {
        return Integer.parseInt(bufferedReader.readLine());
    }

    @Override
    public void close() throws Exception {
        bufferedReader.close();
    }
}
