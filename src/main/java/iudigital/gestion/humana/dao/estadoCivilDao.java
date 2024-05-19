package iudigital.gestion.humana.dao;
import iudigital.gestion.humana.domain.EstadoCivil;
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
public class estadoCivilDao {
    private static final String GET_ECIVIL = "select * from estado_civil;";
    
    public List<EstadoCivil> getEcivil() throws SQLException {

        Connection connection = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultset = null;
        List<EstadoCivil> estadosCivil = new ArrayList<>();

        try {

            connection = new ConecctionUtil().getConnection();
            preparedstatement = connection.prepareStatement(GET_ECIVIL);
            resultset = preparedstatement.executeQuery();

            while (resultset.next()) {
                EstadoCivil Ecivil = new EstadoCivil();
                Ecivil.setEid(resultset.getInt("id_estado_civil"));
                Ecivil.setName(resultset.getString("estado_civil"));
                estadosCivil.add(Ecivil);
            }

            return estadosCivil;
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
