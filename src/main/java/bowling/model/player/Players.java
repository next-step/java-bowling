package bowling.model.player;

import bowling.model.Name;
import bowling.model.frame.Frames;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private static final String ADD_PLAYER_MESSAGE = "동일한 이름은 추가할 수 없습니다.";
    private static final String PLAYER_FINISH_MESSAGE = "플레이어의 상태를 알 수 없습니다.";

    private int nowPlayerIndex = 0;
    private final List<Player> players = new LinkedList<>();

    public void addNewPlayer(String userName){
        userDuplicateCheck(userName);

        Name name = Name.from(userName);
        Player newPlayer = Player.of(name, new Frames());
        players.add(newPlayer);
    }

    private void userDuplicateCheck(String name){
        if(contains(name)){
            throw new IllegalArgumentException(ADD_PLAYER_MESSAGE);
        }
    }

    private boolean contains(String userName){
        String lowerUserName = userName.toLowerCase();

        return players.stream()
                .map(player -> player.toString().toLowerCase())
                .anyMatch(player -> player.equals(lowerUserName));
    }

    public void bowling(int fallenPins){
        nowPlayer().bowling(fallenPins);

        if(nowPlayer().isTurnOver()){
            nowPlayerIndex = rollingIndex();
        }
    }

    public boolean isGameEnd(){
        return players.stream()
                .map(Player::isEnd)
                .reduce((x,y) -> x && y)
                .orElseThrow(() -> new IllegalArgumentException(PLAYER_FINISH_MESSAGE));
    }

    public String nowPlayerName(){
        return nowPlayer().toString();
    }

    private Player nowPlayer(){
        return players.get(nowPlayerIndex);
    }

    private int rollingIndex(){
        return (players.size() - 1 <= nowPlayerIndex) ? 0 : nowPlayerIndex + 1;
    }

    public List<PlayerResult> info(){
        return players.stream()
                .map(player -> PlayerResult.of(player.toString(), player.info()))
                .collect(Collectors.toList());
    }
}
