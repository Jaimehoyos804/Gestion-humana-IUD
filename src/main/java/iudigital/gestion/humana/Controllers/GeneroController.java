package iudigital.gestion.humana.Controllers;

import iudigital.gestion.humana.dao.GeneroDao;
import iudigital.gestion.humana.domain.Genero;
import java.sql.SQLException;
import java.util.List;

public class GeneroController {
    private GeneroDao generoDao;

    public GeneroController() {
        generoDao = new GeneroDao();

    }
    
    public List<Genero> getGenres() throws SQLException {
        return generoDao.getGeneros();
    }
    
}
