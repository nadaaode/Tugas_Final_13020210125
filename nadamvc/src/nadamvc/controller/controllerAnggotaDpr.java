package nadamvc.controller;

import nadamvc.DAO.daoAnggotaDpr;
import nadamvc.DAOImplement.implementAnggotaDpr;
import nadamvc.model.anggotadpr;
import nadamvc.model.tableModelAnggotaDpr;
import nadamvc.view.FormAnggotaDpr;
import java.util.List;
import javax.swing.JOptionPane;

public class controllerAnggotaDpr {
    FormAnggotaDpr frame;
    implementAnggotaDpr implAnggotaDpr;
    List<anggotadpr> lb;

    public controllerAnggotaDpr(FormAnggotaDpr frame) {
        this.frame = frame;
        implAnggotaDpr = new daoAnggotaDpr();
        lb = implAnggotaDpr.getALL();
    }

    //mengosongkan field
    public void reset() {
        frame.getTxtNama().setText("");
        frame.getTxtJabatan().setText("");
        frame.getTxtJk().setSelectedItem("");
        frame.getTxtAlamat().setText("");
       
    }

    //menampilkan data ke dalam tabel
    public void isiTable() {
        lb = implAnggotaDpr.getALL();
        tableModelAnggotaDpr tmb = new tableModelAnggotaDpr(lb);
        frame.getTabelData().setModel(tmb);
    }

    //merupakan fungsi untuk menampilkan data yang dipilih dari tabel
    public void isiField(int row) {
        frame.getTxtNama().setText(lb.get(row).getNama());
        frame.getTxtJabatan().setText(lb.get(row).getJabatan());
        frame.getTxtJk().setSelectedItem(lb.get(row).getJk());
        frame.getTxtAlamat().setText(lb.get(row).getAlamat());
    }

    //merupakan fungsi untuk insert data berdasarkan inputan user dari textfield di frame
    public void insert() {
       
      if (!frame.getTxtNama().getText().trim().isEmpty()& !frame.getTxtNama().getText().trim().isEmpty()) {
         
        anggotadpr b = new anggotadpr();
        b.setNama(frame.getTxtNama().getText());
        b.setJabatan(frame.getTxtJabatan().getText());
        b.setJk(frame.getTxtJk().getSelectedItem().toString());
        b.setAlamat(frame.getTxtAlamat().getText());

        implAnggotaDpr.insert(b);
        JOptionPane.showMessageDialog(null, "Simpan Data sukses");
       
        } else {
            JOptionPane.showMessageDialog(frame, "Data Tidak Boleh Kosong");
        }
    }

    //berfungsi untuk update data berdasarkan inputan user dari textfield di frame
    public void update() {
   if (!frame.getTxtID().getText().trim().isEmpty()) {
            
        anggota dpr b = new anggotadpr();
        b.setNama(frame.getTxtNama().getText());
        b.setJabatan(frame.getTxtJabatan().getText());
        b.setJk(frame.getTxtJk().getSelectedItem().toString());
        b.setAlamat(frame.getTxtAlamat().getText());
        implAnggotaDpr.update(b);
       
        JOptionPane.showMessageDialog(null, "Update Data  sukses");
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih data yang akan di ubah");
        }
    }

    //berfungsi menghapus data yang dipilih
    public void delete() {
        if (!frame.getTxtID().getText().trim().isEmpty()) {
            int id = Integer.parseInt(frame.getTxtID().getText());
            implAnggotaDpr.delete(id);
           
            JOptionPane.showMessageDialog(null, "Hapus Data  sukses");
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih data yang akan di hapus");
        }
    }

    public void isiTableCariNama() {
        lb = implAnggotaDpr.getCariNama(frame.getTxtCariNama().getText());
        tableModelAnggotaDpr tmb = new tableModelAnggotaDpr(lb);
        frame.getTabelData().setModel(tmb);
    }

    public void carinama() {
        if (!frame.getTxtCariNama().getText().trim().isEmpty()) {
            implAnggotaDpr.getCariNama(frame.getTxtCariNama().getText());
            isiTableCariNama();
        } else {
            JOptionPane.showMessageDialog(frame, "SILAHKAN PILIH DATA");
        }
    }
}
