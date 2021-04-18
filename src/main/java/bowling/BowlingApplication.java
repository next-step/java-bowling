package bowling;

import bowling.service.BowlingGame;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    private final InputView inputView;
    private final ResultView resultView;

    public BowlingApplication() {
        this.inputView = new InputView();
        this.resultView = new ResultView();
    }

    // 게임 시작

    public void run() {
        // 사용자 이름 입력 받기
        String name = inputView.inputUserName();
        // board 세팅 확인
        resultView.printBoard(name);
        // 볼링 게임 시작
        BowlingGame bowling = new BowlingGame();
        // 볼링 게임은 10라운드까지 반복
        while(!bowling.isLast()) {
            int tryCount = bowling.tryingCount();
            // 투구로 pin을 쓰러뜨린다.
            String pins = inputView.inputPitch(tryCount);
            // 투구 값을 프레임에 값 업데이트
            bowling.bowl(pins);
            // 투구당 결과 값 출력
            resultView.printResult(bowling);
        }
    }

    public static void main(String[] args) {
        new BowlingApplication().run();
    }

}
