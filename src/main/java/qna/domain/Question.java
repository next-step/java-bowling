package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question extends AbstractEntity {

    @Embedded
    private QuestionBody questionBody = new QuestionBody();

    @Embedded
    private final Answers answers = new Answers();

    @Embedded
    private final ArticleInfo articleInfo = new ArticleInfo();

    public Question() {
    }

    public Question(QuestionBody questionBody) {
        this.questionBody = questionBody;
    }

    public Question(long id, QuestionBody questionBody) {
        super(id);
        this.questionBody = questionBody;
    }

    public Question writeBy(User loginUser) {
        articleInfo.writeBy(loginUser);
        return this;
    }

    public void addAnswer(Answer answer) {
        answers.addAnswer(answer, this);
    }

    public boolean isOwner(User loginUser) {
        return articleInfo.isOwner(loginUser);
    }

    public boolean isDeleted() {
        return articleInfo.isDeleted();
    }

    private void validateQuestionOwner(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        validateQuestionOwner(loginUser);

        articleInfo.delete();

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(
                ContentType.QUESTION, getId(), articleInfo.getWriter(), LocalDateTime.now()));
        deleteHistories.addAll(answers.delete(loginUser));

        return deleteHistories;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionBody=" + questionBody +
                ", answers=" + answers +
                ", articleInfo=" + articleInfo +
                '}';
    }
}
