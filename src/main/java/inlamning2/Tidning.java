package inlamning2;


//Arv med klass Produkt
public class Tidning extends Produkt {

    public Tidning(String namn, double pris) {
        super(namn, pris);
    }
//Polymorphism här
    @Override
    public double getMomsSats() {

        return 6.0; // Tidningar har 6 % moms i Sverige
    }
}

