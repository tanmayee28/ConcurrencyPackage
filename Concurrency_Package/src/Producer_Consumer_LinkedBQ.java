

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Producer_Consumer_LinkedBQ {

	public static void main(String[] args) {
		BlockingQueue<Integer>bq=new LinkedBlockingQueue<>(2);
		ProducerLinked p=new ProducerLinked(bq,"Producer 1");
		ConsumerLinked c=new ConsumerLinked(bq,"Consumer 1");
		p.start();
		c.start();
		
		System.out.println(Thread.currentThread().getName()+" completed");

	}

}
class ProducerLinked extends Thread
{
	BlockingQueue<Integer> bq=null;
	String str="";
	public ProducerLinked(BlockingQueue<Integer> bq, String str) {
		super(str);
		this.bq = bq;
	}
	public void run()
	{
		int i=1;
		while(true)
		{
			System.out.println(bq);
			try{
				TimeUnit.SECONDS.sleep(1);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			int no=ThreadLocalRandom.current().nextInt(1,50);
			System.out.println(Thread.currentThread().getName()+" missing sushant:-)"+no);
			try {
				System.out.println(bq.offer(no,2000,TimeUnit.SECONDS));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(bq.offer(no));
			i++;
		}
	}
	
	}
class ConsumerLinked extends Thread
{
	BlockingQueue<Integer>bq1=null;
	String str;
	public ConsumerLinked(BlockingQueue<Integer> bq1, String str) 
	{
		super(str);
		this.bq1 = bq1;
	}
	public void run()
	{
		int i=0;
		while(true)
		{
			System.out.println(bq1);
			try{
		//	TimeUnit.SECONDS.sleep(10);for only offer();
				TimeUnit.SECONDS.sleep(2);
			}catch(Exception e){
				e.printStackTrace();	
			}
			//System.out.println(Thread.currentThread().getName()+" consuming item:"+bq1.poll());
			try {
				System.out.println(Thread.currentThread().getName()+" consuming item:"+bq1.poll(2000,TimeUnit.SECONDS));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
	}
	
}
//