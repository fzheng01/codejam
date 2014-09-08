import java.lang.IllegalArgumentException;
import java.util.Random;

public class FederalTax {
    private float grossIncome;
    private float withheld;
    private float[] bracket;
    private float[] percentage;
    private float standardDeductible;
    private float personalExemption;
    private float deductible;
    private float childCredit;
    private int headCount = 3;

    public FederalTax(int year, float income, float withheld) {
        this.grossIncome = income;
        this.withheld = withheld;
        switch (year) {
            case 2014:
                this.bracket = new float[]{0f, 18150f, 73800f, 148850f, 226850f, 405100f, 457600f, Float.MAX_VALUE};
                this.percentage = new float[]{0f, .1f, .15f, .25f, .28f, .33f, .35f, .396f};
                this.standardDeductible = 12400f;
                this.personalExemption = 3950f;
                this.childCredit = 1000f;
                break;
            case 2013:
                this.bracket = new float[]{0f, 17850f, 72500f, 146400f, 223050f, 398350f, 450000f, Float.MAX_VALUE};
                this.percentage = new float[]{0f, .1f, .15f, .25f, .28f, .33f, .35f, .396f};
                this.standardDeductible = 12200f;
                this.personalExemption = 3900f;
                this.childCredit = 1000f;
                break;
            default:
                throw new IllegalArgumentException("invalid year for tax bracket");
        }
        this.deductible = standardDeductible + headCount * personalExemption;
    }

    private float getTax(float taxable) {
        if (taxable <= 0f) throw new IllegalArgumentException("taxable amount must be positive");
        float taxDue = 0f;
        for (int i = 1; i < bracket.length; i++) {
            if (this.bracket[i] < taxable) taxDue += this.percentage[i] * (this.bracket[i] - this.bracket[i - 1]);
            else {
                taxDue += this.percentage[i] * (taxable - this.bracket[i - 1]);
                break;
            }
        }
        return taxDue;
    }

    public float addDeductible(float value) {
        this.deductible += value;
        return this.deductible;
    }

    public float run() {
        float tax = getTax(grossIncome - deductible);
        return withheld - (tax - childCredit);
    }

    public static void main(String[] args) {
        // =========== 2013 ===========
        float income = 91571f;
        float withheld = 16226.56f;
        FederalTax taxPayer = new FederalTax(2013, income, withheld);
        taxPayer.addDeductible(5500); // IRA deductible
        System.out.printf("2013 Gross Income: %.2f, Taxable Income: %.2f, Tax Return: %.2f\n", income, taxPayer.grossIncome - taxPayer.deductible, taxPayer.run());
        // =========== 2014 ===========
        income = 77404.18f - (10274.49f - 2946.81f);
        withheld = 13065.33f;
        // mid sep payment of fc
        income += 4174.13f - 83.59f - 10.58f - 1.57f - 41.66f - 250.45f;
        withheld += 668.21f;
        // last payment of fc
        income += .94f * 8 * 6.17f * 48.161186f;
        withheld += 500f;
        // ws payment till end of year
        float ws401k = 0.08f;
        income += 7 * (4916.67f - 110.37f - 31.95f - 35f - ws401k * 4916.67f);
        withheld += 7 * 700f;
//        income = 100366.74f;
//        withheld = 18411.01f;
        taxPayer = new FederalTax(2014, income, withheld);
        taxPayer.addDeductible(5500); // IRA deductible
        System.out.printf("2014 Gross Income: %.2f, Taxable Income: %.2f, Tax Return: %.2f\n", income, taxPayer.grossIncome - taxPayer.deductible, taxPayer.run());
    }
}