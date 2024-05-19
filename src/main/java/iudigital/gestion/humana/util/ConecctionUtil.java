package iudigital.gestion.humana.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author JAIME
 */
public class ConecctionUtil {
    //Variables de coneccion

    private static final String URL = "jdbc:mysql://localhost:3308/gestion_humana_bd";
    private static final String USER = "root";
    private static final String PASS = "Admin";

    //Metodo de coneccion
    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL, USER, PASS);
    }
}


  