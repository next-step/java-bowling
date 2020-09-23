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
<<<<<<< HEAD
    private boolean deleted;
=======
    @Column(name = "title", length = 100, nullable = false)
>>>>>>> cb44143bd22b16010852420b4bf07972ed45c0c6
    private String title;

    @Column(name = "contents")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "writer", foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

<<<<<<< HEAD

//    private Answers answers = new Answers();
//@Resource(name = "answerRepository")
//private AnswerRepository answerRepository;
//
//public findAnswerByQuestion(Question question) {
//    return answerRepository.findByQuestionAndDeletedFalse(question)
//            .orElseThrow(NotFoundException::new);
//}
=======
    @Column(name = "answers")
    private Answers answers;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;
>>>>>>> cb44143bd22b16010852420b4bf07972ed45c0c6


    public Question(String title, String contents, User writer) {
        this.title = title;
        this.contents = contents;
<<<<<<< HEAD
        this.deleted = false;
        this.writer = writer;
======
>>>>>>> cb44143bd22b16010852420b4bf07972ed45c0c6
    }

    public Question(long id, String title, String contents, User writer) {
        super(id);
        this.title = title;
        this.contents = contents;
<<<<<<< HEAD
        this.deleted = false;
        this.writer = writer;
=======
        this.writer = writer;
        this.answers = answers;
        this.deleted = false;
>>>>>>> cb44143bd22b16010852420b4bf07972ed45c0c6
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

<<<<<<< HEAD
    public void deleteBy(User loginUser) throws CannotDeleteException {
        Answers answers = findAnswers();
       // if (findAnswers().isEmpty() && loginUser != this.writer) {
        if (loginUser != this.writer) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
//        if (findAnswers().isEmpty() && loginUser == this.writer) {
        if (loginUser == this.writer) {
            this.deleted(true);
        }
        if (!answers.haveSameWriter(loginUser)) {
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

=======
>>>>>>> cb44143bd22b16010852420b4bf07972ed45c0c6
    public String getContents() {
        return contents;
    }

<<<<<<< HEAD
    public boolean isDeleted() {
        return deleted;
=======
    public List<Answer> getAnswers() {
        if (answers != null) {
            return answers.getAnswers();
        }
        return null;
>>>>>>> cb44143bd22b16010852420b4bf07972ed45c0c6
    }


<<<<<<< HEAD
    public Question writeBy(User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public boolean deleted(boolean bool) {
        this.deleted = bool;
        return deleted;
=======
    public void addAnswer(Answer answer) {
        if (answers != null && answer != null) {
            answers.addAnswer(answer);
        }
>>>>>>> cb44143bd22b16010852420b4bf07972ed45c0c6
    }

//    public void addAnswer(Answer answer) {
//        answer.toQuestion(this);
//        answers.add(answer);
//    }

    public boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

<<<<<<< HEAD
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

=======
    public void delete() {
        isDeleted();
        //delete조건
        this.deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }

>>>>>>> cb44143bd22b16010852420b4bf07972ed45c0c6
    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

}
