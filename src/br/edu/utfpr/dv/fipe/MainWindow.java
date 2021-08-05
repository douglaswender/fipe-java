package br.edu.utfpr.dv.fipe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainWindow {

	private JFrame frmTabelaFipe;
	private JComboBox<Marca> comboMarca;
	private JComboBox<Modelo> comboModelo;
	private JComboBox<Ano> comboAno;
	private JLabel labelValor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmTabelaFipe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTabelaFipe = new JFrame();
		frmTabelaFipe.setTitle("Tabela Fipe");
		frmTabelaFipe.setResizable(false);
		frmTabelaFipe.setBounds(100, 100, 450, 300);
		frmTabelaFipe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTabelaFipe.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblMarca = new JLabel("Marca:");
		frmTabelaFipe.getContentPane().add(lblMarca);
		
		comboMarca = new JComboBox<Marca>();
		comboMarca.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if(comboMarca.getSelectedItem() != null) {
		    		carregarModelos((Marca)comboMarca.getSelectedItem());
		    	}
		    }
		});
		frmTabelaFipe.getContentPane().add(comboMarca);
		
		JLabel lblModelo = new JLabel("Modelo:");
		frmTabelaFipe.getContentPane().add(lblModelo);
		
		comboModelo = new JComboBox<Modelo>();
		comboModelo.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if((comboMarca.getSelectedItem() != null) && (comboModelo.getSelectedItem() != null)) {
		    		carregarAnos((Marca)comboMarca.getSelectedItem(), (Modelo)comboModelo.getSelectedItem());
		    	}
		    }
		});
		frmTabelaFipe.getContentPane().add(comboModelo);
		
		JLabel lblAno = new JLabel("Ano:");
		frmTabelaFipe.getContentPane().add(lblAno);
		
		comboAno = new JComboBox<Ano>();
		comboAno.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if((comboMarca.getSelectedItem() != null) && (comboModelo.getSelectedItem() != null) && (comboAno.getSelectedItem() != null)) {
		    		carregarValor((Marca)comboMarca.getSelectedItem(), (Modelo)comboModelo.getSelectedItem(), (Ano)comboAno.getSelectedItem());
		    	}
		    }
		});
		frmTabelaFipe.getContentPane().add(comboAno);
		
		JLabel lblValor = new JLabel("Valor:");
		frmTabelaFipe.getContentPane().add(lblValor);
		
		labelValor = new JLabel("");
		labelValor.setHorizontalAlignment(SwingConstants.CENTER);
		labelValor.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelValor.setForeground(Color.RED);
		frmTabelaFipe.getContentPane().add(labelValor);
		
		this.carregarMarcas();
	}
	
	private String getContent(String url) throws Exception {
		CloseableHttpClient httpCliente = HttpClientBuilder.create().build();
		HttpUriRequest request = RequestBuilder.get().setUri(url)
				.setHeader("accept", "application/json").build();
		CloseableHttpResponse response = httpCliente.execute(request);
		
		if(response.getStatusLine().getStatusCode() == 200) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			
			return IOUtils.toString(reader);
		} else {
			throw new Exception("HTTP code: " + 
					String.valueOf(response.getStatusLine().getStatusCode()));
		}
	}

	private void carregarMarcas() {
		try {
			String json = getContent("https://parallelum.com.br/fipe/api/v1/carros/marcas");
			
			ObjectMapper mapper = new ObjectMapper();
			List<Marca> marcas = mapper.readValue(json, 
					new TypeReference<List<Marca>>() {});
			
			this.comboMarca.removeAllItems();
			for(Marca m: marcas) {
				this.comboMarca.addItem(m);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void carregarModelos(Marca marca) {
		try {
			String json = getContent(
					"https://parallelum.com.br/fipe/api/v1/carros/marcas/" +
					String.valueOf(marca.getCodigo()) +	"/modelos");
			
			ObjectMapper mapper = new ObjectMapper();
			ListaModelos modelos = mapper.readValue(json, ListaModelos.class);
			
			this.comboModelo.removeAllItems();
			for(Modelo m : modelos.getModelos()) {
				this.comboModelo.addItem(m);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void carregarAnos(Marca marca, Modelo modelo) {
		try {
			System.out.println(modelo);
			String json = getContent("https://parallelum.com.br/fipe/api/v1/carros/marcas/" +
					String.valueOf(marca.getCodigo()) +	"/modelos/"+modelo.getCodigo()+"/anos");
			
			System.out.println(json);
			
			ObjectMapper mapper = new ObjectMapper();
			List<Ano> anos = mapper.readValue(json, new TypeReference<List<Ano>>() {});
			
			this.comboAno.removeAllItems();
			
			for(Ano a: anos) {
				this.comboAno.addItem(a);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void carregarValor(Marca marca, Modelo modelo, Ano ano) {
		try {
			System.out.println(modelo);
			String json = getContent("https://parallelum.com.br/fipe/api/v1/carros/marcas/" +
					String.valueOf(marca.getCodigo()) +	"/modelos/"+modelo.getCodigo()+"/anos/"+ano.getCodigo());
			
			System.out.println(json);
			
			ObjectMapper mapper = new ObjectMapper();
			Veiculo veiculo = mapper.readValue(json, new TypeReference<Veiculo>() {});
			
			this.labelValor.setText(veiculo.getValor());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
