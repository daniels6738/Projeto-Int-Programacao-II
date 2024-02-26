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

import java.io.IOException;
import java.util.Objects;

public class TelaFuncionarioController {
   
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String body;

    public void setBody(String body){
        this.body = body;
    }
    public String pegarNome(String body){
                    
            // Encontrar o índice inicial do nome
            int startIndex = body.indexOf("\"nome\":\"") + "\"nome\":\"".length();
            
            // Encontrar o índice final do nome
            int endIndex = body.indexOf("\"", startIndex);

            // Extrair o CPF usando substring
            String nome = body.substring(startIndex, endIndex);
            
        return nome;
    }


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
        System.out.println("entrou no botao de iniciar");
       // System.out.println(body);
      //  labelBemvindo.setText(labelBemvindo.getText() + pegarNome(body));
      //  labelQtdAlmoco.setText(labelQtdAlmoco.getText() + Controlador.getInstance().listarTicketAlmocoNaoConsumido(Controlador.getInstance().getUsuario()).size());
       // labelQtdJantar.setText(labelQtdJantar.getText() + Controlador.getInstance().listarTicketJantarNaoConsumido(Controlador.getInstance().getUsuario()).size());
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
