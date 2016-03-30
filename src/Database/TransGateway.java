package Database;

import Logic.Transfer;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Devene on 3/24/2016.
 */
public class TransGateway {
    private Connection con;
    private Statement st;
    private ResultSet rez;
    private PreparedStatement pst;

    private void Connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
        }catch (Exception ex) {
            System.out.println("Errot: " + ex);
        }
    }
    public void insert(Transfer rap){
        Connect();
        try{
            st = con.createStatement();
            int res = st.executeUpdate("insert into bank.raport values(default,\'"+rap.getSursa()+"\',\'"+rap.getDest()+"\',\'"+String.valueOf(rap.getSuma())+"\',\'"+rap.getOper()+"\')");
        }catch (Exception ex){

        }
        closeCon();
    }
    public ArrayList<String> find(String oper){
        Connect();
        try{
            st = con.createStatement();
            rez = st.executeQuery("select * from bank.raport where operator=\'"+oper+"\'");
            ArrayList<String> rasp = new ArrayList<>();
            while(rez.next()){
                rasp.add(rez.getString("sursa")+"->"+rez.getString("dest")+"  $:"+String.valueOf(rez.getDouble("valoare"))+"    Operat de:"+rez.getString("operator"));
            }
            closeCon();
            return rasp;
        }catch (Exception ex){
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
