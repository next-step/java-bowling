package bowling.bowl;

public abstract class Running implements Bowl{
    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isStrike(){
        return false;
    }

    @Override
    public boolean isSpare(){
        return false;
    }

    @Override
    public boolean isMiss(){
        return false;
    }

    @Override
    public boolean isGutter(){
        return false;
    }

    @Override
    public boolean isFirst(){
        return false;
    }

    @Override
    public boolean isSecond(){
        return false;
    }
}
