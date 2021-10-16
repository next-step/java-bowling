package bowling.model.state;

abstract class Finished implements State{
    public boolean isFinish(){
        return true;
    }
}
