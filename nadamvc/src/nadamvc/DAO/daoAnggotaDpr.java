package nadamvc.DAO;

import nadamvc.koneksi.koneksi;
import nadamvc.model.anggotadpr;
import nadamvc.DAOImplement.implementAnggotaDpr;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class daoAnggotaDpr implements implementAnggotaDpr {
    Connection connection;
    final String insert = "INSERT INTO tblanggotadpr (nama, jabatan, jk, alamat) VALUES (?, ?, ?,?);";
    final String update = "UPDATE tblanggotadpr set nama=?, jabatan=?, jk=?, alamat=? where id=? ;";
    final String delete = "DELETE FROM tblanggotadpr where id=? ;";
    final String select = "SELECT * FROM tblanggotadpr;";
    final String carinama = "SELECT * FROM tblanggotadpr where nama like ?";
   
    public daoAnggotaDpr() {
        connection = koneksi.connection();
    }

    public void insert(anggotadpr b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, b.getNama());
            statement.setString(2, b.getJabatan());
            statement.setString(3, b.getJk());
            statement.setString(4, b.getAlamat());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                b.setId(rs.getInt(1));
            }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void update(anggotadpr b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getNama());
            statement.setString(2, b.getJabatan());
            statement.setString(3, b.getJk());
            statement.setString(4, b.getAlamat());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void delete(int id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<anggotadpr> getALL() {
        List<anggotadpr> lb = null;
        try {
            lb = new ArrayList<anggotadpr>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                anggotadpr b = new anggotadpr();
                b.setNama(rs.getString("nama"));
                b.setJabatan(rs.getString("jabatan"));
                b.setJk(rs.getString("jk"));
                b.setAlamat(rs.getString("alamat"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoAnggotDpr.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lb;
    }

    public List<anggotadpr> getCariNama(String nama) {
        List<anggotadpr> lb = null;
        try {
            lb = new ArrayList<anggotadpr>();
            PreparedStatement st = connection.prepareStatement(carinama);
            st.setString(1, "%" + nama + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                anggotadpr b = new anggotadpr();
                b.setNama(rs.getString("nama"));
                b.setJabatan(rs.getString("jabatan"));
                b.setJk(rs.getString("jk"));
                b.setAlamat(rs.getString("alamat"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoAnggotaDpr.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }
}



