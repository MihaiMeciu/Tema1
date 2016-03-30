package UIController;

import Logic.Account;
import Logic.Transfer;
import Logic.User;
import UI.TransferGenUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Devene on 3/20/2016.
 */
public class TransferGenC {
    private TransferGenUI view;
    private User cont;

    public TransferGenC(User session) {
        this.view = OptiuniC.trG;
        this.cont = session;
        view.addTransListener(new TransListener());
        view.addBackListener(new BackListener());
    }

    class TransListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String numeS = view.numeC.getText();
            String numeD = view.numeD.getText();
            double suma = Double.parseDouble(view.sumaC.getText());
            String tipS = (String)view.contS.getSelectedItem();
            String tipD = (String)view.contD.getSelectedItem();
            Account contS = new Account();
            Account contD = new Account();
            String dateS = contS.find(numeS,tipS);
            String dateD = contD.find(numeD,tipD);
            String[] start = dateS.split(",");
            String[] dest = dateD.split(",");
            double amount = Double.parseDouble(start[1]);
            double comm = Double.parseDouble(start[3]);
            double amountD = Double.parseDouble(dest[1]);
            if(amount-suma-comm<0 && tipS.equals("Credit")){
                contS.update(numeS,amount-suma-comm, tipS);
                contD.update(numeD,amountD+suma, tipD);
                Transfer rap = new Transfer(numeS,numeD,suma,cont.getNume());
                rap.intsert(rap);
            }else if(amount-suma-comm>=0){
                contS.update(numeS,amount-suma-comm, tipS);
                contD.update(numeD,amountD+suma, tipD);
                Transfer rap = new Transfer(numeS,numeD,suma,cont.getNume());
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
