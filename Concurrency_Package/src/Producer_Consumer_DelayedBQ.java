

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Producer_Consumer_DelayedBQ {

	public static void main(String[] args) {
		BlockingQueue<Employee>emp=new DelayQueue<Employee>();
		ProducerDelay p1=new ProducerDelay(emp,"Producer Thread");
		ConsumerDelay c1=new ConsumerDelay("Consumer Thead",emp);
		p1.start();
		c1.start();

	}

}
class ProducerDelay extends Thread{
	BlockingQueue<Employee>emp=null;
	String nm;
	public ProducerDelay(BlockingQueue<Employee> emp, String nm) {
		super(nm);
		this.emp = emp;
	}
	public void run()
	{
		int i=1;
		while(i<=5){
			try{
				Employee emp1=new Employee("Employee 1",2000);
				System.out.println(Thread.currentThread().getName()+" produce item:"+emp1);
				emp.put(emp1);
				Thread.sleep(1000);
			}catch(Exception e){}
			i++;
		}	
			
	}
}
class ConsumerDelay extends Thread{
	String str;
	BlockingQueue<Employee>bq=null;
	public ConsumerDelay(String str, BlockingQueue<Employee> bq) {
		super(str);
		this.bq = bq;
	}
	public void run()
	{
		int i=1;
		while(i<=5)
		{
			try{
				
				Employee e1=bq.take();
				System.out.println(Thread.currentThread().getName()+" consuming item:"+e1);
				Thread.sleep(1000);
			}catch(Exception e){}
			i++;
		}
	}
}
class Employee implements Delayed{

	String str;
	private long starttime;
	
	public Employee(String string, long delay) {
		this.str=string;
		this.starttime=System.currentTimeMillis()+delay;
		
	}
	
	public int compareTo(Delayed o) {
		if(this.starttime<((Employee)o).starttime){
			return -1;
		}
		if(this.starttime>((Employee)o).starttime){
		return 1;
		}
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		long diff=starttime-System.currentTimeMillis();
		return unit.convert(diff,TimeUnit.MILLISECONDS);
	}

	@Override
	public String toString() {
		return "Employee [str=" + str + ", starttime=" + starttime + "]";
	}
	
}