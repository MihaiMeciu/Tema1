package Logic;

import Database.TransGateway;

import java.util.ArrayList;

/**
 * Created by Devene on 3/20/2016.
 */
public class Transfer {

    private String sursa;
    private String dest;
    private double suma;
    private String oper;

    public Transfer(){}
    public Transfer(String sursa,String dest,double suma,String oper){
        this.sursa = sursa;
        this.dest = dest;
        this.suma = suma;
        this.oper = oper;
    }

    public void intsert(Transfer rap){
        TransGateway gate = new TransGateway();
        gate.insert(rap);
    }
    public ArrayList<String> find(String oper){
        TransGateway gate = new TransGateway();
        ArrayList<String> rez = gate.find(oper);
        return rez;
    }
    public String getSursa(){
        return sursa;
    }
    public String getDest(){
        return dest;
    }
    public double getSuma(){
        return suma;
    }
    public String getOper(){
        return oper;
    }
    public String toString(){
        return "Din:"+sursa+" in:"+dest+" suma de:"+String.valueOf(suma)+" operat de:"+oper+".";
    }
}
