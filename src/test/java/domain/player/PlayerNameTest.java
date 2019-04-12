package domain.player;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerNameTest {

    @Test(expected = IllegalArgumentException.class)
    public void constructor_for_null() {
        new PlayerName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_for_empty() {
        new PlayerName("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_for_long_name() {
        new PlayerName("ABCD");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_for_short_name() {
        new PlayerName("AB");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_for_none_alphabetical_name() {
        new PlayerName("ㄱㄴㄷ");
    }

    @Test
    public void constructor() {
        String expected = "abc";
        PlayerName playerName = new PlayerName(expected);

        assertThat(playerName.getName()).isEqualTo(expected);
    }

}