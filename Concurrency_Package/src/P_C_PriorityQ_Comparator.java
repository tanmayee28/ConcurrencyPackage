

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class P_C_PriorityQ_Comparator {

	public static void main(String[] args) {
		
		
		BlockingQueue<EmployeeTest> bq = new PriorityBlockingQueue<>(2,new IdSort()) ;
		ProducerPriority p = new ProducerPriority("Producer 1", bq);
		ConsumerPriority c1 = new ConsumerPriority("Consumer 1",bq);
		c1.start();
		p.start();
		
	}
}

class ProducerPriority extends Thread {
	String str;
	BlockingQueue<EmployeeTest> bq;

	public ProducerPriority(String str, BlockingQueue<EmployeeTest> bq) {
		super(str);
		this.bq = bq;
	}

	public void run() {
		int id=1;
		while(true){
			EmployeeTest e1 = new EmployeeTest("Tanmayee", id);
			System.out.println(Thread.currentThread().getName() + " producing object:" + e1);
			
		try {
			bq.put(e1);
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			
		}
		id++;
		System.out.println("Queue Item:"+bq);
		}
	}
}

class ConsumerPriority extends Thread {
	BlockingQueue<EmployeeTest> bq;
	String nm = "";

	public ConsumerPriority(String nm,BlockingQueue<EmployeeTest> bq) {
		super(nm);
		this.bq = bq;
	}

	public void run() {

		while (true) {
			try {
				EmployeeTest e2 = bq.take();
				System.out.println(Thread.currentThread().getName() + " consuming object:" + e2);
				TimeUnit.SECONDS.sleep(2);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Queue items:"+bq);
		}
	}
}

class EmployeeTest  {
	String snm;
	int sid;

	public EmployeeTest(String snm, int sid) {
		super();
		this.snm = snm;
		this.sid = sid;
	}

//	public int compareTo(EmployeeTest o1) {
//		return  this.sid - o1.sid;
//	}

	@Override
	public String toString() {
		return "EmployeeTest [snm=" + snm + ", sid=" + sid + "]";
	}

}
class IdSort implements Comparator<EmployeeTest>{
public int compare(EmployeeTest o1, EmployeeTest o2) {
	
	return o1.sid-o2.sid;
}
}

