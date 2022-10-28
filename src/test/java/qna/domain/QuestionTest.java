package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void 작성자_삭제_요청자_다른_경우_오류() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 질문_삭제_검증() throws CannotDeleteException {
        List<DeleteHistory> result = Q1.delete(UserTest.JAVAJIGI);

        List<DeleteHistory> expected = List.of(getSampleDeleteHistory());

        assertThat(result).isEqualTo(expected);
    }

    private DeleteHistory getSampleDeleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, null, UserTest.JAVAJIGI, null);
    }
}
