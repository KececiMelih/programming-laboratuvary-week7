
package lab_week7;


public class Coupon {

    private String code;
    private double discountRate;
    private double minSubtotal;

    public Coupon(String code, double discountRate,
                  double minSubtotal) {

        if (discountRate < 0 || discountRate > 0.50) {
            throw new IllegalArgumentException(
                    "Discount rate must be between 0 and 50%!");
        }

        this.code = code;
        this.discountRate = discountRate;
        this.minSubtotal = minSubtotal;
    }

    public String getCode() {
        return code;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public double getMinSubtotal() {
        return minSubtotal;
    }
}
