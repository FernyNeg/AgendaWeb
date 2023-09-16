package itsh.isic.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itsh.isic.dao.ContactoDao;
import itsh.isic.enums.CodRetorno;
import itsh.isic.enums.Mensajes;
import itsh.isic.excepsiones.ServiciosException;
import itsh.isic.models.ConsultaList;
import itsh.isic.models.agenda.Contacto;
import itsh.isic.service.ContactoIService;

@Service
public class ContactoServiceImpl implements ContactoIService {

	private static final Logger log = LoggerFactory.getLogger(ContactoServiceImpl.class);

	@Autowired
	private ContactoDao contacto;

	@Override
	public ConsultaList<Contacto> leerContactos(ConsultaList<Contacto> reqNombre) throws ServiciosException {
		log.info("se controla consulta de contactos ");
		reqNombre = contacto.getContactosList(reqNombre);
		return reqNombre;
	}

	@Override
	public Contacto creaContacto(Contacto reqContacto) throws ServiciosException {
		log.info("se recibe insersión de contacto: " + reqContacto.getNombre());
		try {
			Contacto res = validaContacto(reqContacto, false);
			if (res.getCodRetorno().equals(CodRetorno.CEROS.getDescripcion())) {
				res = this.contacto.creaContacto(reqContacto);
				reqContacto.setCodRetorno(res.getCodRetorno());
				reqContacto.setMensaje(res.getMensaje());
			} else {
				reqContacto.setCodRetorno(res.getCodRetorno());
				reqContacto.setMensaje(res.getMensaje());
			}
		} catch (ServiciosException e) {
			log.error("Error al guardar nuevo contacto: " + e);
			reqContacto.setCodRetorno(CodRetorno.FALLO_SERVER.getDescripcion());
			reqContacto.setMensaje(Mensajes.FALLO_SERVER.getDescripcion());
		}
		return reqContacto;
	}

	private Contacto validaContacto(Contacto contacto, boolean leeId) {
		log.info("Se valida información del contacto");
		boolean ret = true;
		if (contacto.getUsuario() == null || contacto.getUsuario().isEmpty() || contacto.getUsuario().length() < 6) {
			log.error("El Usuario del contacto esta vacio o es menor de 6 caracteres");
			contacto.setCodRetorno(CodRetorno.FALTAN_PARAMS.getDescripcion());
			contacto.setMensaje(Mensajes.CONTACTO_USU_NULL.getDescripcion());
			ret = false;
		}
		if (contacto.getCorreo() == null || contacto.getCorreo().isEmpty()) {
			log.error("El correo del contacto esta vacio");
			contacto.setCodRetorno(CodRetorno.FALTAN_PARAMS.getDescripcion());
			contacto.setMensaje(Mensajes.CONTACTO_CORREO_NULL.getDescripcion());
			ret = false;
		}
		if (contacto.getContrasenia() == null || contacto.getContrasenia().isEmpty()) {
			log.error("LA contraseña del contacto esta vacia");
			contacto.setCodRetorno(CodRetorno.FALTAN_PARAMS.getDescripcion());
			contacto.setMensaje(Mensajes.CONTACTO_CONTRASENIA_NULL.getDescripcion());
			ret = false;
		}
		if (contacto.getNombre() == null || contacto.getNombre().isEmpty()) {
			log.error("El nombre del contato esta vacio");
			contacto.setCodRetorno(CodRetorno.FALTAN_PARAMS.getDescripcion());
			contacto.setMensaje(Mensajes.CONTACTO_NOMBRE_NULL.getDescripcion());
			ret = false;
		}
		if (leeId) {
			if (contacto.getIdContacto() == null) {
				log.error("Id de contacto esta vacio");
				contacto.setCodRetorno(CodRetorno.FALTAN_PARAMS.getDescripcion());
				contacto.setMensaje(Mensajes.CONTACTO_NULL.getDescripcion());
				ret = false;
			}
		}
		if (ret) {
			contacto.setCodRetorno(CodRetorno.CEROS.getDescripcion());
		}
		return contacto;
	}

}
