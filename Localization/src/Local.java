public class Local {
	public static void main(String[] args) {
//This is the main method
		String[] world = { "green", "red", "red", "green", "green", "red" };
		String measurements[] = { "red", "red" };
		double[] p = { 0.2, 0.2, 0.2, 0.2, 0.2,0.2 };

		for (int i = 0; i < 2; i++) {
			p = sense(p, 0.6, 0.2, measurements[i], world);
			p = move(p, 1);
		}

		for (int i = 0; i < p.length; i++) {
			System.out.print(p[i] + "  ");
		}
	}

	private static double[] move(double[] p, int step) {
		// implement the moving method
		int shift = step;
		double pExact = 0.8;
		double pOvershoot = 0.1, pUndershoot = 0.1;
		double[] arr1 = p;

		double[] arr2 = new double[arr1.length + shift + 1];
		double[] arr2Final = new double[arr1.length];

		for (int i = 0; i < arr1.length; i++) {
			arr2[(i + shift) % arr1.length] += arr1[i] * pExact;
			arr2[(i + shift + 1) % arr1.length] += arr1[i] * pOvershoot;
			arr2[(i + shift - 1) % arr1.length] += arr1[i] * pUndershoot;
		}

		for (int i = 0; i < arr2Final.length; i++) {
			arr2Final[i] = arr2[i];
		}
		return arr2Final;
	}

	// Return the posterior believe (probability)
	private static double[] sense(double[] p, double pHit, double pMiss,
			String sensedThing, String[] worldIn) {
		double sum = 0;
		for (int i = 0; i < p.length; i++) {
			if (sensedThing.contains(worldIn[i])) {
				p[i] = p[i] * pHit;
				sum += p[i];
			} else {
				p[i] = p[i] * pMiss;
				sum += p[i];
			}
		}
		// to normalize the probability
		for (int i = 0; i < p.length; i++) {
			p[i] = p[i] / sum;
		}
		return p;
	}}
