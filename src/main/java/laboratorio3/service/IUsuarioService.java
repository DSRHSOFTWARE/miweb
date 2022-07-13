package laboratorio3.service;

import java.util.List;




import laboratorio3.models.entity.Usuario;


public interface IUsuarioService {
	
	public List<Usuario> findAll();
	
	public void save(Usuario usuario);
	
	public Usuario findOne(Long id);
	
	public void delete(Long id);

}
