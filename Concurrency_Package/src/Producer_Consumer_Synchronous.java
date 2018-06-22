

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Producer_Consumer_Synchronous {

	public static void main(String[] args) {
		BlockingQueue<Integer> bq = new SynchronousQueue<>();
		ProduceSynchronous p=new ProduceSynchronous(bq,"Producer");
		ConsumeSynchronous c=new ConsumeSynchronous(bq,"Consume");
		p.start();
		c.start();
		//System.out.println(Thread.currentThread().getName()+" is completed");
	}

}
class ProduceSynchronous extends Thread
{
	BlockingQueue<Integer>bq=null;
	String str;
	public ProduceSynchronous(BlockingQueue<Integer> bq, String str) {
		super(str);
		this.bq = bq;
	}
	public void run()
	{
		int i=1;
		while(true){
		try{
			Thread.sleep(3000);
			int no=ThreadLocalRandom.current().nextInt(1,50);
			System.out.println(Thread.currentThread().getName()+" produce: "+no);
			bq.put(no);
			
		}catch(Exception e){}
		i++;
	}
}		
}
class ConsumeSynchronous extends Thread{
	BlockingQueue<Integer>bq=null;
	String str;
	public ConsumeSynchronous(BlockingQueue<Integer> bq, String str) {
		super(str);
		this.bq = bq;
		
	}
	public void run()
	{
		int i=0;
		while(true){
		try{
			Thread.sleep(3000);
			System.out.println(Thread.currentThread().getName()+" consume:"+bq.take());
		}catch(Exception e){}
		i++;
	}
		}
}
