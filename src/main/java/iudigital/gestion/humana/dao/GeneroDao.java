package iudigital.gestion.humana.dao;
import iudigital.gestion.humana.domain.Genero;
import iudigital.gestion.humana.util.ConecctionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaime
 */
public class GeneroDao {
    private static final String GET_GENEROS = "select * from generos";
    
    public List<Genero> getGeneros() throws SQLException {

        Connection connection = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultset = null;
        List<Genero> genero = new ArrayList<>();

        try {

            connection = new ConecctionUtil().getConnection();
            preparedstatement = connection.prepareStatement(GET_GENEROS);
            resultset = preparedstatement.executeQuery();

            while (resultset.next()) {
                Genero generos = new Genero();
                generos.setGid(resultset.getInt("id_genero"));
                generos.setName(resultset.getString("genero"));
                genero.add(generos);
            }

            return genero;
        } finally {

            if (connection != null) {
                connection.close();
            }

            if (preparedstatement != null) {
                preparedstatement.close();
            }

            if (resultset != null) {
                resultset.close();
            }
        }

    }
  
}
