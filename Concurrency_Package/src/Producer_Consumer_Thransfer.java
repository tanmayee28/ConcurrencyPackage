

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TransferQueue;

public class Producer_Consumer_Thransfer {

	public static void main(String[] args) {
		TransferQueue<Integer> tf=new LinkedTransferQueue<Integer>();
		ProducerTransfer p1=new ProducerTransfer(tf,"Producer 1");
		ConsumerTransfer c1=new ConsumerTransfer(tf,"Consumer 1");
		p1.start();
		c1.start();
		

	}

}
class ProducerTransfer extends Thread{

	String str;
	TransferQueue<Integer> p1=null;
	public ProducerTransfer(TransferQueue<Integer> tf, String string) {
		super(string);
		this.p1=tf;	
	}
	public void run()
	{
		while(true)
		{
			int no=ThreadLocalRandom.current().nextInt(1,20);
			try {
				System.out.println(Thread.currentThread().getName()+" producing item:"+no);
				System.out.println(p1.tryTransfer(no));
				//p1.transfer(no);
				
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
class ConsumerTransfer extends Thread{

	String str="";
	BlockingQueue<Integer>bq=null;
	public ConsumerTransfer(TransferQueue<Integer> tf, String string) {
		super(string);
		this.bq=tf;
	}
	public void run()
	{
		while(true)
		{
			try{
				System.out.println(Thread.currentThread().getName()+" consuming item:"+bq.take());
				Thread.sleep(4000);
				
			}catch(Exception e){}
		}
	}
}