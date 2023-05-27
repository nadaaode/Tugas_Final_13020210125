package nadamvc.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class tableModelAnggotaDpr extends AbstractTableModel{
     List<anggotadpr> lb;

    public tableModelanggotadpr(List<anggotadpr> lb) {
        this.lb = lb;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }


    public int getRowCount() {
        return lb.size();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nama";
            case 1:
                return "Jabatan";
            case 2:
                return "JenisKelamin";
            case 3:
                return "Alamat";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return lb.get(row).getNama();
            case 1:
                return lb.get(row).getJabatan();
            case 2:
                return lb.get(row).getJk();
            case 3:
                return lb.get(row).getAlamat();
            default:
                return null;
        }
    }
}

