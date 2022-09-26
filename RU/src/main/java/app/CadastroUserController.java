package app;

import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoExisteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Estudante;
import models.Funcionario;
import models.Usuario;
import negocio.Controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class CadastroUserController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField codAluno;

    @FXML
    private TextField codFun;

    @FXML
    private TextField cpfAluno;

    @FXML
    private TextField cpfFun;

    @FXML
    private TextField cpfSearchE;

    @FXML
    private DatePicker dataContrato;

    @FXML
    private Label displayCod;

    @FXML
    private Label displayCodFun;

    @FXML
    private Label displayCpf;

    @FXML
    private Label displayCpfFun;

    @FXML
    private Label displayEmail;

    @FXML
    private Label displayEmailFun;

    @FXML
    private Label displayNasc;

    @FXML
    private Label displayNascFun;

    @FXML
    private Label displayNome;

    @FXML
    private Label displayNomeFun;

    @FXML
    private Label displaySalario;

    @FXML
    private TextField emailAluno;

    @FXML
    private TextField cpfSearchF;

    @FXML
    private TextField emailFun;

    @FXML
    private TextField matriculaAluno;

    @FXML
    private DatePicker nascAluno;

    @FXML
    private DatePicker nascFun;

    @FXML
    private TextField nomeAluno;

    @FXML
    private TextField nomeFun;

    @FXML
    private TextField salario;

    @FXML
    private TextField senhaAluno;

    @FXML
    private TextField senhaFun;


    @FXML
    protected void cadastrarEstudante (){
        if(!(senhaAluno.getText().equals("")) && !(nomeAluno.getText().equals("")) &&  // se não há algum
                !(emailAluno.getText().equals("")) && !(cpfAluno.getText().equals("")) &&  // campo em branco
                !(matriculaAluno.getText().equals("")) && !(codAluno.getText().equals("")) &&
                !(nascAluno.getValue().equals(LocalDate.now()))){
            try {
                Controlador.getInstance().inserirEstudante(new Estudante(codAluno.getText(),
                        nomeAluno.getText(), cpfAluno.getText(), nascAluno.getValue(),
                        emailAluno.getText(), senhaAluno.getText(), matriculaAluno.getText()));
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("CADASTRO REALIZADO!");
                success.setContentText("Estudante cadastro com sucesso!");
                success.show();

                nomeAluno.setText("");
                cpfAluno.setText("");
                nascAluno.setValue(null);
                matriculaAluno.setText("");
                codAluno.setText("");
                emailAluno.setText("");
                senhaAluno.setText("");

            } catch (ElementoJaExisteException e) {
                Alert repeated = new Alert(Alert.AlertType.WARNING);
                repeated.setTitle("CADASTRO INVÁLIDO!");
                repeated.setContentText(e.getMessage());
                repeated.show();
            }
        } else {
            Alert fail = new Alert(Alert.AlertType.WARNING);
            fail.setTitle("CADASTRO INVÁLIDO!");
            fail.setContentText("Verifique as informações preenchidas!");
            fail.show();
        }
    }

    @FXML
    protected void cadastrarFuncionario (){

        if(!(codFun.getText().equals("")) && !(nomeFun.getText().equals("")) &&  // se não há algum
                !(emailFun.getText().equals("")) && !(cpfFun.getText().equals("")) &&  // campo em branco
                !(salario.getText().equals("")) &&
                !(nascFun.getValue().equals(LocalDate.now().minusYears(16)))){
            try {
                Controlador.getInstance().inserirFuncionario(new Funcionario(codFun.getText(),
                        nomeFun.getText(), cpfFun.getText(), nascFun.getValue(),
                        emailFun.getText(), senhaFun.getText(), Double.parseDouble(salario.getText()),
                        dataContrato.getValue()));

                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("CADASTRO REALIZADO!");
                success.setContentText("Funcionário cadastro com sucesso!");
                success.show();

                nomeFun.setText("");
                cpfFun.setText("");
                salario.setText("");
                codFun.setText("");
                emailFun.setText("");
                senhaFun.setText("");
                nascFun.setValue(null);
                dataContrato.setValue(null);

            } catch (ElementoJaExisteException e) {
                Alert repeated = new Alert(Alert.AlertType.WARNING);
                repeated.setTitle("CADASTRO INVÁLIDO!");
                repeated.setContentText(e.getMessage());
                repeated.show();
            }
        } else {
            Alert fail = new Alert(Alert.AlertType.WARNING);
            fail.setTitle("CADASTRO INVÁLIDO!");
            fail.setContentText("Verifique as informações preenchidas!");
            fail.show();
        }

    }

    @FXML
    protected void homeButton(ActionEvent event) throws IOException {
        if (Controlador.getInstance().getUsuario() instanceof Estudante) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TelaAluno.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Tela Inicial");
        } else if (Controlador.getInstance().getUsuario() instanceof Funcionario) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TelaFuncionario.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Tela inicial");
        }
    }

    @FXML
    protected void searchButton () {
        for (Estudante f: Controlador.getInstance().listarEstudantes()) {
            if (f.getCpf().equals(cpfSearchE.getText())) {
                displayNome.setText(f.getNome());
                displayCpf.setText(f.getCpf());
                displayCod.setText(f.getCodigo());
                displayNasc.setText(f.getDataDeNascimento().toString());
                displayEmail.setText(f.getEmail());
            }
        }
    }

    @FXML
    protected void searchButtonF (){
        for (Funcionario f: Controlador.getInstance().listarFuncionarios()) {
            if (f.getCpf().equals(cpfSearchF.getText())) {
                displayNomeFun.setText(f.getNome());
                displayCpfFun.setText(f.getCpf());
                displayCodFun.setText(f.getCodigo());
                displayNascFun.setText(f.getDataDeNascimento().toString());
                displayEmailFun.setText(f.getEmail());
                displaySalario.setText(String.valueOf(f.getSalario()));
            }
        }
    }

    @FXML
    protected void removeButton() {
        for (Estudante e: Controlador.getInstance().listarEstudantes()) {
            if (e.getCpf().equals(cpfSearchE.getText())) {
                try{
                    Controlador.getInstance().removerEstudante(e);
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Estudante removido!");
                    success.setContentText("Estudante removido com sucesso!");
                    success.show();
                    displayNome.setText("");
                    displayCpf.setText("");
                    displayCod.setText("");
                    displayNasc.setText("");
                    displayEmail.setText("");
                }catch (ElementoNaoExisteException ignored){
                    Alert fail = new Alert(Alert.AlertType.WARNING);
                    fail.setTitle("ERRO");
                    fail.setContentText("Estudante não pôde ser removido!");
                    fail.show();
                }
            }
        }
    }

    @FXML
    protected void removeButtonF() {
        for (Funcionario f: Controlador.getInstance().listarFuncionarios()) {
            if (f.getCpf().equals(cpfSearchF.getText()) && !cpfSearchF.getText().equals(Controlador.getInstance().getUsuario().getCpf())) {
                try{
                    Controlador.getInstance().removerFuncionario(f);
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Funcionário removido!");
                    success.setContentText("Funcionário removido com sucesso!");
                    success.show();
                    displayNome.setText("");
                    displayCpf.setText("");
                    displayCod.setText("");
                    displayNasc.setText("");
                    displayEmail.setText("");
                }catch (ElementoNaoExisteException ignored){
                    Alert fail = new Alert(Alert.AlertType.WARNING);
                    fail.setTitle("ERRO");
                    fail.setContentText("Funcionário não pôde ser removido!");
                    fail.show();
                }
            }
            else if(cpfSearchF.getText().equals(Controlador.getInstance().getUsuario().getCpf())){
                Alert fail = new Alert(Alert.AlertType.WARNING);
                fail.setTitle("ERRO");
                fail.setContentText("Um funcionário não pode ser removido por ele mesmo!");
                fail.show();
            }
        }
    }

    @FXML
    protected void editButton() {

    }

}
