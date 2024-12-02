import java.sql.*;

public class Transaction02 {
    public static void main(String[] args) throws SQLException {
        //n02-ADIM : bağlantıyı olusturma : DB URL
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_nt","techpro","password");

        //n03-ADIM : Statement olusturma
        Statement st=con.createStatement();

        st.execute("CREATE TABLE IF NOT EXISTS hesaplar (hesap_no INT UNIQUE, isim VARCHAR(50), bakiye REAL)");

        String sql1 = "INSERT INTO hesaplar VALUES (?,?,?) ";
        PreparedStatement prst1 = con.prepareStatement(sql1);
        prst1.setInt(1, 1234);
        prst1.setString(2,"Fred");
        prst1.setDouble(3,9000);
        prst1.executeUpdate();

        prst1.setInt(1, 5678);
        prst1.setString(2,"Barnie");
        prst1.setDouble(3,6000);
        prst1.executeUpdate();

        //TASK: hesap no:1234 ten hesap no:5678 e 1000$ para transferi olsun
        try {


            String sql = "Update hesaplar set bakiye=bakiye+? where hesap_no=?";
            PreparedStatement prst2 = con.prepareStatement(sql);
            //1-işlem:gonderen hesabın bakiyesini silelim
            prst2.setDouble(1, -1000);
            prst2.setInt(2, 1234);
            prst2.executeUpdate();

            //sistemde hata oluştuğunu varsayalım
            if (true) {
                throw new RuntimeException();
            }

            //2-işlem alıcı hesabın bakiyesini ekleyelim
            prst2.setDouble(1, 1000);
            prst2.setInt(2, 5678);
            prst2.executeUpdate();

            prst2.close();
        }catch (Exception e){
            System.out.println("Sistemde bir arıza oluştu.");

            e.printStackTrace();
        }finally {
            st.close();
            prst1.close();
            con.close();
        }






    }
}