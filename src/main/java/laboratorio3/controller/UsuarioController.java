package laboratorio3.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import laboratorio3.models.entity.Usuario;
import laboratorio3.repository.UsuarioRepository;
import laboratorio3.service.IUsuarioService;

@Controller
public class UsuarioController  {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private IUsuarioService iusuarioService;
	
	
	@GetMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id")long id,Map<String, Object>model, RedirectAttributes flash ) {
	
		Usuario usuario = iusuarioService.findOne(id);
	if(usuario==null) {
		flash.addAttribute("error", "el usuario no existe");
		return "redirect:/index";
	}
	model.put("usuario", usuario);
	model.put("titulo", "Detalle Usuario: " + usuario.getNombre());
	return "ver";
	}
	
	@GetMapping({"/","index"})
	public String verPaginaDeInicio(Model modelo) {
		List<Usuario> usuarios = usuarioRepository.findAll();
		modelo.addAttribute("usuarios", usuarios);
		return "index";
	
	}

	@GetMapping("/nuevo")
	public String mostrarFormularioDeRegistrarUsuario(Model  modelo) {
		modelo.addAttribute("usuario", new Usuario());
		return "nuevo";
	}
	
	@PostMapping("/nuevo")
	public String guardarUsuario(@Valid Usuario usuario,BindingResult result,RedirectAttributes flash,Model modelo,SessionStatus status) {
		if(result.hasErrors()) {
			modelo.addAttribute("usuario", usuario);
			return "nuevo";
		}
		usuarioRepository.save(usuario);
		status.setComplete();
		flash.addAttribute("success",mensajeFlash);
		return "redirect:index";
	}
	
	String mensajeFlash = "el usuario fue agregado con exito";
	
	
	
	@GetMapping("/{id}/editar")
	public String mostrarFormularioDeEditarUsuario(@PathVariable Long id,Model modelo) {
		Usuario usuario = usuarioRepository.getById(id);
		modelo.addAttribute("usuario", usuario);
		return "nuevo";
	}
	
	@PostMapping("/{id}/editar")
	public String actualizarUsuario(@PathVariable Long id,@Validated Usuario usuario,BindingResult bindingResult,RedirectAttributes redirect,Model modelo) {
		Usuario usuarioDB = usuarioRepository.getById(id);
		if(bindingResult.hasErrors()) {
			modelo.addAttribute("usuario", usuario);
			return "nuevo";
		}
		usuarioDB.setNombre(usuario.getNombre());
		usuarioDB.setLibro(usuario.getLibro());
		usuarioDB.setCantidad(usuario.getCantidad());
		usuarioDB.setFechaNacimiento(usuario.getFechaNacimiento());
		
		usuarioRepository.save(usuarioDB);
		redirect.addAttribute("msgExito", "El usuario ha sido actualizado correctamente");
		return "redirect:/";
	}
	
	@PostMapping("/{id}/{eliminar}")
	public String eliminarUsuario(@PathVariable Long id,RedirectAttributes redirect) {
		Usuario usuario = usuarioRepository.getById(id);
		usuarioRepository.delete(usuario);
		redirect.addAttribute("msgExito", "El usuario ha sido eliminado");
		return "redirect:/";
	}
	
	//HorariodeAtencion
	
	@Value("${config.horario.apertura}")
	private Integer apertura;
	@Value("${config.horario.cierre}")
	private Integer cierre;
	
	@GetMapping({"/abierto"})
	public String abierto(Model model) {
		model.addAttribute("titulo","Bienvenido al horario de atencion a clientes");
		return "abierto";
	}
	
	@GetMapping("/cerrado")
	public String cerrado(Model model) {
		StringBuilder mensaje = new StringBuilder("Cerrado, por favor visitenos  desde las ");
		mensaje.append(apertura);
		mensaje.append(" y las ");
		mensaje.append(cierre);
		mensaje.append(" horas. Gracias");
		model.addAttribute("titulo", "Fuera del horario de atencion");
		model.addAttribute("mensaje", mensaje);
		return "cerrado";
	}
	

}
	
	

