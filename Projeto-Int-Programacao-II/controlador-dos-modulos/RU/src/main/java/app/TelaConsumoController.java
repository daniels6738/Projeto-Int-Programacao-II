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
import negocio.UserAtual;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.Random;

import org.json.JSONObject;

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
         try {
            HttpClient client = HttpClient.newHttpClient();
            String cpf = UserAtual.getInstance().getCpf();
    
            // Requisição para obter a quantidade de almoços não consumidos
            HttpRequest requestAlmoco = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:3333/ticket/naoConsumidos"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{\"usuario\": \"" + cpf + "\", \"tipo\": \"almoço\"}"))
                    .build();
    
            // Envia a requisição e obtém a resposta
            HttpResponse<String> responseAlmoco = client.send(requestAlmoco, HttpResponse.BodyHandlers.ofString());
    
            // Verifica se a resposta foi bem-sucedida e atualiza o label com a quantidade de almoços não consumidos
            if (responseAlmoco.statusCode() == 200) {
                JSONObject jsonObjectAlmoco = new JSONObject(responseAlmoco.body());
                int qtdAlmoco = jsonObjectAlmoco.getInt("total");
                labelQtdAlmoco.setText(labelQtdAlmoco.getText() + qtdAlmoco);
            } else {
                labelQtdAlmoco.setText("Erro ao obter quantidade de almoços não consumidos");
            }
    
            // Requisição para obter a quantidade de jantas não consumidas
            HttpRequest requestJanta = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:3333/ticket/naoConsumidos"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{\"usuario\": \"" + cpf + "\", \"tipo\": \"janta\"}"))
                    .build();
    
            // Envia a requisição e obtém a resposta
            HttpResponse<String> responseJanta = client.send(requestJanta, HttpResponse.BodyHandlers.ofString());
    
            // Verifica se a resposta foi bem-sucedida e atualiza o label com a quantidade de jantas não consumidas
            if (responseJanta.statusCode() == 200) {
                JSONObject jsonObjectJanta = new JSONObject(responseJanta.body());
                int qtdJanta = jsonObjectJanta.getInt("total");
                labelQtdJanta.setText(labelQtdJanta.getText() + qtdJanta);
            } else {
                labelQtdJanta.setText("Erro ao obter quantidade de jantas não consumidas");
            }
    
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void botaoConsumirAlmoco() {
        try {
            
            String cpf = UserAtual.getInstance().getCpf();
            String tipoAlmoco = "almoço";

            HttpClient client = HttpClient.newHttpClient();
            
            // Requisição para almoço não consumido
            HttpRequest requestAlmoco = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:3333/ticket/consumir"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{\"usuario\": \"" + cpf + "\", \"tipo\": \"" + tipoAlmoco + "\"}"))
                    .build();

             // Envia a requisição para a API e obtém a resposta para o almoço
             HttpResponse<String> responseAlmoco = client.send(requestAlmoco, HttpResponse.BodyHandlers.ofString());
             // Verifica se a resposta da API indica um login válido para o almoço
             if (responseAlmoco.statusCode() == 200) {
                labelQtdAlmoco.setText("");
                labelQtdJanta.setText("");
               initialize();
            }
            

        } catch (IOException | InterruptedException e) {
            Alert info = new Alert(Alert.AlertType.ERROR);
            info.setTitle("Erro");
            info.setContentText("Desculpe! Tente novamente!");
            info.show();
            e.printStackTrace();
        }
        
    }


    @FXML
    protected void botaoConsumirJantar() {
        try {
            
            String cpf = UserAtual.getInstance().getCpf();
            String tipoJanta = "Janta";

            HttpClient client = HttpClient.newHttpClient();
            
            // Requisição para almoço não consumido
            HttpRequest requestAlmoco = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:3333/ticket/consumir"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{\"usuario\": \"" + cpf + "\", \"tipo\": \"" + tipoJanta + "\"}"))
                    .build();

             // Envia a requisição para a API e obtém a resposta para o almoço
             HttpResponse<String> responseJanta = client.send(requestAlmoco, HttpResponse.BodyHandlers.ofString());
             // Verifica se a resposta da API indica um login válido para o almoço
             if (responseJanta.statusCode() == 200) {
                labelQtdAlmoco.setText("");
                labelQtdJanta.setText("");
               initialize();
            }
            

        } catch (IOException | InterruptedException e) {
            Alert info = new Alert(Alert.AlertType.ERROR);
            info.setTitle("Erro");
            info.setContentText("Desculpe! Tente novamente!");
            info.show();
            e.printStackTrace();
        }
        
    }

    @FXML
    protected void botaoVoltar(ActionEvent event) throws IOException {
        if (UserAtual.getInstance().gettipoUser()==1) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TelaAluno.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Tela Inicial");
        } else if (UserAtual.getInstance().gettipoUser()==2) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TelaFuncionario.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Tela inicial");
        }
    }
}
