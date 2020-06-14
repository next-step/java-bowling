package bowling;

public class FinalFrame implements Frame{

    private int numberOfDownPin;
    private int round;

    @Override
    public String play(int numberOfDownPin) {
        if(!hasNextRound()){
            throw new IllegalStateException("can not play");
        }

        this.round++;
        this.numberOfDownPin += numberOfDownPin;

        if (numberOfDownPin == 0) {
            return "-";
        }

        if (numberOfDownPin == 10) {
            return "X";
        }

        return String.valueOf(numberOfDownPin);
    }

    @Override
    public boolean hasNextRound() {
        if(this.round > 2){
            return false;
        }

        if(this.round < 2){
            return true;
        }

        if(this.round == 2 && this.numberOfDownPin >= 10){
            return true;
        }

        return false;
    }
}
