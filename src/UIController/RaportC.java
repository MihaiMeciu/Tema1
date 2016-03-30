package UIController;

import Logic.Transfer;
import UI.RaportUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Devene on 3/20/2016.
 */
public class RaportC {
    private RaportUI view;
    private ArrayList<String> raport;

    public RaportC(){
        this.view = OptiuniC.rap;
        view.addBackListener(new BackListener());
        view.addSearchListener(new SearchListener());
    }

    class BackListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            LoginC.op.show();
            view.hide();
        }
    }
    class SearchListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String oper = view.oper.getText();
            Transfer rap = new Transfer();
            raport = rap.find(oper);
            view.createTable(raport);
        }
    }
}
