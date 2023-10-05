package model;
//import android.annotation.NonNull;

import java.io.Serializable;

public class Orders implements Serializable {

    private int clNumber;
    private  String pizzaType;
    private int nbSlices;

    public Orders(int clNumber, String pizzaType, int nuSlices) {
        this.clNumber = clNumber;
        this.pizzaType = pizzaType;
        this.nbSlices = nuSlices;
    }

    public int getClNumber() {
        return clNumber;
    }

    public void setClNumber(int clNumber) {
        this.clNumber = clNumber;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public int getNbSlices() {
        return nbSlices;
    }

    public void setNbSlices(int nbSlices) {
        this.nbSlices = nbSlices;
    }


    public String toString(){
        return clNumber + "  \t" + pizzaType + "  \t" + nbSlices + "  \t" + getAmount(pizzaType, nbSlices);
    }

    public static float getAmount(String pizzaType, int nbSlices) {
//        float amount = 0;
//        if(pizzaType.equals("Vegen"))
//            amount = nbSlices * 2.5f;
//        else
//            if(pizzaType.equals("Cheese"))
//                amount = nbSlices * 2.0f;
//            else
//                if(pizzaType.equals("Mexico"))
//                    amount = nbSlices * 2.4f;
//
//        return amount;

        try{

            return  getPrice(pizzaType) * nbSlices;
        }catch (Exception e){
            return  -1;
        }
    }

    public static float getPrice(String pizzaType) throws Exception {
        float price = 0f;
        if(pizzaType.equals("Vegen")){
            price = 2.5f;
        } else if (pizzaType.equals("Cheese")) {
            price = 2.0f;
        } else if (pizzaType.equals("Mexico")) {
            price = 2.4f;
        }else
            throw new Exception("Please select Pizza Type");
        return price;
    }

}
