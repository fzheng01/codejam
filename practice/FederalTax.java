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
    private float amtThres;
    private float adjustedGrossIncome;
    private int headCount = 3;

    public FederalTax(int year, float income, float withheld, float exemptAGI) {
        this.grossIncome = income;
        this.adjustedGrossIncome = this.grossIncome - exemptAGI;
        this.withheld = withheld;
        switch (year) {
            case 2014:
                this.bracket = new float[]{0f, 18150f, 73800f, 148850f, 226850f, 405100f, 457600f, Float.MAX_VALUE};
                this.percentage = new float[]{0f, .1f, .15f, .25f, .28f, .33f, .35f, .396f};
                this.standardDeductible = 12400f;
                this.personalExemption = 3950f;
                this.childCredit = 1000f;
                this.amtThres = 82100f;
                break;
            case 2013:
                this.bracket = new float[]{0f, 17850f, 72500f, 146400f, 223050f, 398350f, 450000f, Float.MAX_VALUE};
                this.percentage = new float[]{0f, .1f, .15f, .25f, .28f, .33f, .35f, .396f};
                this.standardDeductible = 12200f;
                this.personalExemption = 3900f;
                this.childCredit = 1000f;
                this.amtThres = 80800f;
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

    private float getAMT(float agi, float tax) {
        float amt = 0f;
        if (agi > this.amtThres) {
            float line30 = agi - this.amtThres;
            if (line30 <= 179500f) {
                amt = line30 * .26f;
            } else {
                amt = line30 * .28f - 3590f;
            }
        }
        return (amt > tax) ? (amt - tax) : 0f;
    }

    public float addDeductible(float value) {
        this.deductible += value;
        return this.deductible;
    }

    public float run() {
        float taxable = adjustedGrossIncome - deductible;
        float tax = getTax(taxable);
        tax += getAMT(adjustedGrossIncome, tax);
        tax -= childCredit;
        return withheld - tax;
    }

    private static float run2014(float ws401k, boolean iraDeductible) {
        float income = 80867.01f;
        float withheld = 14807.61f;
        float taxablePerPay = 4916.66f - 110.37f - 31.95f - 28.58f - 37.5f;
        float totalIRA = 6 * ws401k * 4916.66f;
        float wsIncome= 6 * taxablePerPay - totalIRA;
        income += wsIncome;
        // TODO, estimate
        withheld += 0.166f * wsIncome;
        float iraDeduction;
        iraDeduction = iraDeductible ? 5500f : 0f;
        totalIRA += iraDeduction;
        FederalTax taxPayer = new FederalTax(2014, income, withheld, iraDeduction);
        System.out.printf("Gross Income: $%.2f\tTaxable Income: $%.2f\tIRA: $%.2f  ", income, taxPayer.grossIncome - taxPayer.deductible, totalIRA);
        return taxPayer.run();
    }

    public static void main(String[] args) {
        // =========== 2013 ===========
        float income = 91571f;
        float withheld = 16226.56f;
        float iraDeduction = 5500f;
        FederalTax taxPayer = new FederalTax(2013, income, withheld, iraDeduction);
        System.out.printf("2013 Gross Income: $%.2f, Taxable Income: $%.2f, Tax Return: $%.2f\n", income, taxPayer.grossIncome - taxPayer.deductible, taxPayer.run());

        System.out.printf("2014\n");
        for (float ws401k = 0f; ws401k <= 0.15f; ws401k = ws401k + 0.01f) {
            System.out.printf("\tReturn: $%.2f\n", run2014(ws401k, false));
        }
        System.out.println("---------------------------------------------");
        for (float ws401k = 0f; ws401k <= 0.15f; ws401k = ws401k + 0.01f) {
            System.out.printf("\tReturn: $%.2f\n", run2014(ws401k, true));
        }
    }
}