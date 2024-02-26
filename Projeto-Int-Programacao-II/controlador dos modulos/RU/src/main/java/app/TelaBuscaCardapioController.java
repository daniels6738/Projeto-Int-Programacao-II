package app;

import exceptions.ElementoNaoExisteException;
import exceptions.ParametroVazioException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.DiasDaSemana;
import models.OpcaoRefeicao;
import models.TipoRefeicao;
import negocio.Controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TelaBuscaCardapioController {
    	
	private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ChoiceBox<TipoRefeicao> cbTipo;
    @FXML
    private TextField principal1SegundaTextField;
    
    @FXML
    private TextField principal1TercaTextField;
    
    @FXML
    private TextField principal1QuartaTextField;
    
    @FXML
    private TextField principal1QuintaTextField;
    
    @FXML
    private TextField principal1SextaTextField;
    
    
    @FXML
    private TextField principal2SegundaTextField;
    
    @FXML
    private TextField principal2TercaTextField;
    
    @FXML
    private TextField principal2QuartaTextField;
    
    @FXML
    private TextField principal2QuintaTextField;
    
    @FXML
    private TextField principal2SextaTextField;
    
    
    @FXML
    private TextField fastSegundaTextField;
    
    @FXML
    private TextField fastTercaTextField;
    
    @FXML
    private TextField fastQuartaTextField;
    
    @FXML
    private TextField fastQuintaTextField;
    
    @FXML
    private TextField fastSextaTextField;
    
    
    @FXML
    private TextField vegetarianoSegundaTextField;
    
    @FXML
    private TextField vegetarianoTercaTextField;
    
    @FXML
    private TextField vegetarianoQuartaTextField;
    
    @FXML
    private TextField vegetarianoQuintaTextField;
    
    @FXML
    private TextField vegetarianoSextaTextField;
    
    
    @FXML
    private TextField sucoSegundaTextField;
    
    @FXML
    private TextField sucoTercaTextField;
    
    @FXML
    private TextField sucoQuartaTextField;
    
    @FXML
    private TextField sucoQuintaTextField;
    
    @FXML
    private TextField sucoSextaTextField;
    
    
    @FXML
    private TextField sobremesaSegundaTextField;
    
    @FXML
    private TextField sobremesaTercaTextField;
    
    @FXML
    private TextField sobremesaQuartaTextField;
    
    @FXML
    private TextField sobremesaQuintaTextField;
    
    @FXML
    private TextField sobremesaSextaTextField;

    
    @FXML
    private DatePicker dataInicioCardapio;
    
    @FXML
    protected void initialize(){
       dataInicioCardapio.setValue(LocalDate.now());
       ObservableList<TipoRefeicao> tipos = FXCollections.observableArrayList();
       tipos.add(TipoRefeicao.ALMOCO);
       tipos.add(TipoRefeicao.JANTAR);
       cbTipo.setItems(tipos);
       
          }
    @FXML
    protected void botaoBuscarCardapio() {
	int i=Controlador.getInstance().indexSemanaCardapio(dataInicioCardapio.getValue(),cbTipo.getValue());
	
    if(i!=-1) {
    	principal1SegundaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.SEGUNDA).getOpcao1());
    	principal2SegundaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.SEGUNDA).getOpcao2());
    	fastSegundaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.SEGUNDA).getFastGrill());
    	vegetarianoSegundaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.SEGUNDA).getOpcaoVegana());
    	sucoSegundaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.SEGUNDA).getSuco());
    	sobremesaSegundaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.SEGUNDA).getSobremesa());
    	
    	principal1TercaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.TERCA).getOpcao1());
    	principal2TercaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.TERCA).getOpcao2());
    	fastTercaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.TERCA).getFastGrill());
    	vegetarianoTercaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.TERCA).getOpcaoVegana());
    	sucoTercaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.TERCA).getSuco());
    	sobremesaTercaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.TERCA).getSobremesa());
    	
    	principal1QuartaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.QUARTA).getOpcao1());
    	principal2QuartaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.QUARTA).getOpcao2());
    	fastQuartaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.QUARTA).getFastGrill());
    	vegetarianoQuartaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.QUARTA).getOpcaoVegana());
    	sucoQuartaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.QUARTA).getSuco());
    	sobremesaQuartaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.QUARTA).getSobremesa());
    	
    	principal1QuintaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.QUINTA).getOpcao1());
    	principal2QuintaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.QUINTA).getOpcao2());
    	fastQuintaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.QUINTA).getFastGrill());
    	vegetarianoQuintaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.QUINTA).getOpcaoVegana());
    	sucoQuintaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.QUINTA).getSuco());
    	sobremesaQuintaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.QUINTA).getSobremesa());
    	
    	principal1SextaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.SEXTA).getOpcao1());
    	principal2SextaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.SEXTA).getOpcao2());
    	fastSextaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.SEXTA).getFastGrill());
    	vegetarianoSextaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.SEXTA).getOpcaoVegana());
    	sucoSextaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.SEXTA).getSuco());
    	sobremesaSextaTextField.setText(Controlador.getInstance().listarCardapioSemanal().get(i).getCardapio().get(DiasDaSemana.SEXTA).getSobremesa());
        }
        else {
        	
        	limparTabela();    	
        	
        	Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Cardápio não existe");
            info.setContentText("O Cardápio buscado não existe");
            info.show();
        }
		
	
    }
    
    @FXML
    protected void botaoEditarCardapio() {
    	try {
    	int i=Controlador.getInstance().indexSemanaCardapio(dataInicioCardapio.getValue(),cbTipo.getValue());
    	if(i!=-1) {
    	Map<DiasDaSemana,OpcaoRefeicao> mapa=new HashMap<>();

		mapa.put(DiasDaSemana.SEGUNDA,Controlador.getInstance().inserirOpcoes(principal1SegundaTextField.getText(),
				principal2SegundaTextField.getText(),vegetarianoSegundaTextField.getText(),fastSegundaTextField.getText(),
				sucoSegundaTextField.getText(),sobremesaSegundaTextField.getText(),cbTipo.getValue(),DiasDaSemana.SEGUNDA) );
		
		mapa.put(DiasDaSemana.TERCA,Controlador.getInstance().inserirOpcoes(principal1TercaTextField.getText(),
				principal2TercaTextField.getText(),vegetarianoTercaTextField.getText(),fastTercaTextField.getText(),
				sucoTercaTextField.getText(),sobremesaTercaTextField.getText(),cbTipo.getValue(),DiasDaSemana.TERCA) );
		
		mapa.put(DiasDaSemana.QUARTA,Controlador.getInstance().inserirOpcoes(principal1QuartaTextField.getText(),
				principal2QuartaTextField.getText(),vegetarianoQuartaTextField.getText(),fastQuartaTextField.getText(),
				sucoQuartaTextField.getText(),sobremesaQuartaTextField.getText(),cbTipo.getValue(),DiasDaSemana.QUARTA) );
	
		mapa.put(DiasDaSemana.QUINTA,Controlador.getInstance().inserirOpcoes(principal1QuintaTextField.getText(),
				principal2QuintaTextField.getText(),vegetarianoQuintaTextField.getText(),fastQuintaTextField.getText(),
				sucoQuintaTextField.getText(),sobremesaQuintaTextField.getText(),cbTipo.getValue(),DiasDaSemana.QUINTA) );
		
		mapa.put(DiasDaSemana.SEXTA,Controlador.getInstance().inserirOpcoes(principal1SextaTextField.getText(),
				principal2SextaTextField.getText(),vegetarianoSextaTextField.getText(),fastSextaTextField.getText(),
				sucoSextaTextField.getText(),sobremesaSextaTextField.getText(),cbTipo.getValue(),DiasDaSemana.SEXTA) );
    	
    	Controlador.getInstance().listarCardapioSemanal().get(i).setCardapio(mapa);
    	
    	Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Cardapio Atualizado");
        info.setContentText("O cardapio Atualizado com sucesso");
        info.show();
    	}else {
    		Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Cardápio indisponivel");
            info.setContentText("O Cardápio que deseja atualizar não existe");
            info.show();
    	}
    	
    	} catch (ParametroVazioException e) {
			Alert info = new Alert(Alert.AlertType.WARNING);
            info.setTitle("Campo vazio!");
            info.setContentText(e.getMessage());
            info.show();
    	}
			
		
    	

    }
    
    @FXML
    protected void botaoRemoverCardapio() {
    	int i=Controlador.getInstance().indexSemanaCardapio(dataInicioCardapio.getValue(),cbTipo.getValue());
    	try {
    	if(i!=1 && dataInicioCardapio.getValue().isAfter(LocalDate.now())) {
    		Controlador.getInstance().removerCardapioSemanal(Controlador.getInstance().listarCardapioSemanal().get(i));
    	
    		
    		Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Cardapio Removido");
            info.setContentText("O cardapio foi removido com sucesso");
            info.show();
            limparTabela();
    		
    	
		}
    	}catch (ElementoNaoExisteException e) {
    		Alert info = new Alert(Alert.AlertType.WARNING);
            info.setTitle("Cardapio não existe");
            info.setContentText("O Cardapio que voce tentou remover não existe");
            info.show();
		}
    	
    
    	
    	
    }


      @FXML
    protected void botaoVoltar(ActionEvent event) throws IOException {
          root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TelaFuncionario.fxml")));
          stage = (Stage)((Node)event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
          stage.setTitle("Tela Inicial");
    }
    
      @FXML
      private void limparTabela() {
    	  principal1SegundaTextField.setText("");
      	principal1TercaTextField.setText("");
      	principal1QuartaTextField.setText("");
      	principal1QuintaTextField.setText("");
      	principal1SextaTextField.setText("");
      	
      	principal2SegundaTextField.setText("");
      	principal2TercaTextField.setText("");
      	principal2QuartaTextField.setText("");
      	principal2QuintaTextField.setText("");
      	principal2SextaTextField.setText("");
      	
      	fastSegundaTextField.setText("");
      	fastTercaTextField.setText("");
      	fastQuartaTextField.setText("");
      	fastQuintaTextField.setText("");
      	fastSextaTextField.setText("");
      	
      	vegetarianoSegundaTextField.setText("");
      	vegetarianoTercaTextField.setText("");
      	vegetarianoQuartaTextField.setText("");
      	vegetarianoQuintaTextField.setText("");
      	vegetarianoSextaTextField.setText("");
      	
      	sucoSegundaTextField.setText("");
      	sucoTercaTextField.setText("");
      	sucoQuartaTextField.setText("");
      	sucoQuintaTextField.setText("");
      	sucoSextaTextField.setText("");
      	
      	sobremesaSegundaTextField.setText("");
      	sobremesaTercaTextField.setText("");
      	sobremesaQuartaTextField.setText("");
      	sobremesaQuintaTextField.setText("");
      	sobremesaSextaTextField.setText("");
      	
  
      }
   
}


