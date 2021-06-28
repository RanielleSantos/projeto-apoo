package br.edu.facthus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.facthus.exception.CustomException;
import br.edu.facthus.model.Pessoa;


// Tarefa 15: completar...
public class ProprietarioDAO {
	
	private Connection connection = null;
	
	private Connection getConnection() {
		try {
			if (connection == null)
				connection = DriverManager
					.getConnection("jdbc:sqlite:imoveis.db");
			
			return connection;
		} catch (SQLException e) {
			throw new CustomException("Erro abrindo conexão com o banco de dados.");
		}
	}
	
	public void insere(Pessoa proprietario) 
	{
		
		try {
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO pessoas (nome, cpf, telefone) "
					+ "VALUES (?, ?)");
			
			statement.setString(1, proprietario.getNome());
			statement.setString(2, proprietario.getCpf());
			statement.setString(2, proprietario.getTelefone());
			
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CustomException("Ocorreu um erro ao inserir .");
		}
	}
	
	public List<Pessoa> pesquisa() 
	{
		List<Pessoa> Proprietario = new ArrayList<>();
		
		try {
			Connection connection = getConnection();
			Statement Statement = connection.createStatement();
			
			ResultSet rs = Statement.executeQuery(
					"SELECT id, nome, cpf, telefone FROM pessoas ORDER BY email");
			
			while (rs.next()) 
			{
				Pessoa proprietario01 = new Pessoa();
				proprietario01.setId(rs.getInt("id"));
				proprietario01.setNome(rs.getString("nome"));
				proprietario01.setCpf(rs.getString("cpf"));
				proprietario01.setTelefone(rs.getString("telefone"));
				
				Proprietario.add(proprietario01);
			}
		
		return null;
	} catch (SQLException e) {
		e.printStackTrace();
		throw new CustomException("Ocorreu um erro ao pesquisar .");
	}		
}

}
