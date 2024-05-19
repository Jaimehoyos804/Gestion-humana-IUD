package iudigital.gestion.humana.dao;

import iudigital.gestion.humana.domain.Funcionario;
import iudigital.gestion.humana.util.ConecctionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaime
 */
public class FuncionarioDao {

    private static final String GET_FUNCIONARIOS
            = "SELECT f.id, td.tipo_documento AS tipo_documento, "
            + "f.documento, f.nombres, f.apellidos, "
            + "ec.estado_civil AS estado_civil, "
            + "g.genero AS genero, "
            + "f.direccion, f.telefono, f.fecha_nacimiento "
            + "FROM Funcionarios f "
            + "JOIN Tipos_Doc td ON f.id_tipo_documento = td.id_tipo_documento "
            + "JOIN estado_civil ec ON f.id_estado_civil = ec.id_estado_civil "
            + "JOIN generos g ON f.id_genero = g.id_genero";

    private static final String CREATE_FUNCIONARIO = "INSERT INTO Funcionarios "
            + "(id_tipo_documento,documento,nombres,apellidos,id_estado_civil,"
            + "id_genero,direccion,telefono,fecha_nacimiento)"
            + "VALUES (?,?,?,?,?,?,?,?,?)";

    private static final String GET_FUNCIONARIO_BY_ID = "SELECT f.id, td.tipo_documento,"
            + "f.documento,f.nombres,f.apellidos,ec.estado_civil,g.genero,f.direccion,"
            + "f.telefono,f.fecha_nacimiento FROM Funcionarios f JOIN Tipos_Doc td "
            + "ON f.id_tipo_documento = td.id_tipo_documento JOIN estado_civil ec "
            + "ON f.id_estado_civil = ec.id_estado_civil JOIN  generos g "
            + "ON f.id_genero = g.id_genero WHERE f.id = ?";

    private static final String UPDATE_FUNCIONARIO = "UPDATE Funcionarios SET id_tipo_documento = ?,"
            + "documento = ?, nombres = ?, apellidos = ?,id_estado_civil = ?,"
            + "id_genero = ?,direccion = ?,telefono = ?,fecha_nacimiento = ? "
            + "WHERE id = ?";

    private static final String DELETE_FUNCIONARIO = "DELETE FROM FUNCIONARIOS WHERE ID=?";

    public List<Funcionario> getFuncionario() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultset = null;
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            connection = new ConecctionUtil().getConnection();
            preparedstatement = connection.prepareStatement(GET_FUNCIONARIOS);
            resultset = preparedstatement.executeQuery();

            while (resultset.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultset.getInt("id"));
                funcionario.setTipoDocumento(resultset.getString("tipo_documento"));
                funcionario.setNroDocumento(resultset.getString("documento"));
                funcionario.setNombres(resultset.getString("nombres"));
                funcionario.setApellidos(resultset.getString("apellidos"));
                funcionario.setEstadoCivil(resultset.getString("estado_civil"));
                funcionario.setGenero(resultset.getString("genero"));
                funcionario.setDireccion(resultset.getString("direccion"));
                funcionario.setTelefono(resultset.getString("telefono"));
                funcionario.setFechaNacimiento(resultset.getString("fecha_nacimiento"));
                funcionarios.add(funcionario);
            }

            return funcionarios;
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

    public void createFuncionario(Funcionario funcionario) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = new ConecctionUtil().getConnection();
            preparedStatement = connection.prepareCall(CREATE_FUNCIONARIO);
            preparedStatement.setString(1, funcionario.getTipoDocumento());
            preparedStatement.setString(2, funcionario.getNroDocumento());
            preparedStatement.setString(3, funcionario.getNombres());
            preparedStatement.setString(4, funcionario.getApellidos());
            preparedStatement.setString(5, funcionario.getEstadoCivil());
            preparedStatement.setString(6, funcionario.getGenero());
            preparedStatement.setString(7, funcionario.getDireccion());
            preparedStatement.setString(8, funcionario.getTelefono());
            preparedStatement.setString(9, funcionario.getFechaNacimiento());
            preparedStatement.executeUpdate();
        } finally {

            if (connection != null) {
                connection.close();
            }
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

    }

    public Funcionario getFuncionarioBYID(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Funcionario funcionario = null;

        try {
            connection = new ConecctionUtil().getConnection();
            preparedStatement = connection.prepareStatement(GET_FUNCIONARIO_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                funcionario.setId(resultSet.getInt("id"));
                funcionario.setTipoDocumento(resultSet.getString("tipo_documento"));
                funcionario.setNroDocumento(resultSet.getString("documento"));
                funcionario.setNombres(resultSet.getString("nombres"));
                funcionario.setApellidos(resultSet.getString("apellidos"));
                funcionario.setEstadoCivil(resultSet.getString("estado_civil"));
                funcionario.setGenero(resultSet.getString("genero"));
                funcionario.setDireccion(resultSet.getString("direccion"));
                funcionario.setTelefono(resultSet.getString("telefono"));
                funcionario.setFechaNacimiento(resultSet.getString(("fecha_nacimiento")));

            }
            return funcionario;
        } finally {

            if (resultSet != null) {
                resultSet.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    public void UpdateFuncionario(int id, Funcionario funcionario) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = new ConecctionUtil().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_FUNCIONARIO);
            preparedStatement.setString(1, funcionario.getTipoDocumento());
            preparedStatement.setString(2, funcionario.getNroDocumento());
            preparedStatement.setString(3, funcionario.getNombres());
            preparedStatement.setString(4, funcionario.getApellidos());
            preparedStatement.setString(5, funcionario.getEstadoCivil());
            preparedStatement.setString(6, funcionario.getGenero());
            preparedStatement.setString(7, funcionario.getDireccion());
            preparedStatement.setString(8, funcionario.getTelefono());
            preparedStatement.setString(9, funcionario.getFechaNacimiento());
            preparedStatement.executeUpdate();
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    public void deleteFuncionario(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = new ConecctionUtil().getConnection();
            preparedStatement = connection.prepareStatement(DELETE_FUNCIONARIO);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

}
