package itsh.isic.service;

import itsh.isic.excepsiones.ServiciosException;
import itsh.isic.models.ConsultaList;
import itsh.isic.models.agenda.Contacto;

public interface ContactoIService {

	ConsultaList<Contacto> leerContactos(ConsultaList<Contacto> reqNombre) throws ServiciosException;

	Contacto creaContacto(Contacto reqContacto) throws ServiciosException;

	// Modificar

	// Borrar

	// LeerContactoEspecificoPorId

}
