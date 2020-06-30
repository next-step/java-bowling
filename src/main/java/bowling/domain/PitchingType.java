package bowling.domain;

public enum PitchingType {
    STRIKE {
        @Override
        public Pitching extract(Pitching pitching, FallenPinNumber fallenPinNumber) {
            return StrikePitching.of(fallenPinNumber);
        }
    },
    SPARE {
        @Override
        public Pitching extract(Pitching pitching, FallenPinNumber fallenPinNumber) {
            FirstPitching firstPitching = (FirstPitching) pitching;
            return firstPitching.toSparePitching(fallenPinNumber);
        }
    },
    MISS {
        @Override
        public Pitching extract(Pitching pitching, FallenPinNumber fallenPinNumber) {
            FirstPitching firstPitching = (FirstPitching) pitching;
            return firstPitching.toMissingPitching(fallenPinNumber);
        }
    },
    GUTTER {
        @Override
        public Pitching extract(Pitching pitching, FallenPinNumber fallenPinNumber) {
            throw new RuntimeException("Gutter 는 축출할 수 없습니다.");
        }
    };

    public abstract Pitching extract(Pitching pitching, FallenPinNumber fallenPinNumber);
}
