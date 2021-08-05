package br.edu.utfpr.dv.fipe;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Veiculo {

	@JsonProperty("Valor")
	private String valor;
	@JsonProperty("Marca")
	private String marca;
	@JsonProperty("Modelo")
	private String modelo;
	@JsonProperty("AnoModelo")
	private String anoModelo;
	@JsonProperty("Combustivel")
	private String combustivel;
	@JsonProperty("CodigoFipe")
	private String codigoFipe;
	@JsonProperty("MesReferencia")
	private String mesReferencia;
	@JsonProperty("TipoVeiculo")
	private String tipoVeiculo;
	@JsonProperty("SiglaCombustivel")
	private String siglaCombustivel;
	
	public Veiculo() {
		this.setValor("");
		this.setMarca("");
		this.setModelo("");
		this.setAnoModelo("");
		this.setCombustivel("");
		this.setCodigoFipe("");
		this.setMesReferencia("");
		this.setTipoVeiculo("");
		this.setSiglaCombustivel("");
	}
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getAnoModelo() {
		return anoModelo;
	}
	public void setAnoModelo(String anoModelo) {
		this.anoModelo = anoModelo;
	}
	public String getCombustivel() {
		return combustivel;
	}
	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}
	public String getCodigoFipe() {
		return codigoFipe;
	}
	public void setCodigoFipe(String codigoFipe) {
		this.codigoFipe = codigoFipe;
	}
	public String getMesReferencia() {
		return mesReferencia;
	}
	public void setMesReferencia(String mesReferencia) {
		this.mesReferencia = mesReferencia;
	}
	public String getTipoVeiculo() {
		return tipoVeiculo;
	}
	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
	public String getSiglaCombustivel() {
		return siglaCombustivel;
	}
	public void setSiglaCombustivel(String siglaCombustivel) {
		this.siglaCombustivel = siglaCombustivel;
	}
	public String toString() {
		return this.getModelo();
	}
}
