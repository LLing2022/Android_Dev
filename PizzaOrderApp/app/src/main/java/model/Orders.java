package model;

public class Orders {

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
        return clNumber + "\t" + nbSlices + "\t" + getAmount();
    }

    private float getAmount() {
        float amount = 0;
        if(pizzaType.equals("Vegen"))
            amount = nbSlices * 2.5f;
        else
            if(pizzaType.equals("Cheese"))
                amount = nbSlices * 2.0f;
            else
                if(pizzaType.equals("Mexico"))
                    amount = nbSlices * 2.4f;

        return amount;
    }
}
