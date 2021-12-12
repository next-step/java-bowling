package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question(2L, "title2", "contents2").writeBy(UserTest.SANJIGI);

    @BeforeClass
    public static void setUp() throws Exception {
        Q1.addAnswer(AnswerTest.A1);
        Q2.addAnswer(AnswerTest.A2);
    }

    @Test
    public void delete_성공시_DeleteHistory를_정상적으로_반환하는지_검증() throws CannotDeleteException {
        List<DeleteHistory> expected = Arrays.asList(new DeleteHistory(ContentType.QUESTION, Q1.getId(), UserTest.JAVAJIGI),
                                                     new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(),
                                                                       UserTest.JAVAJIGI));

        List<DeleteHistory> deleteHistories = Q1.delete(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(deleteHistories).isEqualTo(expected);
    }

    @Test
    public void 삭제시_질문_작성자가아니면_예외를_던진다() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void 삭제시_답변_작성자가_아니면_예외를_던진다() {
        assertThatThrownBy(() -> Q2.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
