package laboratorio3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import laboratorio3.models.entity.Usuario;
import laboratorio3.repository.UsuarioRepository;


@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	@Transactional(readOnly=true)
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);

	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findOne(Long id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		usuarioRepository.deleteById(id);

	}

}
