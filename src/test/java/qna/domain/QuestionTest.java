package qna.domain;

import org.junit.jupiter.api.Test;
import qna.global.exception.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void delete_확인() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void delete_DeleteHistory_값이_추가되었는지_확인() throws CannotDeleteException {
        List<DeleteHistory> result = Q1.delete(UserTest.JAVAJIGI);
        assertThat(result).hasSize(1);
    }

}
