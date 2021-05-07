import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ElectronicStoreApp extends Application{

    //Instance variables
    private ElectronicStore modelApp;
    private ElectronicStoreView view;

    //Constructor
    public ElectronicStoreApp(){
         modelApp = ElectronicStore.createStore();
         view = new ElectronicStoreView(modelApp);
    }

    //Start the app and set the stage with all needed elements
    public void start(Stage primaryStage){
        Pane aPane = new Pane();

        //Create the main view
        aPane.getChildren().add(view);

        //Updates the view each time user clicks an item in the stock list (ie. so buttons are enabled/disabled, etc.)
        view.getStockList().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update(modelApp);
            }
        });

        //Updates the view each time user clicks an item in the cart list (ie. so buttons are enabled/disabled, etc.)
        view.getCartList().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update(modelApp);
            }
        });

        //On add button press, calls a handler to make needed changes in modelApp and view
        view.getButtonPane().getAddButton().setOnMouseReleased(new EventHandler<MouseEvent>() { //Try setOnMouseReleased
            public void handle(MouseEvent mouseEvent) {
                handleAdd();
            }
        });

        //On remove button press, calls a handler to make needed changes in modelApp and view
       view.getButtonPane().getRemoveButton().setOnMouseReleased(new EventHandler<MouseEvent>() { //Try setOnMouseReleased
            public void handle(MouseEvent mouseEvent) {
                handleRemove();
            }
        });

       //On reset button press, calls a handler to make needed changes in modelApp and view
       view.getButtonPane().getResetButton().setOnMouseReleased(new EventHandler<MouseEvent>() {
           public void handle(MouseEvent mouseEvent) {
               handleReset();
           }
       });

        //On complete button press, calls a handler to make needed changes in modelApp and view
       view.getButtonPane().getCompleteButton().setOnMouseReleased(new EventHandler<MouseEvent>() {
           public void handle(MouseEvent mouseEvent) {
               handleComplete();
           }
       });

        //Setting up the stage for the view to fit all together once outputs to user
        primaryStage.setTitle(modelApp.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();
        view.update(modelApp);
    }

    //Launches the application
    public static void main(String[] args) {
        launch(args);
    }

    //Handles calling the appropriate methods in the modelApp and view to update data/view when the add button is pressed
    public void handleAdd(){
        int stockSelection = view.getStockList().getSelectionModel().getSelectedIndex();

        if(stockSelection >= 0){
            modelApp.addCart(stockSelection);
            view.update(modelApp);
        }
    }

    //Handles calling the appropriate methods in the modelApp and view to update data/view when the remove button is pressed
    public void handleRemove(){
        int cartSelection = view.getCartList().getSelectionModel().getSelectedIndex();

        if(cartSelection >= 0){
            modelApp.removeCart(cartSelection);
            view.update(modelApp);
        }
    }

    //Handles calling the appropriate methods in the modelApp and view to reset data/view when the reset button is pressed
    public void handleReset(){
        modelApp =  ElectronicStore.createStore();
        view.update(modelApp);
    }

    //Handles calling the appropriate methods in the modelApp and view to update data/view when the complete button is pressed
    public void handleComplete(){
        modelApp.completeSale();
        view.update(modelApp);
    }
}
