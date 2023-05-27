package nadamvc.DAOImplement;

import java.util.List;
import nadamvc.model.*;

public interface implementAnggotaDpr {
    public void insert(anggotadpr b);

    public void update(anggotadpr b);

    public void delete(int id);

    public List<anggotadpr> getALL();

    public List<anggotadpr> getCariNama(String nama);
}
