package page.bshukla.contactsotp.util;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

public class RandomOtpGenerator {
    private static final String DIGITS = "0123456789";
    private final Random random;
    private final char[] symbols;
    private final char[] buffer;

    public String nextString() {
        for(int idx = 0; idx < buffer.length; ++idx) {
            buffer[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buffer);
    }

    public RandomOtpGenerator(int length, Random random, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buffer = new char[length];
    }

    public RandomOtpGenerator(int length, Random random) {
        this(length, random, DIGITS);
    }

    public RandomOtpGenerator(int length) {
        this(length, new SecureRandom());
    }

    public RandomOtpGenerator() {
        this(6);
    }
}
