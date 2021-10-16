package bowling.model.state;

abstract class Running implements State{
    public boolean isFinish(){
        return false;
    }
}
