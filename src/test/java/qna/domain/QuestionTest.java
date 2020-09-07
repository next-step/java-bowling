package qna.domain;

import org.junit.jupiter.api.Test;
import qna.global.exception.CannotDeleteException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private List<DeleteHistory> result;

    @Test
    void delete_질문_삭제_상태_확인() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI, Arrays.asList(AnswerTest.A1));
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void delete_DeleteHistory_값이_추가되었는지_확인() throws CannotDeleteException {
        result = Q1.delete(UserTest.JAVAJIGI, Arrays.asList());
        assertThat(result).hasSize(1);
    }

    @Test
    void delete_질문에_달려있는_댓글들_포함하여_삭제() throws CannotDeleteException {
        result = Q1.delete(UserTest.JAVAJIGI, Arrays.asList(AnswerTest.A1));
        assertThat(result.size()).isEqualTo(2);
    }

}
