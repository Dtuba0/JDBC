import java.sql.*;

public class ExecuteUpdate02 {
    public static void main(String[] args) throws SQLException {
        //n02-ADIM : bağlantıyı olusturma : DB URL
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_nt","techpro","password");

        //n03-ADIM : Statement olusturma
        Statement statement=con.createStatement();

        //ÖRNEK2:developers tablosuna yeni bir developer ekleyiniz.
//       String sql1="Insert Into developers(name,salary,prog_lang) "+
//                    "Values('Husamettin',5000,'Java')";
//       int inserted=statement.executeUpdate(sql1);
//       System.out.println("Eklenen veri sayisi : "+inserted);

        //tüm kayıtları görelim
        ResultSet rs=statement.executeQuery("select * from developers");
        while (rs.next()){
            System.out.println("id : "+rs.getInt("id")+"isim : "+rs.getString("name")+" maas : "+rs.getDouble("salary"));
        }

        //ÖRNEK3:developers tablosundan id'si 9 olanı siliniz.
        //   int deleted=statement.executeUpdate("Delete from developers where id=9");
        //   System.out.println("silinen kayıt sayısı : "+deleted);

        //ÖRNEK4:developers tablosundan id'si 14 den büyük olanları siliniz.
        int deleted2=statement.executeUpdate("delete from developers where id>14");
        System.out.println("silinen kayıt sayısı : "+deleted2);

        //tüm kayıtları görelim
        ResultSet rs2=statement.executeQuery("select * from developers order by id");
        while (rs2.next()){
            System.out.println("id : "+rs2.getInt("id")+"isim : "+rs2.getString("name")+" maas : "+rs2.getDouble("salary"));
        }




    }
}
