package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Devene on 3/20/2016.
 */
public class RaportUI {
    static JFrame frame = new JFrame("-Raport-");
    JPanel butoane = new JPanel(new GridLayout(1,4));
    JPanel date = new JPanel(new GridLayout(1,1));
    JButton cauta = new JButton("Search");
    JButton inapoi = new JButton("Back");
    JLabel operL = new JLabel("Utilizator");
    public JTextField oper = new JTextField("",JTextField.LEFT);
    static JTable table = new JTable();
    static DefaultTableModel tableModel;

    public RaportUI(){
        frame.setSize(650,600);
        butoane.add(operL);
        butoane.add(oper);
        butoane.add(cauta);
        butoane.add(inapoi);
        date.add(table);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(butoane);
        frame.getContentPane().add(date);
        frame.setVisible(true);
    }
    public void  createTable(ArrayList<String> raport){
        try {
            tableModel = new DefaultTableModel(new Object[]{"Tranzactie"}, 0);
            table.setModel(tableModel);
            tableModel.addRow(new Object[]{"Tranzactie"});
            for(int i=0;i<raport.size();i++) {
                String row = raport.get(i);
                int count = tableModel.getRowCount()+1;
                tableModel.addRow(new Object[]{row});
            }
        }catch (Exception ex){

        }

    }
    public void addBackListener(ActionListener back){ inapoi.addActionListener(back);}
    public void addSearchListener(ActionListener search){ cauta.addActionListener(search);}
    public void hide(){
        frame.setVisible(false);
    }
    public void show(){
        frame.setVisible(true);
    }
}
