package bowling.controller;

import bowling.service.BowlingService;
import bowling.view.InputView;
import bowling.view.ResultView;

/**
 * 볼링 비즈니스 로직
 */
public class BowlingGameController {

    private final InputView inputView;
    private final ResultView resultView;

    public BowlingGameController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    // 게임 시작
    public void run() {
        // 사용자 이름 입력 받기
        String name = inputView.inputUserName();
        // board 세팅 확인
        resultView.printBoard(name);
        // 볼링 게임 시작
        BowlingService bowling = new BowlingService();
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
}
