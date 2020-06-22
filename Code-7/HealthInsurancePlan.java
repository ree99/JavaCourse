public class HealthInsurancePlan {
    // Code for 'coverage' field goes here
    private double coverage;
    private double extraDiscount;

    public double getExtraDiscount() {
        return extraDiscount;
    }
    public double getCoverage() {
        return this.coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }
    public void setExtraDiscount(double extraDiscount) {
        this.extraDiscount = extraDiscount;
    }

    // Don't worry about the below code and also the InsuranceBrand class
	private InsuranceBrand offeredBy;

	public InsuranceBrand getOfferedBy() {
		return offeredBy;
	}

	public void setOfferedBy(InsuranceBrand offeredBy) {
		this.offeredBy = offeredBy;
	}
}
