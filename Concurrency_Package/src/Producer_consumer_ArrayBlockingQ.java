

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Producer_consumer_ArrayBlockingQ {

	public static void main(String[] args) {
		BlockingQueue<Integer> bq = new ArrayBlockingQueue<>(2);
		Produce p=new Produce(bq,"Producer");
		Consume c=new Consume(bq,"Consume");
		p.start();
		c.start();
	}

}
class Produce extends Thread
{
	BlockingQueue<Integer>bq=null;
	String str;
	public Produce(BlockingQueue<Integer> bq, String str) {
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
class Consume extends Thread{
	BlockingQueue<Integer>bq=null;
	String str;
	public Consume(BlockingQueue<Integer> bq, String str) {
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