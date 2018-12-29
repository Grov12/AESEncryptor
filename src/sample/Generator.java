package sample;

import com.sun.deploy.ui.ImageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.ResourceBundle;

public class Generator implements Initializable {
            @FXML private TextField keyLabel;
            @FXML private TextField ivLabel;
          @FXML private ImageView imageView;
             @FXML private ImageView imageView2;






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("C:\\Users\\robin\\Desktop\\AESEncryptor\\hola.png");
        File file2 = new File("C:\\Users\\robin\\Desktop\\AESEncryptor\\src\\sample\\lk.jpg");
        Image image = new Image(file.toURI().toString());
        Image image2 = new Image(file2.toURI().toString());
        imageView.setImage(image);
        imageView2.setImage(image2);
        }





    @FXML
    public void handleGenerator() throws Exception {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Reminder");
        dialog.setHeaderText("Security advice");
        dialog.setContentText("Never save your secret key on the computer. Write it down and keep it secure.");
        dialog.show();

        KeyGenerator gen = KeyGenerator.getInstance("AES");
        gen.init(128); /* 128-bit AES */
        SecretKey secret = gen.generateKey();
        String encodedKey = Base64.getEncoder().encodeToString(secret.getEncoded());
        System.out.println(encodedKey.getBytes().length);
        keyLabel.setText(encodedKey);


        SecureRandom sr = new SecureRandom(); //create new secure random
        byte[] iv = new byte[8];
        sr.nextBytes(iv);
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        byte[] m = ivspec.getIV();
        String x = new String(m, StandardCharsets.UTF_16LE);
        System.out.println(x);
       ivLabel.setText(String.format("%08X", new BigInteger(+1, iv)));





    }
    @FXML
    public void goBack(ActionEvent e) throws Exception {

        Node node = (Node)  e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent parent = loader.load();


        Scene scene = new Scene(parent);
        stage.setScene(scene);

        scene.getStylesheets();



    }


}
