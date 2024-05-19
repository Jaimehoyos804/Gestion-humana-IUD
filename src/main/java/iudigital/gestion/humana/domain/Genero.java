package iudigital.gestion.humana.domain;

/**
 *
 * @author jaime
 */
public class Genero {
    private int Gid;
    private String  name;

    public int getGid() {
        return Gid;
    }

    public void setGid(int Gid) {
        this.Gid = Gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return  name ;
    }
    
      
    
    
}
