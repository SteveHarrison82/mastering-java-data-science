package chapter09;

import java.util.Arrays;

import org.apache.commons.lang3.Validate;

import smile.validation.AUC;

public class Metrics {

    public static double auc(double[] actual, double[] predicted) {
        Validate.isTrue(actual.length == predicted.length, "the lengths don't match");

        int[] truth = Arrays.stream(actual).mapToInt(i -> (int) i).toArray();
        double auc = AUC.measure(truth, predicted);
        if (auc > 0.5) {
            return auc;
        } else {
            return 1 - auc;
        }
    }

    public static double logLoss(double[] actual, double[] predicted) {
        return logLoss(actual, predicted, 1e-15);
    }

    public static double logLoss(double[] actual, double[] predicted, double eps) {
        Validate.isTrue(actual.length == predicted.length, "the lengths don't match");
        int n = actual.length;
        double total = 0.0;

        for (int i = 0; i < n; i++) {
            double yi = actual[i];
            double pi = predicted[i];

            if (yi == 0.0) {
                double log = Math.log(Math.min(1 - pi, 1 - eps));
                total = total + log;
            } else if (yi == 1.0) {
                double log = Math.log(Math.max(pi, eps));
                total = total + log;
            } else {
                throw new IllegalArgumentException("unrecognized class " + yi);
            }
        }

        return -total / n;
    }

}
