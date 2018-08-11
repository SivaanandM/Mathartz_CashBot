package com.cashbot.engine;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.sft.feedprovider.MarketDataProvider;

public class FeedBlockingQueue{

	public  static FeedBlockingQueue feedBlockingQueue = null;
	static BlockingQueue<Map<String, MarketDataProvider>> blockingQueue = new ArrayBlockingQueue<Map<String, MarketDataProvider>>(100000);
	
	private FeedBlockingQueue(){
		
	}
	
	public static FeedBlockingQueue getInstance(){
		if(feedBlockingQueue == null){
			synchronized (FeedBlockingQueue.class) {
				if(feedBlockingQueue == null){
					feedBlockingQueue = new FeedBlockingQueue();
				}
			}
		}
		 return feedBlockingQueue;
	}

	public Map<String, MarketDataProvider> take() {
		Map<String, MarketDataProvider> takeout = null;
		try {
			takeout = blockingQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return takeout;
	}

	public void put(String symbol,MarketDataProvider blockingQueueAddMessage) {
		try {
			Map<String, MarketDataProvider> addSymbolDetails=new HashMap<String, MarketDataProvider>();
			addSymbolDetails.put(symbol, blockingQueueAddMessage);
			blockingQueue.put(addSymbolDetails);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
