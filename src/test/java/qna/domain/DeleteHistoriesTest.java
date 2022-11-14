package qna.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoriesTest {
    @Test
    void 생성() {
        assertThat(DeleteHistories.of(DeleteHistory.ofQuestion(0L, UserTest.JAVAJIGI)).getHistories())
                .isInstanceOf(List.class);
    }

    @Test
    void 생성2() {
        DeleteHistories histories1 = DeleteHistories.of(DeleteHistory.ofQuestion(0L, UserTest.JAVAJIGI));
        DeleteHistories histories2 = DeleteHistories.of(DeleteHistory.ofAnswer(1L, UserTest.JAVAJIGI));
        histories1.join(histories2);
        assertThat(histories1.getHistories()).contains(DeleteHistory.ofAnswer(1L, UserTest.JAVAJIGI));
    }
}
