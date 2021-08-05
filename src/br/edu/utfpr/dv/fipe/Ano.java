package br.edu.utfpr.dv.fipe;

public class Ano {

	private String codigo;
	private String nome;
	
	public Ano() {
		this.setCodigo("");
		this.setNome("");
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String toString() {
		return this.getNome();
	}
	
}
