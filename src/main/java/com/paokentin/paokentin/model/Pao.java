package com.paokentin.paokentin.model;

import java.util.Objects;

public class Pao {

	private int codigo;
	private String nome;
	private String descricao;
	private int tempoAssar;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getTempoAssar() {
		return tempoAssar;
	}

	public void setTempoAssar(int tempoAssar) {
		this.tempoAssar = tempoAssar;
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
		Pao other = (Pao) obj;
		return codigo == other.codigo;
	}

	@Override
	public String toString() {
		return "Pao [codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao + ", tempoAssar=" + tempoAssar
				+ "]";
	}
	
}
