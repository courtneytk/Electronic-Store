import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ElectronicStoreView extends Pane{

    //Instance Variables
    private ListView<String> stockList;
    private ListView<String> cartList;
    private ListView<String> popularList;
    private StoreButtonPane buttonPane;
    private TextField salesField, revenueField, perSaleField;
    private Label label1,label2,label3,label4,label5,label6,label7;
    private ElectronicStore modelView;
    private Product[] stockView;
    private Product[] popular;
    private String[] inStock;
    private String[] inPopular;

    //Get/set methods for controller to access easily
    public ListView<String> getStockList() { return stockList; }
    public ListView<String> getCartList() { return cartList; }
    public ListView<String> getPopularList() { return popularList; }
    public StoreButtonPane getButtonPane() { return buttonPane; }

    //Constructor
    public ElectronicStoreView(ElectronicStore initModel){

        modelView = initModel;

        //Creating labels to highlight the purpose of text-boxes, and listviews in view
        label1 = new Label("Store Summary:");
        label1.relocate(45,20);

        label2 = new Label("# Sales:");
        label2.relocate(35,42);

        label3 = new Label("Revenue:");
        label3.relocate(27,73);

        label4 = new Label("$ / Sale:");
        label4.relocate(33,103);

        label5 = new Label("Most Popular Items:");
        label5.relocate(33,133);

        label6 = new Label("Store Stock:");
        label6.relocate(280,20);

        label7 = new Label("Current Cart ($" + String.format("%.2f",modelView.getTotalAmount()) + "):");
        label7.relocate(580,20);

        //Create the lists
        stockList = new ListView<>();
        stockList.relocate(170,42);
        stockList.setPrefSize(300,273);

        cartList = new ListView<>();
        cartList.relocate(480,42);
        cartList.setPrefSize(300,273);

        popularList = new ListView<>();
        popularList.relocate(10,155);
        popularList.setPrefSize(150,160);

        //Create the text fields
        salesField = new TextField();
        salesField.relocate(80,42);
        salesField.setPrefSize(80,15);

        revenueField = new TextField();
        revenueField.relocate(80,72);
        revenueField.setPrefSize(80,15);

        perSaleField = new TextField();
        perSaleField.relocate(80,102);
        perSaleField.setPrefSize(80,15);

        //Create the button pane
        buttonPane = new StoreButtonPane();
        buttonPane.relocate(10,330);
        buttonPane.setPrefSize(340, 70);

        //Add all the components to the main pane
        getChildren().addAll(label1,label2,label3,label4,label5,label6,label7,stockList,cartList,popularList,salesField,revenueField,perSaleField,buttonPane);

        //Setting the size of the pane
        setPrefSize(800,400);

    }

    //Method to update the view
    public void update(ElectronicStore newModel) {

        //Sending in a new modelView when refreshing/restarting the view
        modelView = newModel;

        //Instantiating the variables to be used within the view.update method
        stockView = modelView.getStock();
        popular = modelView.getMostPopular();
        inStock = new String[modelView.getProductCount()];
        inPopular = new String[modelView.getPopularCount()];

        //Setting up the values found within the store summary text fields
        salesField.setText(modelView.getNumSales() + "");

        revenueField.setText(String.format("%.2f",modelView.getRevenue()) + "");

        if(modelView.getAvSale() != -1){
            perSaleField.setText(String.format("%.2f",modelView.getAvSale()) + "");
        }
        else{
            perSaleField.setText("N/A");
        }

        //Set text to update label that outputs the running total of items in the cart
        label7.setText("Current Cart ($" + String.format("%.2f",modelView.getTotalAmount()) + "):");

        //Adding popular items to listview
        for (int i = 0; i<modelView.getPopularCount(); i++) {

            inPopular[i] = popular[i].toString();
        }
        popularList.setItems(FXCollections.observableArrayList(inPopular));


        //Adding stock values to listview
        for (int i = 0; i < modelView.getProductCount(); i++) {
            inStock[i] = stockView[i].toString();
        }
        stockList.setItems(FXCollections.observableArrayList(inStock));

        //Adding cart items to listview - Note list is populated with an array returned by a function in the modelView
        cartList.setItems(FXCollections.observableArrayList(modelView.CartString()));

        //Enable/disable add button based on whether or not user clicks an item in store stock
        int selected = stockList.getSelectionModel().getSelectedIndex();

        if (selected == -1) { //If user didn't click an item in stock list
            getButtonPane().getAddButton().setDisable(true);
        } else {
            getButtonPane().getAddButton().setDisable(false);
        }

        //Enable/disable remove button when item in cart is selected or not
        int selected2 = cartList.getSelectionModel().getSelectedIndex();

        if(selected2 == -1) {
            getButtonPane().getRemoveButton().setDisable(true);
        }
        else{
            getButtonPane().getRemoveButton().setDisable(false);
        }

        //Enable/disable completed button if items are in the cart or not
        if(modelView.getCartCount() > 0){
            getButtonPane().getCompleteButton().setDisable(false);
        }
        else{
            getButtonPane().getCompleteButton().setDisable(true);
        }
    }

}
