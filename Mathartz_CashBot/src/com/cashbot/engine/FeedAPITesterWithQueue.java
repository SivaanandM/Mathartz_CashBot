package com.cashbot.engine;

import com.cashbot.commons.CommonObjects;
import com.sft.feedprovider.Executor;
import com.sft.feedprovider.FeedService;
import com.sft.feedprovider.MarketDataProvider;

public class FeedAPITesterWithQueue implements FeedService {
	static String sleep = "100";
	private FeedBlockingQueue feedBlockingQueue = null;
	Executor executor;
	static FeedConsumer feedconsumer=null;
	int feedno =0;
	public void startfeed()
	{
		try 
		{
			Thread t2 = new Thread(new Runnable()
	        {
	            @Override
	            public void run()
	            {
	                feedconsumer=new FeedConsumer();
	                feedconsumer.start();
	            }
	        });
	        t2.start();
	        try {
				t2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new FeedAPITesterWithQueue().feedrequest();
		}
		catch(Exception ex)
		{
			System.out.print(ex.toString());
		}
	}
	
	public void feedrequest() {
		
		executor = new Executor();
		executor.start(this);
		
		if (CommonObjects.Globaluniqueheadid != null)
		{
			for (String[] headid : CommonObjects.Globaluniqueheadid) 
			{
				executor.subscribe(headid[0], headid[1]);
			}
		}
		
		try {
			Thread.sleep(Long.parseLong(sleep) * 1000);
		} catch (InterruptedException e) 
		{ 
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void onFeed(MarketDataProvider subject, final String symbol) 
	{
		FeedBlockingQueue.getInstance().put(symbol,subject);
	}
	
	public void stopfeed()
	{
		try 
		{
			executor.stop();
			System.out.println("-------------Stopping Feed---------");
		}
		catch(Exception ex)
		{
			System.out.print(ex.toString());
		}
	}
	
	//	public static void main(String[] args) {
	//  Thread t2 = new Thread(new Runnable()
	//  {
	//      @Override
	//      public void run()
	//      {
	//          feedconsumer=new FeedConsumer();
	//          feedconsumer.start();
	//      }
	//  });
	//  t2.start();
	//  try {
	//		t2.join();
	//	} catch (InterruptedException e) {
	//		// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
	//	new FeedAPITesterWithQueue().feedrequest();
	//
	//
	//}
}