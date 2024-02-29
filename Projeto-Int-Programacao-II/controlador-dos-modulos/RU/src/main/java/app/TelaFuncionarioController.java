package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import negocio.Controlador;
import negocio.UserAtual;

import java.io.IOException;
import java.util.Objects;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import org.json.JSONObject;

public class TelaFuncionarioController {
   
    private Stage stage;
    private Scene scene;
    private Parent root;




    @FXML
    private Label labelBemvindo;

    @FXML
    private Label labelQtdAlmoco;

    @FXML
    private Label labelQtdJantar;

    @FXML
    MenuItem cadastrarCardapio;

    @FXML
    MenuItem buscarCardapio;

    @FXML
    MenuButton menuBtn;

    @FXML
    protected void initialize() {
        try {
            String cpf = UserAtual.getInstance().getCpf();
            String tipoAlmoco = "almoço";
            String tipoJanta = "janta";
    
            // Cria um cliente HTTP
            HttpClient client = HttpClient.newHttpClient();
            
            // Requisição para almoço não consumido
            HttpRequest requestAlmoco = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:3330/ticket/listar/naoConsumidos"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{\"usuario\": \"" + cpf + "\", \"tipo\": \"" + tipoAlmoco + "\"}"))
                    .build();
            
            // Requisição para janta não consumida
            HttpRequest requestJanta = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:3330/ticket/listar/naoConsumidos"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{\"usuario\": \"" + cpf + "\", \"tipo\": \"" + tipoJanta + "\"}"))
                    .build();
    
            // Envia a requisição para a API e obtém a resposta para o almoço
            HttpResponse<String> responseAlmoco = client.send(requestAlmoco, HttpResponse.BodyHandlers.ofString());
            // Verifica se a resposta da API indica um login válido para o almoço
            if (responseAlmoco.statusCode() == 200) {
                // Converta a string do corpo da requisição em um objeto JSONObject para o almoço
                JSONObject jsonObjectAlmoco = new JSONObject(responseAlmoco.body());
                String totalAlmoco = jsonObjectAlmoco.getString("total");
    
                labelBemvindo.setText(labelBemvindo.getText() + UserAtual.getInstance().getNome());
                labelQtdAlmoco.setText(labelQtdAlmoco.getText() + totalAlmoco);
            }
    
            // Envia a requisição para a API e obtém a resposta para a janta
            HttpResponse<String> responseJanta = client.send(requestJanta, HttpResponse.BodyHandlers.ofString());
            // Verifica se a resposta da API indica um login válido para a janta
            if (responseJanta.statusCode() == 200) {
                // Converta a string do corpo da requisição em um objeto JSONObject para a janta
                JSONObject jsonObjectJanta = new JSONObject(responseJanta.body());
                String totalJanta = jsonObjectJanta.getString("total");
    
                labelQtdJantar.setText(labelQtdJantar.getText() + totalJanta);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void botaoComprarApertar(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TelaCompra.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Compra de tickets");
    }

    @FXML
    protected void botaoSair(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Login");
    }

    @FXML
    protected void botaoVerCardapioApertar(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TelaCardapio.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Cardápio Semanal");
    }


    @FXML
    protected void userCadastro(ActionEvent event) throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CadastroUsers.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Cadastrar Usuários");
    }
    
   /* @FXML
    protected void botaoCdCardapio(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TelaCDCardapio.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Cadastro do Cardapio");
    }*/

    @FXML
    protected void botaoRelatoriosApertar(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TelaRelatorio.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Relatório");
    }

    @FXML
    protected void botaoConsumirApertar(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TelaConsumo.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Consumo de Tickets");
    }

    @FXML
    protected void cadastrarCardapioApertar(ActionEvent event)throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TelaCDCardapio.fxml")));
        stage = (Stage)menuBtn.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Cadastro do Cardapio");
    }
    @FXML
    protected void buscarCardapioApertar(ActionEvent event)throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TelaBuscaCardapio.fxml")));
        stage = (Stage)menuBtn.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Buscar Cardápio");
    }

    @FXML
    protected void botaoHistoricoApertar(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TelaHistorico.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Histórico de Usuário");
    }
}
