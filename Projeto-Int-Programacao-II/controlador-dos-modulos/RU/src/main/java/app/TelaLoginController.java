package app;

import exceptions.LoginInvalidoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Estudante;
import negocio.Controlador;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class TelaLoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField CPFTextField;

    @FXML
    private PasswordField senhaPasswordField;

    @FXML
    protected void initialize() {
        if (Controlador.getInstance().getUsuario() != null) {
            CPFTextField.setText(Controlador.getInstance().getUsuario().getCpf());
            senhaPasswordField.setText(Controlador.getInstance().getUsuario().getSenha());
        }
    }

    @FXML
    protected void botaoLoginApertar(ActionEvent event) throws IOException {
        System.out.println("entoru aqui 1");
        String cpf = CPFTextField.getText();
        String senha = senhaPasswordField.getText();

        // Cria um cliente HTTP
        HttpClient client = HttpClient.newHttpClient();
        System.out.println("entoru aqui 2");

        // Cria a requisição HTTP para a rota de login da API
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3332/usuario/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"cpf\": \"" + cpf + "\", \"senha\": \"" + senha + "\"}"))
                .build();
                System.out.println("entoru aqui 3");

        try {
            // Envia a requisição para a API e obtém a resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("entoru aqui 4");

          
      


            // Verifica se a resposta da API indica um login válido
            if (response.statusCode() == 200) {
               // System.out.println("entoru aqui 5");
                System.out.println(response.body());
                // Se o login for válido, carrega a interface correspondente
                if (response.body().contains("estudante")) {
                    
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TelaAluno.fxml")));
                } else {
                    //chamada da tela funcionario (assim funciona porem não chama a tela)
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TelaFuncionario.fxml")));
                 //desse modo é para passar o parametro para saber o usuario
                   /*  
                   FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("TelaFuncionario.fxml")));
                    Parent root = loader.load();
                    TelaFuncionarioController controller = loader.getController();
                    controller.setBody(response.body());
                    */
                    
                    
                    
                    
                  
                }








                // Configura a cena e exibe a tela inicial
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                stage.setTitle("Tela inicial");
            } else {
                // Se a resposta da API indicar um login inválido, exibe um alerta
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Login inválido!");
                alerta.setContentText("CPF ou senha incorretos.");
                alerta.show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
