package be.ac.umons.util;

public interface PizzaComponent
{
    public abstract String getName();
    public abstract float getPrice();
    public abstract void setName(String n);
    public abstract void setPrice(float p);
    public abstract  String toString();
}
