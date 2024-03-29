package model;

import database.Connecter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entity.qlSach;
import model.entity.qlTraSach;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerBooksReturnAccessObject implements DataAccessObject<qlTraSach> {

    @Override
    public ObservableList getList() {
        ObservableList<qlTraSach> ds = FXCollections.observableArrayList();
        try {
            Connecter cn = Connecter.getInstance();
            String sql = "select * from qltrasach";
            ResultSet rs = cn.getStatement().executeQuery(sql);
            while (rs.next()) {
                Integer sttTS = rs.getInt("sttT");
                String maKHT = rs.getString("maKhT");
                String tenKHN  = rs.getString("tenKhT");
                Integer sdt = rs.getInt("sdtkhT");
                String diachi = rs.getString("diaChiNT");
                String idb = rs.getString("maS");
                String nameB = rs.getString("tenS");
                Integer amount = rs.getInt("soLuong");
                Date date = rs.getDate("ngayTra");
                qlTraSach qls = new qlTraSach(sttTS,maKHT,tenKHN,sdt,diachi,idb,nameB,amount,date);
                ds.add(qls);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("ERROR");
        }
        return ds;
    }

    @Override
    public boolean create(qlTraSach qlTraSach) throws SQLException {
        try {
            Connecter cn = Connecter.getInstance();
            String sql = "insert into  qltrasach( maKhT,tenKhT,sdtkhT,diaChiNT, maS,tenS, soLuong, ngayTra)" +"value('"+qlTraSach.getIdKT()+"','"+qlTraSach.getNameKT()+"',"+qlTraSach.getPhoneKT()+",'"+qlTraSach.getAdressKT()+"','"+qlTraSach.getIdB()+"','"+qlTraSach.getNameB()+"',"+qlTraSach.getSoluongT()+",'"+qlTraSach.getNgayT()+"')";
            cn.getStatement().executeUpdate(sql);
            return false;
        }catch (Exception e){
            System.out.println("\"Kết Nối Thất Bại\"");
            return true;
        }
    }

    @Override
    public boolean create(qlSach qls) throws SQLException {
        return false;
    }

    @Override
    public boolean update(qlTraSach qlTraSach) {
        try {
            Connecter c = Connecter.getInstance();
            String sql = "update qlTraSach set tenKhT ='" + qlTraSach.getNameKT() + "' ,sdtKhT ='" + qlTraSach.getPhoneKT() + "',diaChiNT = '" + qlTraSach.getAdressKT() + "',tenS = '" + qlTraSach.getNameB() + "',soLuong = " + qlTraSach.getSoluongT() + ",ngayTra = '" + qlTraSach.getNgayT() + "' where maKhT= '"+ qlTraSach.getIdKT() +"'";
            c.statement.execute(sql);
            return false;
        } catch (SQLException e) {
            return true;
        }
    }


    @Override
    public boolean delete(qlTraSach qlTS) {
        try {
            Connecter c = Connecter.getInstance();
            String sql = "DELETE FROM `qltrasach` WHERE maKhT='"+qlTS.getIdKT()+"'";
            c.getStatement().executeUpdate(sql);
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Lỗi");
            return true;
        }

    }


}
