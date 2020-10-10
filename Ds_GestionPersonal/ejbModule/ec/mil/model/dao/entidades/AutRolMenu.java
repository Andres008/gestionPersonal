package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the aut_rol_menu database table.
 * 
 */
@Entity
@Table(name="aut_rol_menu")
@NamedQuery(name="AutRolMenu.findAll", query="SELECT a FROM AutRolMenu a")
public class AutRolMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AUT_ROL_MENU_ID_GENERATOR", sequenceName="SEQ_AUT_ROL_MENU")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUT_ROL_MENU_ID_GENERATOR")
	private long id;

	//bi-directional many-to-one association to AutMenu
	@ManyToOne
	@JoinColumn(name="id_menu")
	private AutMenu autMenu;

	//bi-directional many-to-one association to AutRole
	@ManyToOne
	@JoinColumn(name="id_rol")
	private AutRole autRole;

	public AutRolMenu() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AutMenu getAutMenu() {
		return this.autMenu;
	}

	public void setAutMenu(AutMenu autMenu) {
		this.autMenu = autMenu;
	}

	public AutRole getAutRole() {
		return this.autRole;
	}

	public void setAutRole(AutRole autRole) {
		this.autRole = autRole;
	}

}