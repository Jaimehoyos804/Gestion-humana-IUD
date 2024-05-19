package iudigital.gestion.humana.presentation;

import iudigital.gestion.humana.Controllers.FuncionarioController;
import iudigital.gestion.humana.domain.Funcionario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jaime
 */
public class GestionHumanaApp {
    public static void obtenerfuncionarios(FuncionarioController funcionarioController){
        try {
            List<Funcionario> funcionarios = funcionarioController.obtenerFuncionarios();
            
            if(funcionarios.isEmpty()){
                System.out.println("No hay funcionarios disponibles");
            }else{
                funcionarios.forEach(funcionario -> {
                    System.out.println("Id: " + funcionario.getId());
                    System.out.println("Tipo Documento: " + funcionario.getTipoDocumento());
                    System.out.println("Nro Documento: " + funcionario.getNroDocumento());
                    System.out.println("Nombres: " + funcionario.getNombres());
                    System.out.println("Apellidos: " + funcionario.getApellidos());
                    System.out.println("Estado Civil: " + funcionario.getEstadoCivil());
                    System.out.println("Genero: " + funcionario.getGenero());
                    System.out.println("Dir: " + funcionario.getDireccion());
                    System.out.println("Telefon: " + funcionario.getTelefono());
                    System.out.println("Fecha Nacimiento: " + funcionario.getFechaNacimiento());
                    System.out.println("--------------------------------------");
                });
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        
        FuncionarioController funcionarioController = new FuncionarioController();
        obtenerfuncionarios(funcionarioController);
        //System.out.println("Hello World!");
    }
}
