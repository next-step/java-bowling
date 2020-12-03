package bowling.domain;

import bowling.dto.PlayerDto;
import bowling.exception.PlayerException;

/**
 * NOTE: Player 클래스에는 사용자 정보만을 넣고, 게임과 관련된 로직은 최소화한다.
 * 예시) 이름, 휴대폰번호, 주소
 * 이유) 로그인 혹은 인증과 관련될 수 있는 로직은, 게임 도메인에 종속되면 유지보수 및 재활용이 어려워진다.
 */
public class Player {
    private final String name;

    public Player(String name) {
        if (!name.matches("^[a-zA-Z]{3}$")) {
            throw new PlayerException("플레이어 이름은 3개의 영어 문자여야 합니다.");
        }
        this.name = name;
    }

    public PlayerDto exportPlayerDto() {
        return new PlayerDto(name);
    }
}
