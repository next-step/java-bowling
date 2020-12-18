package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoryTest {

    @Test
    public void createInstanceTest() {
        //Given & When
        DeleteHistory history = new DeleteHistory(ContentType.ANSWER, 1L, UserTest.JAVAJIGI);

        //Then
        assertThat(history).isEqualTo(new DeleteHistory(ContentType.ANSWER, 1L, UserTest.JAVAJIGI));

    }
}
