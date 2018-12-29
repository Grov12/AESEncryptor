package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.misc.BASE64Decoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Label listView;
    @FXML
    private Label mainLabel;
    @FXML
    private ImageView imageview1;
    @FXML
    private ImageView imageView2;
    @FXML
    private TextField keyValue;
    @FXML
    private TextField ivValue;
    @FXML
    private Label headLabel;

    @FXML private Button decryptButton;


    String fileName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("C:\\Users\\robin\\Desktop\\AESEncryptor\\hola.png");

        File file2 = new File("C:\\Users\\robin\\Desktop\\AESEncryptor\\src\\sample\\lk.jpg");
        Image image = new Image(file.toURI().toString());
        Image image2 = new Image(file2.toURI().toString());
        imageview1.setImage(image);
        imageView2.setImage(image2);








    }


    @FXML
    public void handleFileChooser() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            listView.setText(selectedFile.getAbsolutePath());
            fileName = selectedFile.getAbsolutePath();
        } else {
            System.out.println("Not valid");

        }

    }


    @FXML
    public void encryptFileAES(int cipherMode, String initVector, String key, File input, File outPut) {

        try {


            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));

            byte[] decodedKey = Base64.getDecoder().decode(key);
            System.out.println(decodedKey.length);
            SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");


            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(cipherMode, originalKey, iv);


            FileInputStream inputStream = new FileInputStream(input);
            byte[] inputBytes = new byte[(int) input.length()];
            inputStream.read(inputBytes);
            String encoded = Base64.getEncoder().encodeToString(inputBytes);
            byte[] outPutBytes = cipher.doFinal(new BASE64Decoder().decodeBuffer(encoded));


            FileOutputStream fileOutputStream = new FileOutputStream(outPut);
            fileOutputStream.write(outPutBytes);

            inputStream.close();
            fileOutputStream.close();





        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Invalid key");
            dialog.setContentText("File NOT Decrypted!");
            dialog.show();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }


    }


    @FXML
    public void encryptionButton() {

        if(ivValue.getText().getBytes().length == 16 && keyValue.getText().getBytes().length == 24) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Successful");
            dialog.setContentText("File encrypted!");
            dialog.show();
            String userHomeFolder = System.getProperty("user.home");
            File file = new File(fileName);
            encryptFileAES(Cipher.ENCRYPT_MODE, ivValue.getText(), keyValue.getText(), file, file);


        }

        else {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Error");
            dialog.setContentText("Invalid key");
            dialog.show();
        }



    }
    @FXML
    public void decryptButton() {

            File file = new File(fileName);
            encryptFileAES(Cipher.DECRYPT_MODE, ivValue.getText(), keyValue.getText(), file, file);
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Successful");
            dialog.setContentText("File Decrypted!");
            dialog.show();


    }
    @FXML
    public void switchScene(ActionEvent e) throws Exception {

        Node node = (Node)  e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("generator.fxml"));
        Parent parent = loader.load();


        Scene scene = new Scene(parent);
        stage.setScene(scene);


        scene.getStylesheets();



    }

}
