package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;
import qna.NotFoundException;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
public class Question extends AbstractEntity {
    private boolean deleted;

    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

//    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
//    @Where(clause = "deleted = false")
//    @OrderBy("id ASC")
//    private Answers answers = new Answers();
//@Resource(name = "answerRepository")
//private AnswerRepository answerRepository;
//
//public findAnswerByQuestion(Question question) {
//    return answerRepository.findByQuestionAndDeletedFalse(question)
//            .orElseThrow(NotFoundException::new);
//}

    public Question() {
    }

    public Question(String title, String contents, User writer) {
        this.title = title;
        this.contents = contents;
        this.deleted = false;
        this.writer = writer;
    }

    public Question(long id, String title, String contents, User writer) {
        super(id);
        this.title = title;
        this.contents = contents;
        this.deleted = false;
        this.writer = writer;
    }

    private Answers findAnswers() {
        Answers answers = new Answers();
        if (answers.isEmpty()) {
            return null;
        }
        return answers.getAnswers()
                .stream()
                .filter(answer -> answer.getQuestion() == this)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Answers::new));
    }

    public void deleteBy(User loginUser) throws CannotDeleteException {
        if (findAnswers().isEmpty() && loginUser != this.writer) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        if (findAnswers().isEmpty() && loginUser == this.writer) {
            this.deleted(true);
        }
        if (!findAnswers().haveSameWriter(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
           //상태
        this.deleted(true);
        this.findAnswers().deleted(true);
            DeleteHistories deleteHistories = new DeleteHistories();
//            deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId, question.getWriter(), LocalDateTime.now()));
//            deleteHistoryService.save(deleteHistory);
    }

    public User getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public boolean isDeleted() {
        return deleted;
    }


    public Question writeBy(User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public boolean deleted(boolean bool) {
        this.deleted = bool;
        return deleted;
    }

//    public void addAnswer(Answer answer) {
//        answer.toQuestion(this);
//        answers.add(answer);
//    }

    public boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Question question = (Question) o;
        return deleted == question.deleted &&
                Objects.equals(title, question.title) &&
                Objects.equals(contents, question.contents) &&
                Objects.equals(writer, question.writer);
           //   && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), deleted, title, contents, writer);
    //  return Objects.hash(super.hashCode(), deleted, title, contents, writer, answers);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

}
