package bowling.domain;

public interface ScoreSheet {

    /**
     * 남아 있는 프레임이 있는지 여부
     *
     * @return
     */
    boolean isRemainingFrame();


    /**
     * 다음 프레임을 가져온다
     *
     * @return
     * @throws IllegalStateException 현재 프레임이 완료되지 않은상태인 경우 발생한다
     */
    Frame nextFrame();

    ScoreSheetReader getReader();
}


