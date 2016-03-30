package Database;

import Logic.User;

import java.sql.*;

/**
 * Created by Devene on 3/23/2016.
 */
public class UserGateway {
    private Connection con;
    private Statement st;
    private ResultSet rez;
    private PreparedStatement pst;

    public void update(String owner,String adr,String tel,String pass){
        Connect();
        try{
            st = con.createStatement();
            int res = st.executeUpdate("update users set adr=\'"+adr+"\' ,tel=\'"+tel+"\' ,pass=\'"+pass+"\' where nume=\'"+owner+"\'");
        }catch (Exception ex) {

        }
    }
    public void updateData(String owner,String adr,String tel){
        Connect();
        try{
            st = con.createStatement();
            int res = st.executeUpdate("update users set adr=\'"+adr+"\' ,tel=\'"+tel+"\' where nume=\'"+owner+"\'");
        }catch (Exception ex) {

        }
    }
    public void insert(User cont){
        try{
            User ver = find(cont.getNume());
            Connect();
            st = con.createStatement();
            if(ver==null) {
                int res = st.executeUpdate("insert into bank.users values (default,\'"+cont.getNume()+"\',\'"+cont.getPass()+"\',\'"+cont.getAdr()+"\',\'"+cont.getTel()+"\',\'"+cont.getTip()+"\')");
                closeCon();
            }
            closeCon();
        }catch (Exception ex) {
            closeCon();
        }
    }
    private void Connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
        }catch (Exception ex) {
            System.out.println("Errot: " + ex);
        }
    }
    public User find(String owner){
        Connect();
        try {
            st = con.createStatement();
            rez = st.executeQuery("select * from bank.users where nume=\'"+owner+"\'");
            if(rez.next()) {
                String nume = rez.getString("nume");
                String pass = rez.getString("pass");
                String adr = rez.getString("adr");
                String tel = rez.getString("tel");
                String tip = rez.getString("tip");
                User cont = new User(nume,pass,adr,tel,tip);
                closeCon();
                return cont;
            }
            closeCon();;
            return null;
        }catch (Exception e) {
            closeCon();
            return null;
        }
    }
    public void closeCon() {
        try {
            if (rez != null) {
                rez.close();
            }

            if (st != null) {
                st.close();
            }

            if (con != null) {
                con.close();
            }
        } catch (Exception e) {

        }
    }
}
