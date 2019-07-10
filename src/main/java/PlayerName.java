public class PlayerName {

    private final String name;

    private PlayerName(String inputName) {
        this.name = inputName;
    }

    public static PlayerName from(String inputName) {
        return new PlayerName(inputName);
    }

    public String getName() {
        return name;
    }
}
