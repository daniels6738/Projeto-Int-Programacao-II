package app;

import exceptions.NaoPossuiTicketDisponivelException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Estudante;
import models.Funcionario;
import models.TipoRefeicao;
import negocio.Controlador;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class TelaConsumoController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label labelQtdAlmoco;

    @FXML
    private Label labelQtdJanta;


    Random random = new Random();

    @FXML
    protected void initialize(){
        labelQtdAlmoco.setText(String.valueOf(Controlador.getInstance().listarTicketAlmocoNaoConsumido(Controlador.getInstance().getUsuario()).size()));
        labelQtdJanta.setText(String.valueOf(Controlador.getInstance().listarTicketJantarNaoConsumido(Controlador.getInstance().getUsuario()).size()));
    }

    @FXML
    protected void botaoConsumirAlmoco() {
        try {
            String text=Controlador.getInstance().consumirRefeicao(Controlador.getInstance().getUsuario(),TipoRefeicao.ALMOCO);
            labelQtdAlmoco.setText(String.valueOf(Controlador.getInstance().listarTicketAlmocoNaoConsumido(Controlador.getInstance().getUsuario()).size()));
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Ticket");
            info.setContentText("O código do seu ticket é "+text);
            info.showAndWait();
        } catch (NaoPossuiTicketDisponivelException e) {
            Alert info = new Alert(Alert.AlertType.WARNING);
            info.setTitle("Aviso");
            info.setContentText(e.getMessage());
            info.show();
        }
    }


    @FXML
    protected void botaoConsumirJantar() {
        try {
            String text=Controlador.getInstance().consumirRefeicao(Controlador.getInstance().getUsuario(),TipoRefeicao.JANTAR);
            labelQtdJanta.setText(String.valueOf(Controlador.getInstance().listarTicketJantarNaoConsumido(Controlador.getInstance().getUsuario()).size()));
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Ticket");
            info.setContentText("O código do seu ticket é "+text);
            info.showAndWait();
        } catch (NaoPossuiTicketDisponivelException e) {
            Alert info = new Alert(Alert.AlertType.WARNING);
            info.setTitle("Aviso");
            info.setContentText(e.getMessage());
            info.show();
        }
    }

    @FXML
    protected void botaoVoltar(ActionEvent event) throws IOException {
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
}
