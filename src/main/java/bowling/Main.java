package bowling;

public class Main {
    public static void main(String[] args) {

        try {
            BowlingController bowlingController = new BowlingController();
            bowlingController.start();
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
