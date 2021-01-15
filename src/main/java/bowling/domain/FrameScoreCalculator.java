package bowling.domain;

public enum FrameScoreCalculator {
    Default(new DefaultScoreRule()),
    Strike(new StrikeScoreRule()),
    Spare(new SpareScoreRule()),
    Final(new FinalScoreRule());

    private final FrameScoreRule rule;

    FrameScoreCalculator(FrameScoreRule rule){
        this.rule = rule;
    }

    FrameScore calculate(Frame frame){
        if( frame.isEnd() )
            return rule.createBaseScore(frame);
        throw new IllegalStateException("frame 이 진행중이면 점수를 계산할 수 없다");
    }

}


