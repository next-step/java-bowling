package bowling.domain;

import bowling.domain.Frame;
import bowling.domain.Score;

/**
 * Created : 2020-12-16 오전 9:09
 * Developer : Seo
 */
public class FinalFrame extends Frame {

    public static final String FINAL_FRAME_NO = "10";

    public FinalFrame(Score score) {
        super(score);
//        System.out.println(frameNo + " : " + score.get());
//        if (isStrike()) {
//            bowl();
//        }

    }


}
