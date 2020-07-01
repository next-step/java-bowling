package bowling.domain.status;

public abstract class Running implements Status{
    @Override
    public boolean canPlayMore(){
        return true;
    }
}
