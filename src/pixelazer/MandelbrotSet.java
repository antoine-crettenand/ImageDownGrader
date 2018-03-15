package pixelazer;

import javafx.scene.paint.Color;

import java.util.function.BiFunction;

public final class MandelbrotSet {

	private static int mand(Complex z0, int maxIterations) {
		Complex z = z0;
		for (int i = 0; i < maxIterations; i++) {
			if (z.abs() > 2.0)
				return i;
			z = z.multiply(z).plus(z0);
		}
		return maxIterations;
	}

	public static int mand(int x, int y, int maxIterations) {
		return mand(new Complex(x, y), maxIterations);
	}

	public static BiFunction<Integer, Integer, Color> mandelbrotPainter(int maxIterations) {
		return (x, y) -> {
			int gray = ((int) ((maxIterations - mand(x, y, maxIterations)) / ((double) maxIterations)));
			return new Color(gray, gray, gray, 1);
		};
	}

}

class Complex {
	private double re;
	private double im;

	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}

	public double abs() {
		return Math.sqrt(re * re + im * im);
	}

	public Complex multiply(Complex that) {
		return new Complex(re * that.re - im * that.im, re * that.im + im * that.re);
	}

	public Complex plus(Complex that) {
		return new Complex(re + that.re, im + that.im);
	}
}
