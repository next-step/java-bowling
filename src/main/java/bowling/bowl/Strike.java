package bowling.bowl;

public class Strike extends Ended{

    @Override
    public String toString(){
        return "[Strike]";
    }


    @Override
    public String getSymbol() {
        return "X";
    }

}
