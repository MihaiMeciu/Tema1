package UIController;

import Logic.Account;
import Logic.Transfer;
import Logic.User;
import UI.TransferGenUI;
import UI.TransferInUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Devene on 3/20/2016.
 */
public class TransferInC {
    private TransferInUI view;
    private User cont;

    public TransferInC(User session) {
        this.view = OptiuniC.trI;
        view.addTransListener(new TransListener());
        view.addBackListener(new BackListener());
        this.cont = session;
    }

    class TransListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double suma = Double.parseDouble(view.sumaC.getText());
            String tipS = (String)view.contS.getSelectedItem();
            String tipD = (String)view.contD.getSelectedItem();
            Account contS = new Account();
            Account contD = new Account();
            String dateS = contS.find(cont.getNume(),tipS);
            String dateD = contD.find(cont.getNume(),tipD);
            String[] start = dateS.split(",");
            String[] dest = dateD.split(",");
            double amount = Double.parseDouble(start[1]);
            double comm = Double.parseDouble(start[3]);
            double amountD = Double.parseDouble(dest[1]);
            if(amount-suma-comm<0 && tipS.equals("Credit")){
                contS.update(cont.getNume(),amount-suma-comm, tipS);
                contD.update(cont.getNume(),amountD+suma, tipD);
                Transfer rap = new Transfer(cont.getNume(),cont.getNume(),suma,cont.getNume());
                rap.intsert(rap);
            }else if(amount-suma-comm>=0){
                contS.update(cont.getNume(),amount-suma-comm, tipS);
                Transfer rap = new Transfer(cont.getNume(),cont.getNume(),suma,cont.getNume());
                rap.intsert(rap);
            }
        }
    }

    class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            LoginC.op.show();
            view.hide();
        }
    }
}
