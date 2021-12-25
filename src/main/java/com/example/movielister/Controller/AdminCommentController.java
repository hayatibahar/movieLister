package com.example.movielister.Controller;

import com.example.movielister.Data.Manager.CommentManager;
import com.example.movielister.Data.Repository.CommentRepository;
import com.example.movielister.Model.Comment;
import com.example.movielister.util.FXAlert;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AdminCommentController extends BaseController implements Initializable {

    private final CommentManager commentManager = new CommentManager(new CommentRepository());

    @FXML
    private TableColumn<Comment, String> comment;

    @FXML
    private TableColumn<Comment, Integer> commentID;

    @FXML
    private TableColumn<Comment, String> date;

    @FXML
    private TableColumn<Comment, Integer> dislikeCount;

    @FXML
    private TableColumn<Comment, Integer> likeCount;

    @FXML
    private TableColumn<Comment, Integer> movieID;

    @FXML
    private TableColumn<Comment, String> title;

    @FXML
    private TableColumn<Comment, Integer> userID;

    @FXML
    private TableView<Comment> table_comment;

    @FXML
    private TextField tf_comment;

    @FXML
    private TextField tf_commentID;

    @FXML
    private TextField tf_dislikeCount;

    @FXML
    private TextField tf_likeCount;

    @FXML
    private TextField tf_movieID;

    @FXML
    private TextField tf_title;

    @FXML
    private TextField tf_userID;

    @FXML
    private DatePicker dp_commentDate;

    private ObservableList<Comment> comments;


    @FXML
    void openAdminAccountScene(MouseEvent event) {
        openStage(event, "adminAccount-view.fxml");
    }

    @FXML
    void openAdminMovieScene(MouseEvent event) {
        openStage(event, "adminMovie-view.fxml");
    }

    @FXML
    void openAdminPageScene(MouseEvent event) {
        openStage(event, "admin-view.fxml");
    }

    @FXML
    void openHomePageScene(MouseEvent event) {
        openStage(event, "homePage-view.fxml");
    }

    @FXML
    void addComment(MouseEvent event) {
        Comment comment = textFieldToComment();
        if (comment != null) {
            commentManager.addComment(comment);
            comments = commentManager.getAllComment();
            table_comment.setItems(comments);
        }
    }

    @FXML
    void deleteComment(MouseEvent event) {
        Comment comment = textFieldToComment();
        if (comment != null) {
            commentManager.deleteComment(comment);
            comments = commentManager.getAllComment();
            table_comment.setItems(comments);
        }
    }

    @FXML
    void updateComment(MouseEvent event) {
        Comment comment = textFieldToComment();
        if (comment != null) {
            commentManager.updateComment(comment);
            comments = commentManager.getAllComment();
            table_comment.setItems(comments);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comments = commentManager.getAllComment();
        userID.setCellValueFactory(new PropertyValueFactory<Comment, Integer>("userID"));
        movieID.setCellValueFactory(new PropertyValueFactory<Comment, Integer>("movieID"));
        commentID.setCellValueFactory(new PropertyValueFactory<Comment, Integer>("commentID"));
        title.setCellValueFactory(new PropertyValueFactory<Comment, String>("title"));
        comment.setCellValueFactory(new PropertyValueFactory<Comment, String>("userComment"));
        date.setCellValueFactory(new PropertyValueFactory<Comment, String>("commentDate"));
        likeCount.setCellValueFactory(new PropertyValueFactory<Comment, Integer>("likeCount"));
        dislikeCount.setCellValueFactory(new PropertyValueFactory<Comment, Integer>("dislikeCount"));
        table_comment.setItems(comments);
        tf_commentID.setText("0");
        setTextFieldFromTable();
    }


    private void setTextFieldFromTable() {
        table_comment.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (table_comment.getSelectionModel().getSelectedIndex() > -1) {
                    Comment comment = table_comment.getItems().get(table_comment.getSelectionModel().getSelectedIndex());
                    tf_userID.setText(String.valueOf(comment.getUserID()));
                    tf_commentID.setText(String.valueOf(comment.getCommentID()));
                    tf_movieID.setText(String.valueOf(comment.getMovieID()));
                    tf_title.setText(comment.getTitle());
                    tf_comment.setText(comment.getUserComment());
                    dp_commentDate.setValue(Timestamp.valueOf(comment.getCommentDate()).toLocalDateTime().toLocalDate());
                    tf_likeCount.setText(String.valueOf(comment.getLikeCount()));
                    tf_dislikeCount.setText(String.valueOf(comment.getDislikeCount()));

                }
            }
        });
    }


    private Comment textFieldToComment() {
        Comment comment = null;
        if (!tf_title.getText().isEmpty() && !tf_comment.getText().isEmpty()) {
            try {
                comment = new Comment(Integer.parseInt(tf_commentID.getText()),
                        Integer.parseInt(tf_userID.getText()),
                        Integer.parseInt(tf_movieID.getText()),
                        tf_title.getText(),
                        tf_comment.getText(),
                        dp_commentDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")),
                        Integer.parseInt(tf_likeCount.getText()),
                        Integer.parseInt(tf_dislikeCount.getText())
                );
            } catch (Exception e) {
                FXAlert.showException(e, "Alanların doğru girildiğinden emin olun!");
            }
        } else {
            FXAlert.showWarning("Lütfen boş alan bırakmayınız!");
        }
        return comment;
    }

}
