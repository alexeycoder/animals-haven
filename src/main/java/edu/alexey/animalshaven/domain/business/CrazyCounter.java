package edu.alexey.animalshaven.domain.business;

public class CrazyCounter implements AutoCloseable {

	private volatile boolean isClosed;
	private int count;

	public CrazyCounter(int initial) {
		this.count = initial;
		this.isClosed = false;
	}

	public CrazyCounter() {
		this(0);
	}

	public int add() throws CounterClosedException {
		if (isClosed) {
			throw new CounterClosedException();
		}
		return ++count;
	}

	public int get() throws CounterClosedException {
		if (isClosed) {
			throw new CounterClosedException();
		}
		return ++count;
	}

	@Override
	public void close() {
		isClosed = true;
	}

	public static class CounterClosedException extends Exception {
	}
}

// class Test {
// 	public static void main(String[] args) {
// 		try (var counterA = new CrazyCounter()) {
// 			System.out.println(counterA.add());
// 			System.out.println(counterA.add());
// 			System.out.println(counterA.add());
// 		} catch (CrazyCounter.CounterClosedException e) {
// 			e.printStackTrace();
// 		}

// 		var counterB = new CrazyCounter();
// 		System.out.println(counterB.add());
// 		System.out.println(counterB.add());
// 	}
// }
