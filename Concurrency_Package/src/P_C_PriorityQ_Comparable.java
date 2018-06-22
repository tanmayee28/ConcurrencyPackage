

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class P_C_PriorityQ_Comparable {
	public static void main(String[] args) {
		BlockingQueue<EmployeeTest1> bq = new PriorityBlockingQueue<>(2);
		ProducerPriority1 p1 = new ProducerPriority1("Producer 1", bq);
		ConsumerPriority1 c1 = new ConsumerPriority1("Consumer 1", bq);
		p1.start();
		c1.start();
	}

}

class ProducerPriority1 extends Thread {
	String str;
	BlockingQueue<EmployeeTest1> bq;

	public ProducerPriority1(String str, BlockingQueue<EmployeeTest1> bq) {
		super(str);
		this.bq = bq;
	}

	public void run() {
		int id = 1;
		while (true) {
			EmployeeTest1 e1 = new EmployeeTest1("Tanmayee", id);
			System.out.println(Thread.currentThread().getName() + " producing object:" + e1);

			try {
				bq.put(e1);
				TimeUnit.SECONDS.sleep(2);
			} catch (Exception e) {

			}
			id++;
			System.out.println("Queue Item:" + bq);
		}
	}
}

class ConsumerPriority1 extends Thread {
	BlockingQueue<EmployeeTest1> bq;
	String nm = "";

	public ConsumerPriority1(String nm, BlockingQueue<EmployeeTest1> bq) {
		super(nm);
		this.bq = bq;
	}

	public void run() {

		while (true) {
			try {
				EmployeeTest1 e2 = bq.take();
				System.out.println(Thread.currentThread().getName() + " consuming object:" + e2);
				TimeUnit.SECONDS.sleep(2);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Queue items:" + bq);
		}
	}
}

class EmployeeTest1 implements Comparable<EmployeeTest1> {
	String snm;
	int sid;

	public EmployeeTest1(String snm, int sid) {
		super();
		this.snm = snm;
		this.sid = sid;
	}

	@Override
	public String toString() {
		return "EmployeeTest1 [snm=" + snm + ", sid=" + sid + "]";
	}

	@Override
	public int compareTo(EmployeeTest1 o) {

		return this.sid - o.sid;
	}

}
