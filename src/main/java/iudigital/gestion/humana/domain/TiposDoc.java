package iudigital.gestion.humana.domain;

/**
 *
 * @author jaime
 */
public class TiposDoc {
    private int Tid;
    private String  name;

    public int getTid() {
        return Tid;
    }

    public void setTid(int Tid) {
        this.Tid = Tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    @Override
    public String toString() {
        return  name;
    }
    
    
}
