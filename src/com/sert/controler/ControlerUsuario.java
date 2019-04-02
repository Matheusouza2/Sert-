package com.sert.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sert.dao.IUsuDao;
import com.sert.dao.UsuDao;
import com.sert.entidades.Usuario;
import com.sert.exceptions.NenhumUsuCadException;
import com.sert.exceptions.UsuarioJaCadastradoException;
import com.sert.exceptions.UsuarioNaoCadastradoException;

public class ControlerUsuario {
	private IUsuDao usuDao;

	public ControlerUsuario() throws ClassNotFoundException, SQLException, IOException {
		usuDao = new UsuDao();
	}

	public void cadastrarUsuario(Usuario usu) throws SQLException, UsuarioJaCadastradoException {
		Usuario usuario = usuDao.consultaCad(usu.getNome());
		if(usuario.getNome() != null) throw new UsuarioJaCadastradoException();
		usuDao.cadastrar(usu);
	}

	public List<Usuario> listarUsuario() throws SQLException, NenhumUsuCadException {
		if (usuDao.listar().isEmpty()) {
			throw new NenhumUsuCadException();
		} else {
			return usuDao.listar();
		}
	}

	public void atualizarUsuario(Usuario usu) throws SQLException{
		usuDao.atualizar(usu);
	}

	public void excluirUsuario(int id) throws SQLException {
		usuDao.excluir(id);
	}

	public int confereId() throws SQLException {
		return usuDao.confereId();
	}

	public Usuario login(String login, String senha) throws UsuarioNaoCadastradoException, SQLException {
		if(usuDao.consulta(login, senha) == null) throw new UsuarioNaoCadastradoException();
		
		return usuDao.consulta(login, senha);
	}
	
	public Usuario consultaUsuEdit(int id) throws SQLException {
		return usuDao.consultaAlter(id);
	}
}