package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void delete_불가_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> {
            Q1.checkQuestionDeletable(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
