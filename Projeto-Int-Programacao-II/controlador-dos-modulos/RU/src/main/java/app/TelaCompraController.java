package app;

import exceptions.ElementoJaExisteException;
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

public class TelaCompraController {

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
    protected void botaoComprarAlmoco() {
        try {
            //Controlador.getInstance().getRepositorioTicketRefeicao().inserir(new TicketRefeicao(LocalDate.now(),String.valueOf(random.nextInt(100000)),3.5,Controlador.getInstance().getUsuario(), TipoRefeicao.ALMOCO));
            Controlador.getInstance().comprarRefeicao(Controlador.getInstance().getUsuario(),TipoRefeicao.ALMOCO);
            labelQtdAlmoco.setText(String.valueOf(Controlador.getInstance().listarTicketAlmocoNaoConsumido(Controlador.getInstance().getUsuario()).size()));
        } catch (ElementoJaExisteException e) {
            Alert info = new Alert(Alert.AlertType.ERROR);
            info.setTitle("Erro");
            info.setContentText("Desculpe! Tente novamente!");
            info.show();
        }
    }

    @FXML
    protected void botaoComprarJanta() throws ElementoJaExisteException {
        //Controlador.getInstance().getRepositorioTicketRefeicao().inserir(new TicketRefeicao(LocalDate.now(),String.valueOf(random.nextInt(100000)),3.0,Controlador.getInstance().getUsuario(), TipoRefeicao.JANTAR));
        Controlador.getInstance().comprarRefeicao(Controlador.getInstance().getUsuario(),TipoRefeicao.JANTAR);
        labelQtdJanta.setText(String.valueOf(Controlador.getInstance().listarTicketJantarNaoConsumido(Controlador.getInstance().getUsuario()).size()));
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
