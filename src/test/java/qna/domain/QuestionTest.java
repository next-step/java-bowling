package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(SANJIGI);

    Question question;

    @BeforeEach
    void setUp() {
        question = new Question("title1", "contents1").writeBy(JAVAJIGI);
    }

    @Test
    void 소유자_확인() {
        assertThat(question.isOwner(JAVAJIGI)).isTrue();
    }

    @Test
    void 작성자가_본인이면_삭제_가능() throws CannotDeleteException {
        assertThat(question.isDeleted()).isFalse();
        question.delete(JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    void 작성자가_본인이_아니면_삭제_불가능() {
        assertThatThrownBy(() -> question.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 삭제시_삭제이력을_남김() throws CannotDeleteException {
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, question.getId(), JAVAJIGI, LocalDateTime.now());
        Assertions.assertThat(question.delete(JAVAJIGI)).contains(deleteHistory);
    }

}
