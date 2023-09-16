package itsh.isic.dao;

import itsh.isic.excepsiones.ServiciosException;
import itsh.isic.models.ConsultaList;
import itsh.isic.models.agenda.Contacto;

public interface ContactoDao {

	ConsultaList<Contacto> getContactosList(ConsultaList<Contacto> reqNombre) throws ServiciosException;

	Contacto creaContacto(Contacto reqContacto) throws ServiciosException;

	// Modificar

	// Borrar

	// LeerContactoEspecificoPorId

}
