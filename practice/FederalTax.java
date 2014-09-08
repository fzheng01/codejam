public class FederalTax {
    private float grossIncome;
    private float withheld;
    private float deductible;
    private float standardDeductible = 12400f;
    private float childCredit = 1000f;
    private float personalExemption = 3950f;
    private int heads = 3;
    
    public FederalTax(float income, float withheld) {
        this.grossIncome = income;
        this.withheld = withheld;
        this.deductible = standardDeductible + heads * personalExemption;
    }
    
    public float addDeductible(float value) {
        this.deductible += value;
        return this.deductible;
    }
    
    public float run() {
        float tax = getTaxOfMarriedFilingJointly(grossIncome - deductible);
        return withheld - (tax - childCredit);
    }
    
    private float getTaxOfMarriedFilingJointly(float taxable) {
        if(taxable <= 18150) return 0.1f*taxable;
        else if(taxable <= 73800) return 1815f + 0.15f*(taxable-18150);
        else if(taxable <= 148850) return 10162.5f + 0.25f*(taxable-73800);
        else if(taxable <= 226850) return 28925f + 0.28f*(taxable-148850);
        else if(taxable <= 405100) return 50765f + 0.33f*(taxable-226850);
        else if(taxable <= 457600) return 109587.5f + 0.35f*(taxable-405100);
        else return 127962.5f + 0.396f*(taxable-457600);
    }
    
    private float getTaxOfMarriedFilingJointly2013(float taxable) {
        // for tax year 2013
        if(taxable <= 17850) return 0.1f*taxable;
        else if(taxable <= 72500) return 1785f + 0.15f*(taxable-17850);
        else if(taxable <= 146400) return 10875f + 0.25f*(taxable-72500);
        else if(taxable <= 223050) return 28925f + 0.28f*(taxable-146400); // inaccurate
        else if(taxable <= 398350) return 50765f + 0.33f*(taxable-223050); // inaccurate
        else if(taxable <= 450000) return 109587.5f + 0.35f*(taxable-398350); // inaccurate
        else return 127962.5f + 0.396f*(taxable-450000); // inaccurate
    }
    
    public static void main(String[] args) {
        float income = 100366.74f;
        float withheld = 18411.01f;
//        float income = 91571f;
//        float withheld = 16226.56f;
        FederalTax feng = new FederalTax(income, withheld);
        feng.addDeductible(5500); // IRA deductible
        System.out.printf("Gross Income: %.2f\n", feng.grossIncome);
        System.out.printf("Taxable Income: %.2f\n", feng.grossIncome-feng.deductible);
        System.out.printf("Tax Return: %.2f\n", feng.run());
    }
}