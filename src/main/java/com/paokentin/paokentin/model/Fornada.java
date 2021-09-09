package com.paokentin.paokentin.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Fornada {

	private int codigo;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private Pao pao;
	
	public Fornada() {
		this.entrada = LocalDateTime.now();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Pao getPao() {
		return pao;
	}

	public void setPao(Pao pao) {
		this.pao = pao;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}

	public LocalDateTime getSaida() {
		return saida;
	}

	public void setSaida(LocalDateTime saida) {
		this.saida = saida;
	}
	
	public void atualizaSaida() {
		if (pao != null) {
			this.saida = this.entrada.plusMinutes(pao.getTempoAssar());
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornada other = (Fornada) obj;
		return codigo == other.codigo;
	}

	@Override
	public String toString() {
		return "Fornada [codigo=" + codigo + ", pao=" + pao + ", entrada=" + entrada + ", saida=" + saida + "]";
	}

}
