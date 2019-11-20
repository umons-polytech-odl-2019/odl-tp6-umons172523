package be.ac.umons.util;
import be.ac.umons.Ingredient;
import java.util.ArrayList;

public class Pizza implements PizzaComponent
{
    private String name;
    private float price =0;
    private ArrayList<Ingredient>listIngredients;

    Pizza (String name)
    {
       this.name= name;

    }
    @Override
    public String getName()
    {
        return this.name;
    }
    @Override
    public float getPrice()
    {
        return this.price;

    }
    @Override
    public void setName(String n)
    {
        this.name=n;
    }
    @Override
    public void setPrice(float p)
    {
        this.price = p;
    }
    @Override
    public ArrayList<Ingredient>getListIngredient()
    {
        return listIngredients;
    }
    @Override
    public void addIngredient(Ingredient i)
    {
        listIngredients.add(i);
    }
    @Override
    public String toString()
    {
        String tmp= "";
        for(Ingredient elem : listIngredients)
        {
            tmp+=" \t"+"Nom "+elem.getName()+"Prix "+elem.getPrice();


        }
        return "Nom: " +this.name +"Prix: "+this.price +"Ingredients: "+tmp;
    }

}
