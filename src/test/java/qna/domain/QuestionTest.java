package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("질문 테스트")
public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("삭제(Q1:작성자 JAVAJIGI)")
    @Test
    public void delete_Q1() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI, new ArrayList<>());
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI, new ArrayList<>()))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("삭제(Q2:작성자 SANJIGI)")
    @Test
    public void delete_Q2() throws CannotDeleteException {
        Q2.delete(UserTest.SANJIGI, new ArrayList<>());
        assertThatThrownBy(() -> Q2.delete(UserTest.JAVAJIGI, new ArrayList<>()))
                .isInstanceOf(CannotDeleteException.class);
    }

}
