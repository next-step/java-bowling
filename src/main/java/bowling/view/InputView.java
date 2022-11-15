package bowling.view;

import java.io.BufferedReader;
import java.io.IOException;

public class InputView implements AutoCloseable {

    private final BufferedReader bufferedReader;

    public InputView(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public String getUsername() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getNumber() throws IOException {
        try {
            return Integer.parseInt(bufferedReader.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값은 숫자여야만 합니다.", e);
        }
    }

    @Override
    public void close() throws Exception {
        bufferedReader.close();
    }

}
