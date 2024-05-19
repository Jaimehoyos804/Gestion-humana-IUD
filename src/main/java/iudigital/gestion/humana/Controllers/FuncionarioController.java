package iudigital.gestion.humana.Controllers;
import iudigital.gestion.humana.dao.FuncionarioDao;
import iudigital.gestion.humana.domain.Funcionario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jaime
 */
public class FuncionarioController {
    private FuncionarioDao funcionarioDao;
    
    public FuncionarioController(){
        funcionarioDao = new FuncionarioDao();
        
    }
    public List<Funcionario> obtenerFuncionarios() throws SQLException {
        
        return funcionarioDao.getFuncionario();
    }
    public void create(Funcionario funcionario) throws SQLException {
        funcionarioDao.createFuncionario(funcionario);
    }
    
    public Funcionario getFuncionario(int id) throws SQLException {
        return funcionarioDao.getFuncionarioBYID(id);
    }
    
    public void updateFuncionario(int id, Funcionario funcionario) throws SQLException {
      
       funcionarioDao.UpdateFuncionario(id, funcionario);
    }
     public void deleteFuncionario(int id) throws SQLException {
        funcionarioDao.deleteFuncionario(id);
    }
    
}
