package bowling.domain;

public enum ScoreTypeEnum {
    STRIKE("X") {
        public String scoreType() {
            return getScoreType();
        }
    },

    SPARE("/") {
        public String scoreType () {
            return getScoreType();
        }
    },

    GUTTER("-") {
        public String scoreType () {
            return getScoreType();
        }
    };

    private String scoreType;

    private ScoreTypeEnum(String scoreType) {
        this.scoreType = scoreType;
    }

    public String getScoreType(){
        return this.scoreType;
    }


    public abstract String scoreType();
}
