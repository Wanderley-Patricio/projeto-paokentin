package com.paokentin.paokentin.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.paokentin.paokentin.model.Fornada;
import com.paokentin.paokentin.model.Pao;

public class FornadaRepositorio {

	private static FornadaRepositorio instance = null;

	private FornadaRepositorio() {

	}

	public static FornadaRepositorio getInstance() {
		if (instance == null) {
			instance = new FornadaRepositorio();
		}
		return instance;
	}

	public void inserir(Fornada f) throws SQLException {
		String sql = "insert into tbl_fornada(entrada, saida, pao_cod) values (?,?,?)";
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		f.atualizaSaida();
		pstm.setObject(1, f.getEntrada());
		pstm.setObject(2, f.getSaida());
		pstm.setInt(3, f.getPao().getCodigo());
		pstm.execute();
	}

	public Fornada ler(int codigo) throws SQLException {
		String sql = "select * from tbl_fornada as f join tbl_pao as p on (f.pao_cod = p.codigo) where f.codigo = ?";
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		pstm.setInt(1, codigo);
		pstm.executeQuery();
		ResultSet result = pstm.getResultSet();
		Fornada f = null;
		if (result.next()) {
			f = new Fornada();
			f.setCodigo(codigo);
			f.setEntrada(result.getObject("entrada", LocalDateTime.class));
			f.setSaida(result.getObject("saida", LocalDateTime.class));
			Pao p = new Pao();
			p.setCodigo(result.getInt("p.codigo"));
			p.setNome(result.getString("nome"));
			p.setDescricao(result.getString("descricao"));
			p.setTempoAssar(result.getInt("tempo_assar"));
			f.setPao(p);
		}
		return f;
	}

	public List<Fornada> lerTodos() throws SQLException {
		String sql = "select * from tbl_fornada as f join tbl_pao as p on (f.pao_cod = p.codigo)";
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		pstm.executeQuery();
		ResultSet result = pstm.getResultSet();
		List<Fornada> fornadas = new ArrayList<>();
		while (result.next()) {
			Fornada f = new Fornada();
			f.setCodigo(result.getInt("f.codigo"));
			f.setEntrada(result.getObject("entrada", LocalDateTime.class));
			f.setSaida(result.getObject("saida", LocalDateTime.class));
			Pao p = new Pao();
			f.setPao(p);
			p.setCodigo(result.getInt("p.codigo"));
			p.setNome(result.getString("nome"));
			p.setDescricao(result.getString("descricao"));
			p.setTempoAssar(result.getInt("tempo_assar"));
			fornadas.add(f);
		}
		return fornadas;
	}

	public void atualizar(Fornada f) throws SQLException {
		String sql = "update tbl_fornada set entrada=?, saida=?, pao_cod=? where codigo=?";
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		pstm.setObject(1, f.getEntrada());
		pstm.setObject(2, f.getSaida());
		pstm.setInt(3, f.getPao().getCodigo());
		pstm.setInt(4, f.getCodigo());
		pstm.executeUpdate();
	}

	public void deletar(int codigo) throws SQLException {
		String sql = "delete from tbl_fornada where codigo=?";
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		pstm.setInt(1, codigo);
		pstm.execute();
	}

}
