package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.model.Login;

public class UserDao implements IUserDao {
	private DataSource dataSource;
	private Login login;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	

	/**
	 * Metodo para obtener TODOS los datos de un registro de la tabla PERSONAS
	 * @param login se usara login en caso de que se este buscando con el correo y el password del usuario
	 * @param personaNo se usara este valor cuando se desee buscar por medio de la llave primaria USER_NO
	 * @return login contendra los datos que se encuentren en el registro encontrado
	 */
	@Override
	public Login query(Login login) {
		String sql;
		
		sql = "SELECT * FROM NS_USERS WHERE ID = ? AND PASSWORD = ?";//Buscar con los datos del login
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, login.getId());
			ps.setString(2, login.getPassword());
			
			login = new Login();
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				login.setUserNo(rs.getInt("USER_NO"));
				login.setId(rs.getString("ID"));
				login.setName(rs.getString("NAME"));
			}
			rs.close();
			ps.close();
			return login;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	/**
	 * Metodo para obtener la llave primaria USER_NO de la tabla PERSONAS buscando con el correo
	 * @param username contendra el correo con el que queremos buscar el registro
	 * @return personaNo devuelve la llave primaria obetenida, en caso de que no encuentre registro devolvera 0
	 */
	@Override
	public int searchIdByUsername(String username) {
		String sql = "SELECT USER_NO FROM NS_USERS WHERE ID = ?";
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			int personaNo = 0;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				personaNo = rs.getInt("USER_NO");
			}
			rs.close();
			ps.close();
			return personaNo;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	/**
	 * Metodo para buscar la ultima llave primaria USER_NO de la tabla personas e incrementarla en 1
	 * @return personaNo es el valor de la siguiente llave primaria que se debe insertar en la tabla
	 */
	@Override
	public int searchLastId() {
		String sql = "SELECT USER_NO FROM NS_USERS ORDER BY USER_NO DESC";
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			int personaNo = 0, temp = 0;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (temp == 1)
					rs.close();
				personaNo = rs.getInt("USER_NO") + 1;
				temp ++;
			}
			rs.close();
			ps.close();
			return personaNo;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

}