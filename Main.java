public class Main {

	/**
	 * creates a number of workers
	 * creates a single book
	 * sleeps for a while
	 * interrupts the workers
	 */

	private Worker w1, w2, w3, w4, w5;
	private Book book;



	private Main() {
		book = new Book();
		w1 = new Worker("Thread 1",book);
		w2 = new Worker("Thread 2",book);
		w3 = new Worker("Thread 3",book);
		w4 = new Worker("Thread 4",book);
		w5 = new Worker("Thread 5",book);
	}

	public static void main(String[] args) {
		System.out.println("Number of threads = 4");
		Main m = new Main() ;
		Thread t1 = new Thread(m.w1) ;
		t1.start() ;
		Thread t2 = new Thread(m.w2) ;
		t2.start() ;
		Thread t3 = new Thread(m.w3) ;
		t3.start() ;
		Thread t4 = new Thread(m.w4) ;
		t4.start() ;
		Thread t5 = new Thread(m.w5);
		t5.start();
		//System.out.println("Number of threads = " + ((int)Thread.activeCount()-1));


		try {
			Thread.sleep(500000) ;
		} catch (InterruptedException e) {}


	}
}
