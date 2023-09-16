package itsh.isic.daoimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import itsh.isic.constantes.Constants;
import itsh.isic.conexion.ConnectDao;
import itsh.isic.dao.ContactoDao;
import itsh.isic.enums.CodRetorno;
import itsh.isic.enums.Mensajes;
import itsh.isic.enums.Numeros;
import itsh.isic.enums.tables.DBEnum;
import itsh.isic.enums.tables.TblConsContacto;
import itsh.isic.excepsiones.ServiciosException;
import itsh.isic.models.ConsultaList;
import itsh.isic.models.agenda.Contacto;

@Repository
public class ContactoDaoImpl implements ContactoDao {

	// Creado para imprimir los procesos de cada
	private static final Logger log = LoggerFactory.getLogger(ContactoDaoImpl.class);

	@Autowired
	private ConnectDao dao;

	@Override
	public ConsultaList<Contacto> getContactosList(ConsultaList<Contacto> reqNombre) throws ServiciosException {
		log.info("Inicia consulta de contactos ");
		ConsultaList<Contacto> res = new ConsultaList<Contacto>();
		MapSqlParameterSource namedParameters = new MapSqlParameterSource("nombre",
				reqNombre.getParam() == null ? "" : reqNombre.getParam());
		try {
			List<Contacto> ret = this.dao.getNamedjdbcTemplate().query(this.genQryGetContactos(), namedParameters,
					new BeanPropertyRowMapper<Contacto>(Contacto.class));
			res.setList(ret);
			res.setCodRetorno(CodRetorno.CEROS.getDescripcion());
			res.setMensaje(Mensajes.OP_COMPLETADA.getDescripcion());
		} catch (EmptyResultDataAccessException e) {
			log.error(String.format("No se encontraron datos de la consulta: %s: %s", reqNombre, e.getMessage()));
			res.setCodRetorno(CodRetorno.CEROS.getDescripcion());
			res.setMensaje(Mensajes.EMPTY_RES_LIST.getDescripcion());
		} catch (Exception e) {
			log.error(String.format("Error al obtener los datos de la consulta :%s", e.getMessage()));
			res.setCodRetorno(CodRetorno.FALLO_SERVER.getDescripcion());
			res.setMensaje(Mensajes.FALLO_SERVER.getDescripcion());
		}
		return null;
	}

	@Override
	public Contacto creaContacto(Contacto contacto) throws ServiciosException {
		log.info("Inicia insersion de contacto");
		BeanPropertySqlParameterSource paramContacto = new BeanPropertySqlParameterSource(contacto);
		try {
			KeyHolder key = new GeneratedKeyHolder();
			log.debug(this.genQrySetContacto());
			this.dao.getNamedjdbcTemplate().update(this.genQrySetContacto(), paramContacto, key,
					new String[] { TblConsContacto.COL_IDCONTACTO.getDescripcion() });
			if (key.getKey().intValue() != 0) {
				log.info("Se ha agregado correctamente el usuario");
				contacto.setIdContacto(key.getKey().intValue());
				contacto.setCodRetorno(CodRetorno.CEROS.getDescripcion());
				contacto.setMensaje(Mensajes.OP_COMPLETADA.getDescripcion());
			} else {
				log.error("Fallo en la insersion del contacto: " + key.getKey().intValue());
				contacto.setCodRetorno(CodRetorno.FALLO_SERVER.getDescripcion());
				contacto.setMensaje(Mensajes.FALLO_SERVER.getDescripcion());
			}
		} catch (DuplicateKeyException dke) {
			log.error("error en la insersion del contacto: correo existente en los registros " + dke.getMessage());
			contacto.setCodRetorno(CodRetorno.PARAMS_REPETIDOS.getDescripcion());
			if (dke.getMessage().contains("Correo_UNIQUE")) {
				contacto.setMensaje(Mensajes.CORREO_REPETIDO.getDescripcion());
			} else if (dke.getMessage().contains("Usuario_UNIQUE")) {
				contacto.setMensaje(Mensajes.USUARIO_REPETIDO.getDescripcion());
			} else {
				contacto.setMensaje(dke.getMessage());
			}
		} catch (NullPointerException e) {
			log.error("error en la insersion del contacto" + e);
			contacto.setCodRetorno(CodRetorno.FALLO_SERVER.getDescripcion());
			contacto.setMensaje(Mensajes.FALLO_SERVER.getDescripcion());
		} catch (Exception e) {
			log.error("error en la insersion del contacto" + e);
			contacto.setCodRetorno(CodRetorno.FALLO_SERVER.getDescripcion());
			contacto.setMensaje(Mensajes.FALLO_SERVER.getDescripcion());
		}
		return contacto;
	}

	private String genQrySetContacto() {
		StringBuilder qry = new StringBuilder();
		qry.append(" insert into ").append(DBEnum.CONTACTO.getDescripcion());
		qry.append(" ( ");
		qry.append(TblConsContacto.COL_IDCONTACTO.getDescripcion() + Constants.COMMA);
		qry.append(TblConsContacto.COL_NOMBRE.getDescripcion() + Constants.COMMA);
		qry.append(TblConsContacto.COL_PATERNO.getDescripcion() + Constants.COMMA);
		qry.append(TblConsContacto.COL_MATERNO.getDescripcion() + Constants.COMMA);
		qry.append(TblConsContacto.COL_USUARIO.getDescripcion() + Constants.COMMA);
		qry.append(TblConsContacto.COL_CORREO.getDescripcion() + Constants.COMMA);
		qry.append(TblConsContacto.COL_CONTRASENIA.getDescripcion() + Constants.COMMA);
		qry.append(TblConsContacto.COL_DIRECCION.getDescripcion() + Constants.COMMA);
		qry.append(TblConsContacto.COL_PAIS.getDescripcion() + Constants.COMMA);
		qry.append(TblConsContacto.COL_CIUDAD.getDescripcion() + Constants.COMMA);
		qry.append(TblConsContacto.COL_CODPOSTAL.getDescripcion() + Constants.COMMA);
		qry.append(TblConsContacto.COL_NOTAS.getDescripcion());
		qry.append(") ");
		qry.append(" values ( ").append(Numeros.CERO.getNumero()).append(", ");
		qry.append(":nombre,:aPaterno,:aMaterno,:usuario,:correo,:contrasenia,:direccion,:pais,:ciudad,:codPostal,:notas");
		qry.append(")");
		return qry.toString();
	}

	private String genQryGetContactos() {
		StringBuilder qry = new StringBuilder();
		qry.append(" SELECT ");
		qry.append(TblConsContacto.COL_IDCONTACTO.getDescripcion() + Constants.COMMA);
		qry.append(TblConsContacto.COL_NOMBRE.getDescripcion() + Constants.COMMA);
		qry.append(TblConsContacto.COL_DIRECCION.getDescripcion() + Constants.COMMA);
		qry.append(TblConsContacto.COL_CORREO.getDescripcion());
		qry.append(" FROM ").append(DBEnum.CONTACTO.getDescripcion());
		qry.append(" WHERE ");
		qry.append(TblConsContacto.COL_NOMBRE.getDescripcion());
		qry.append(" LIKE CONCAT('%',");
		qry.append(":nombre,");
		qry.append("'%')");
		return qry.toString();
	}

}
