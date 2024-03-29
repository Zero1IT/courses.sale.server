package by.gstu.courses.domain;

/**
 * createdAt: ${DATE}
 * project: ${PROJECT_NAME}
 *
 * @author Alexander Petrushkin
 */
@Deprecated
public enum PaymentType {
    MANUAL((user, t) -> true); // nothing do

    private final PaymentDescribe paymentDescribe;
    private final PaymentProcessor paymentProcessor;

    PaymentType(PaymentProcessor paymentProcessor) {
        this(null, paymentProcessor);
    }

    PaymentType(PaymentDescribe paymentDescribe, PaymentProcessor paymentProcessor) {
        this.paymentDescribe = paymentDescribe;
        this.paymentProcessor = paymentProcessor;
    }

    public PaymentDescribe getPaymentDescribe() {
        return paymentDescribe;
    }

    public boolean processPayment(User user, Object ... data) {
        return paymentProcessor != null && paymentProcessor.paymentSuccess(user, data);
    }

    public static class PaymentDescribe {
        private String iconUrl;

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }
    }

    public interface PaymentProcessor {
        boolean paymentSuccess(User user, Object ... paymentData);

        default String errorInfo() {
            return "No error info";
        }
    }
}
