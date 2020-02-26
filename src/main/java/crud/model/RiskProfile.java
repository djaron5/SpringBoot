package crud.model;

public enum RiskProfile {
    LOW(1), NORMAL(2), HIGHT(3);

    public int getRiskLevel() {
        return riskLevel;
    }

    int riskLevel;

    RiskProfile(int riskLevel) {
        this.riskLevel = riskLevel;
    }
}
