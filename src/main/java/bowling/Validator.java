package bowling;

public class Validator {
    private static final int PLAYER_NAME_LENGTH = 3;
    public static final int MIN_HIT_PIN = 0;
    public static final int MAX_HIT_PIN = 10;

    public static String checkPlayerName(String playerName) {
        playerName = playerName.toUpperCase();
        checkNameLength(playerName);
        checkNameLanguage(playerName);
        return playerName;
    }

    private static void checkNameLength(String playerName) {
        if (playerName.length() > PLAYER_NAME_LENGTH) {
            throw new IllegalArgumentException("플레이어 이름은 3자를 넘을 수 없습니다.");
        }
    }

    private static void checkNameLanguage(String playerName) {
        for (int i = 0; i < PLAYER_NAME_LENGTH; i++) {
            int index = playerName.charAt(i);
            if (index < 65 || index > 90) {
                throw new IllegalArgumentException("플레이어 이름은 영어만 가능합니다.");
            }
        }
    }

    public static int checkHitPinCount(int hitPinCount) {
        if(hitPinCount < MIN_HIT_PIN || hitPinCount > MAX_HIT_PIN) {
            throw new IllegalArgumentException("쓰러진 볼링핀은 0~10개중 하나만 입력 가능합니다.");
        }
        return hitPinCount;
    }

    public static void checkFrameHitPinCount(int firstHitPinCount, int secondHitPinCount) {
        if(firstHitPinCount + secondHitPinCount > MAX_HIT_PIN) {
            throw new IllegalArgumentException("두번의 투구에서 쓰러진 볼링핀은 10개를 초과할 수 없습니다.");
        }
    }
}
