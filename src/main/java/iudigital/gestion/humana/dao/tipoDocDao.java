package iudigital.gestion.humana.dao;

import iudigital.gestion.humana.domain.TiposDoc;
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
public class tipoDocDao {
     private static final String GET_tipoDoc = "select * from Tipos_Doc";
     public List<TiposDoc> gettiposDoc() throws SQLException {

        Connection connection = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultset = null;
        List<TiposDoc> tipoDoc = new ArrayList<>();

        try {

            connection = new ConecctionUtil().getConnection();
            preparedstatement = connection.prepareStatement(GET_tipoDoc);
            resultset = preparedstatement.executeQuery();

            while (resultset.next()) {
                TiposDoc tDoc = new TiposDoc();
                tDoc.setTid(resultset.getInt("id_tipo_documento"));
                tDoc.setName(resultset.getString("tipo_documento"));
                tipoDoc.add(tDoc);
            }

            return tipoDoc;
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
