package bowling.view;

import bowling.application.Response;

public class ResultView {

    public static void print(Response response) {
        System.out.println(response.view());
    }
}
