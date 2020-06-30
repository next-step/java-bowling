package bowling.step2.domain.pitch;

public class NormalPitch extends Pitch {

    public NormalPitch(int pitch, PitchType pitchType) {
        super(pitch, pitchType);
    }

    public Pitch firstPitch() {
        if (isClear(pitch)){
            return new NormalPitch(pitch, PitchType.STRIKE);
        }
        if (isAllMiss(pitch)){
            return new NormalPitch(pitch, PitchType.GUTTER);
        }
        return this;
    }

    @Override
    public Pitch nextPitch(int pitch) {
        if (isClear(this.pitch + pitch)){
            return new NormalPitch(pitch, PitchType.SPARE);
        }
        if (isAllMiss(pitch)){
            return new NormalPitch(pitch, PitchType.GUTTER);
        }
        return new NormalPitch(pitch, PitchType.DEFAULT);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
