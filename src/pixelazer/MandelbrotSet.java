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

	private static int mand(float x, float y, int maxIterations) {
		return mand(new Complex(x, y), maxIterations);
	}

	public static BiFunction<Integer, Integer, Color> mandelbrotPainter(int maxIterations, int maxX, int maxY) {
		return (x, y) -> {
			//x from (0 to maxX) to (-2, 2)		y from (0 to maxY) to (-1, 1)
			float i = map(0, maxX, -2, 2, x);
			float j = map(0, maxY, -1, 1, y);
			//		System.out.println("(" + x + ", " + y +") -> (" + i + ", " + j + ")");
			float gray = mand(i, j, maxIterations) / maxIterations;
			//		System.out.println(gray);
			return new Color(gray, gray, gray, 1);
		};
	}

	/**
	 * Using Linear Interpolation
	 *
	 * @return
	 */
	private static float map(float xa, float xb, float ya, float yb, float v) {
		return (ya - yb) / (xa - xb) * v + (xa * yb - xb * ya) / (xa - xb);
	}

}

final class Complex {
	private final double re;
	private final double im;

	Complex(double re, double im) {
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
