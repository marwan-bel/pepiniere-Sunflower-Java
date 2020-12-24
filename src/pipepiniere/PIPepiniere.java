package pipepiniere;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import service.DaoUsers;

/**
 *
 * @author gazzah
 */
public class PIPepiniere extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       DaoUsers usr =new DaoUsers();
       Parent root;
       //if(usr.get_role().equals("client"))
        root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
      /* else  if(usr.get_role().equals("agent"))
          root = FXMLLoader.load(getClass().getResource("/fxml/Agent/Agent.fxml"));
       else  if(usr.get_role().equals("admin")) 
          root = FXMLLoader.load(getClass().getResource("/fxml/fxmlAdmin/Admin.fxml")); 
       else    root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml")); */
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.setMaximized(true);

        stage.getIcons().add(new Image("/Icons/sunflower.png"));
        stage.setTitle("SunFlower");
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

}
