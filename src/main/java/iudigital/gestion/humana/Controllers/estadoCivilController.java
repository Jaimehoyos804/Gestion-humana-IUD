package iudigital.gestion.humana.Controllers;

import iudigital.gestion.humana.dao.estadoCivilDao;
import iudigital.gestion.humana.domain.EstadoCivil;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jaime
 */
public class estadoCivilController {

    private estadoCivilDao estadocivilDao;

    public estadoCivilController() {
        estadocivilDao = new estadoCivilDao();

    }

    public List<EstadoCivil> getEstados() throws SQLException {
        return estadocivilDao.getEcivil();
    }

}
