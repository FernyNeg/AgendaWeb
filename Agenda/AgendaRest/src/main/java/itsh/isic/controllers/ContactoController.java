package itsh.isic.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import itsh.isic.constantes.UrlConstantes;
import itsh.isic.excepsiones.ServiciosException;
import itsh.isic.models.ConsultaList;
import itsh.isic.models.agenda.Contacto;
import itsh.isic.service.ContactoIService;

@RestController
@RequestMapping("/controller/contacto")
public class ContactoController {

	private static final Logger log = LoggerFactory.getLogger(ContactoController.class);

	@Autowired
	private ContactoIService contactoCont;

	@PostMapping(value = UrlConstantes.LEER_CONTACTOS)
	@ResponseBody
	public ConsultaList<Contacto> leerContactos(@RequestBody ConsultaList<Contacto> reqNombre) throws ServiciosException {
		log.info("se recibe consulta de contactos: " + reqNombre.getParam());
		return this.contactoCont.leerContactos(reqNombre);
	}
}
