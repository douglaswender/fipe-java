package br.edu.utfpr.dv.fipe;

import java.util.ArrayList;
import java.util.List;

public class ListaModelos {

	private List<Modelo> modelos;
	private List<Ano> anos;

	public ListaModelos() {
		this.setModelos(new ArrayList<Modelo>());
		this.setAnos(new ArrayList<Ano>());
	}
	
	public List<Modelo> getModelos() {
		return modelos;
	}
	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}
	public List<Ano> getAnos() {
		return anos;
	}
	public void setAnos(List<Ano> anos) {
		this.anos = anos;
	}
	
}
