class TasteException extends Exception {
    TasteException() {
        super("Sorry, I cannot drink this, it is too cheap");
    }
}

class MoneyException extends Exception {
    MoneyException() {
        super("Sorry, it is too expensive, I cannot afford");
    }
}

class Beer {
    void drink(String brand) throws TasteException, MoneyException {
        if (brand.equals("BudLight")) {
            throw new TasteException();
        } else {
            if (brand.equals("Sapporo")) {
                throw new MoneyException();
            } else {
                System.out.println("I am enjoying " + brand);
            }
        }
    }
}

public class ExceptionTest {
    int internalTest(String brand) {
        int retVal = 1;
        Beer tonightBeer = new Beer();
        try {
            tonightBeer.drink(brand);
        } catch (TasteException e) {
            System.out.println(e.getMessage());
            return retVal;
        } catch (MoneyException e) {
            System.out.println(e.getMessage());
            return -retVal;
        } finally {
            System.out.println("I have to pay it");
            return 2*retVal;
        }
    }
    
    public static void main(String[] args) {
        String brand = args[0];
        ExceptionTest et = new ExceptionTest();
        int retVal = et.internalTest(brand);
        System.out.println(retVal);
    }
}
