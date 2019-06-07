package com.sert.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sert.dao.PermissaoDao;
import com.sert.dao.UsuDao;
import com.sert.entidades.PermissoesFunc;
import com.sert.entidades.Usuario;
import com.sert.exceptions.NenhumUsuCadException;
import com.sert.exceptions.UsuarioJaCadastradoException;
import com.sert.exceptions.UsuarioNaoCadastradoException;

public class ControlerUsuario {
	private UsuDao usuDao;
	private Usuario usuario;
	private PermissaoDao permissaoDao;
	public ControlerUsuario() throws ClassNotFoundException, SQLException, IOException {
		usuDao = new UsuDao();
		permissaoDao = new PermissaoDao();
	}

	public void cadastrarUsuario(Usuario usu, PermissoesFunc permissoes) throws SQLException, UsuarioJaCadastradoException {
		usuario = usuDao.consultaCad(usu.getNome());
		if(usuario.getNome() != null) throw new UsuarioJaCadastradoException();
		usuDao.cadastrar(usu);
		cadPermissoes(permissoes);
	}

	public List<Usuario> listarUsuario() throws SQLException, NenhumUsuCadException {
		if (usuDao.listar().isEmpty()) {
			throw new NenhumUsuCadException();
		} else {
			return usuDao.listar();
		}
	}

	public void atualizarUsuario(Usuario usu, PermissoesFunc permissoes) throws SQLException{
		usuario = consultaUsuEdit(usu.getId());
		if(usu.getSenha().equals("D41D8CD98F00B204E9800998ECF8427E")) {
			usu.setSenha(usuario.getSenha());
		}
		usuDao.atualizar(usu);
		alterarPermissoes(permissoes);
	}

	public void excluirUsuario(int id) throws SQLException {
		usuDao.excluir(id);
	}

	public int confereId() throws SQLException {
		return usuDao.confereId();
	}

	public Usuario login(String login, String senha) throws UsuarioNaoCadastradoException, SQLException {
		if(usuDao.consulta(login, senha).getNome() == null) throw new UsuarioNaoCadastradoException();
		
		return usuDao.consulta(login, senha);
	}
	
	public Usuario consultaUsuEdit(int id) throws SQLException {
		return usuDao.consultaAlter(id);
	}
	
	private void cadPermissoes(PermissoesFunc permissoes) throws SQLException{
		permissaoDao.cadastrar(permissoes);
	}
	
	private void alterarPermissoes(PermissoesFunc permissoes) throws SQLException{
		permissaoDao.alterar(permissoes);
	}
	
	public PermissoesFunc consultaPermicoes(int idUsu) throws SQLException{
		return permissaoDao.permissaoUsu(idUsu);
	}
}