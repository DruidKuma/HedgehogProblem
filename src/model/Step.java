package model;

/**
 * Created by Iurii Miedviediev
 *
 * Represents step between adjacent Apple Trees in the Apple Garden
 *
 * @author DruidKuma
 * @version 1.0.0
 * @since 3/28/17
 */
public class Step {
    private final AppleTree source;
    private final AppleTree destination;

    public Step(AppleTree source, AppleTree destination) {
        this.source = source;
        this.destination = destination;
    }

    public AppleTree getDestination() {
        return destination;
    }

    public AppleTree getSource() {
        return source;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }
}