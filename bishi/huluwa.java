package bishi;
import java.util.*;

public class Person implements Runnable{
	private volatile int i = 7;
	private String name;
	private Integer cloth;
	public Person(String name, Integer cloth) {
	           this.name=name;
	           this.cloth=cloth;
	    
	}
	public void run() {
		while(true) {
			synchronized(this) {
				notify();
				try {
					Thread.sleep(10);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				if(i > 0) {
					int one = Integer.valueOf(this.name.split("-")[1]);
					if(i == one){
						System.out.println(this.name + "拿到了他的衣服");	
						break;

					}
					i--;
					
				}
			}
		}
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		List<Person> brothers = new ArrayList<Person>() {
		    {
		        add(new Person("二娃-2", null));
		        add(new Person("七娃-7", null));
		        add(new Person("大娃-1", null));
		        add(new Person("四娃-4", null));
		        add(new Person("六娃-6", null));
		        add(new Person("三娃-3", null));
		        add(new Person("五娃-5", null));
		    }
		};
		for(int i = 0; i < 7; i++) {
			Thread th = new Thread(brothers.get(i));
			th.start();			
		}
	}

}

