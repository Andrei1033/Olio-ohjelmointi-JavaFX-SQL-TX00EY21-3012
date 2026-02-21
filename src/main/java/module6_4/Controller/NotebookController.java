package module6_4.Controller;

import module6_4.Model.Note;
import module6_4.Model.Notebook;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;

public class NotebookController {
    private Notebook notebook = new Notebook();

    @FXML
    private TextField titleField;
    @FXML
    private TextArea contentArea;
    @FXML
    private void handleAddNote() {
        String title = titleField.getText();
        String content = contentArea.getText();

        if (title.isEmpty() || content.isEmpty()) {
            return; // ei lisätä tyhjää muistiinpanoa
        }

        Note newNote = new Note(title, content);
        notebook.addNote(newNote);

        noteListView.getItems().clear();

        System.out.println("Notes in notebook:");

        for (Note note : notebook.getNotes()) {
            noteListView.getItems().add(note.getTitle());
        }

        // tyhjennetään kentät
        titleField.clear();
        contentArea.clear();
    }

    @FXML
    private ListView<String> noteListView;

    @FXML
    private void initialize() {
        noteListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                for(Note note : notebook.getNotes()) {
                    if(note.getTitle().equals(newValue)) {
                        displayArea.setText(note.getContent());
                        break;
                    }
                }
            }
        });
    }

    @FXML
    private TextArea displayArea;
}
