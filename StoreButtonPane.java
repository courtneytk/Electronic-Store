import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class StoreButtonPane extends Pane{

    //Instance variables
    private Button resetButton, addButton, removeButton, completeButton;

    //Get/set methods to assist the controller (and the main view) in accessing information from the view
    public Button getResetButton() { return resetButton; }
    public Button getAddButton() { return addButton; }
    public Button getRemoveButton() { return removeButton; }
    public Button getCompleteButton() { return completeButton; }

    public StoreButtonPane(){
        Pane innerPane = new Pane();

        //Creating the buttons
        resetButton = new Button("Reset Store");
        resetButton.setStyle("-fx-font: 12 arial;");
        resetButton.relocate(5,0);
        resetButton.setPrefSize(140,48);

        addButton = new Button("Add to Cart");
        addButton.setStyle("-fx-font: 12 arial;");
        addButton.relocate(240,0);
        addButton.setPrefSize(140,50);

        removeButton = new Button("Remove from Cart");
        removeButton.setStyle("-fx-font: 12 arial;");
        removeButton.relocate(470,0);
        removeButton.setPrefSize(140,50);

        completeButton = new Button("Complete Sale");
        completeButton.setStyle("-fx-font: 12 arial;");
        completeButton.relocate(630,0);
        completeButton.setPrefSize(140,50);

        //Adding all the buttons to the pane
        innerPane.getChildren().addAll(resetButton,addButton,removeButton,completeButton);

        getChildren().addAll(innerPane);


    }
}
