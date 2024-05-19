package iudigital.gestion.humana.Controllers;

import iudigital.gestion.humana.dao.tipoDocDao;
import iudigital.gestion.humana.domain.TiposDoc;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jaime
 */
public class tipoDocController {

    private tipoDocDao tiposDocDao;

    public tipoDocController() {
        tiposDocDao = new tipoDocDao();

    }

    public List<TiposDoc> getDoc() throws SQLException {
        return tiposDocDao.gettiposDoc();
    }
}
