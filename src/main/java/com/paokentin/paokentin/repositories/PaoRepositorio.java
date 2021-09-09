package com.paokentin.paokentin.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.paokentin.paokentin.model.Pao;

public class PaoRepositorio {

	private static PaoRepositorio instance = null;

	private PaoRepositorio() {

	}

	public static PaoRepositorio getInstance() {
		if (instance == null) {
			instance = new PaoRepositorio();
		}
		return instance;
	}

	public void inserir(Pao p) throws SQLException {
		String sql = "insert into tbl_pao(nome, descricao, tempo_assar) values (?,?,?)";
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		pstm.setString(1, p.getNome());
		pstm.setString(2, p.getDescricao());
		pstm.setInt(3, p.getTempoAssar());
		pstm.execute();
	}

	public Pao ler(int codigo) throws SQLException {
		String sql = "select * from tbl_pao where codigo=?";
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		pstm.setInt(1, codigo);
		pstm.executeQuery();
		ResultSet result = pstm.getResultSet();
		Pao p = null;
		if (result.next()) {
			p = new Pao();
			p.setCodigo(codigo);
			p.setNome(result.getString("nome"));
			p.setDescricao(result.getString("descricao"));
			p.setTempoAssar(result.getInt("tempo_assar"));
		}
		return p;
	}

	public List<Pao> lerTodos() throws SQLException {
		String sql = "select * from tbl_pao";
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		pstm.executeQuery();
		List<Pao> paes = new ArrayList<>();
		ResultSet result = pstm.getResultSet();
		while (result.next()) {
			Pao p = new Pao();
			p.setCodigo(result.getInt("codigo"));
			p.setNome(result.getString("nome"));
			p.setDescricao(result.getString("descricao"));
			p.setTempoAssar(result.getInt("tempo_assar"));
			paes.add(p);
		}
		return paes;
	}

	public void atualizar(Pao p) throws SQLException {
		String sql = "update tbl_pao set nome=?, descricao=?, tempo_assar=? where codigo=?";
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		pstm.setString(1, p.getNome());
		pstm.setString(2, p.getDescricao());
		pstm.setInt(3, p.getTempoAssar());
		pstm.setInt(4, p.getCodigo());
		pstm.executeUpdate();
	}

	public void deletar(int codigo) throws SQLException {
		String sql = "delete from tbl_pao where codigo=?";
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		pstm.setInt(1, codigo);
		pstm.execute();
	}

}
