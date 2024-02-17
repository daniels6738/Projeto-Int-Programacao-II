package app;

import exceptions.LoginInvalidoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Estudante;
import negocio.Controlador;

import java.io.IOException;
import java.util.Objects;

public class TelaLoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField CPFTextField;

    @FXML
    private Label LabelAvisoErroLogin;

    @FXML
    private PasswordField senhaPasswordField;

    /*public void trocaPraAluno(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TelaAluno.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/

    /*public void trocaPraFuncionario(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TelaFuncionario.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root); //copiar esse código dentro do evento pra trocar tela, só mudar o .fxml
        stage.setScene(scene);
        stage.show();
    }*/

    @FXML
    protected void initialize(){
        if(Controlador.getInstance().getUsuario() != null){
        CPFTextField.setText(Controlador.getInstance().getUsuario().getCpf());
        senhaPasswordField.setText(Controlador.getInstance().getUsuario().getSenha());
        }
    }

    @FXML
    protected void botaoLoginApertar (ActionEvent event) throws IOException {

        try{

            Controlador.getInstance().setUsuario(Controlador.getInstance().validLogin(CPFTextField.getText(), senhaPasswordField.getText()));

            if(Controlador.getInstance().validLogin(CPFTextField.getText(), senhaPasswordField.getText()) instanceof Estudante) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TelaAluno.fxml")));
            } else {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TelaFuncionario.fxml")));
            }

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Tela inicial");

        }catch (LoginInvalidoException e){

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Login inválido!");
            alerta.show();
            alerta.setContentText(e.errorMessage());

        }

    }

}