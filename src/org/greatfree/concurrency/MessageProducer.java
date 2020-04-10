package org.greatfree.concurrency;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.greatfree.client.OutMessageStream;
import org.greatfree.data.ServerConfig;
import org.greatfree.message.ServerMessage;
import org.greatfree.server.ServerDispatcher;

/*
 * This is a producer/consumer pattern class to input received messages into a concurrency mechanism, the server dispatcher, smoothly. 07/30/2014, Bing Li
 */

// Created: 07/30/2014, Bing Li
//public class MessageProducer<Consumer extends ServerMessageDispatcher<ServerMessage>> implements Runnable
// public class MessageProducer<Consumer extends ServerDispatcher<ServerMessage>> implements Runnable
public class MessageProducer<Consumer extends ServerDispatcher<ServerMessage>> extends RunnerTask
{
	// A dispatcher that processes messages concurrently. 08/04/2014, Bing Li
	private Consumer consumer;
	// A queue to schedule messages in the way of first-in-first-out. 08/04/2014, Bing Li
	private Queue<OutMessageStream<ServerMessage>> queue;
	// The collaborator supports the concurrency control. 08/04/2014, Bing Li
	private Sync collaborator;
	// Timeout for checking incoming messages. 05/28/2018, Bing Li
//	private final long timeout;

	// Initializing ... 08/04/2014, Bing Li
//	public MessageProducer(Consumer consumer, long timeout)
	public MessageProducer(Consumer consumer)
	{
		// The consumer is defined and initialized outside the message producer. 08/22/2014, Bing Li
		this.consumer = consumer;
//		System.out.println("MessageProducer: consumer-hashCode = " + this.consumer.hashCode());
		// Initialize a concurrency controlled queue to keep the messages in a thread-safe way. 08/22/2014, Bing Li
		this.queue = new LinkedBlockingQueue<OutMessageStream<ServerMessage>>();
		// Initialize a collaborator for the notify-wait mechanism. 08/22/2014, Bing Li
		this.collaborator = new Sync();
		// Initialize the timeout. 05/28/2018, Bing Li
//		this.timeout = timeout;
	}

	/*
	 * Disposing ... 08/04/2014, Bing Li
	 */
	@Override
	public void dispose(long timeout) throws InterruptedException
	{
		/*
		// Set the shutdown flag to true. 08/22/2014, Bing Li
		this.collaborator.setShutdown();
		// Notify all the threads that hold the lock to check the shutdown flag. 08/22/2014, Bing Li
		this.collaborator.signalAll();
		*/

		// The above two lines are combined and executed atomically to shutdown the dispatcher. 02/26/2016, Bing Li
		this.collaborator.shutdown();

		// Since the message producer is shutdown, the queue can be cleared. 08/22/2014, Bing Li
		this.queue.clear();
		// Shutdown the consumer. 08/22/2014, Bing Li
//		this.consumer.shutdown(timeout);
		this.consumer.dispose(timeout);
	}

	/*
	 * Push new messages into the queue and notify the associated thread to process. 08/04/2014, Bing Li
	 */
	public synchronized void produce(OutMessageStream<ServerMessage> message)
	{
		// Push the messages into the queue. 08/22/2014, Bing Li
		this.queue.add(message);
		// Notify the running thread to process the message. 08/22/2014, Bing Li
		this.collaborator.signal();
	}

	/*
	 * 	Wait for the available messages to process. 08/04/2014, Bing Li
	 */
	public void run()
	{
		OutMessageStream<ServerMessage> message;
		// An always running loop to keep the thread alive all the time until it is disposed explicitly. 08/22/2014, Bing Li
		while (!this.collaborator.isShutdown())
		{
			// Check whether the queue is empty or not. If messages exist in the queue, all of them must be processed until the queue is empty. 08/22/2014, Bing Li
			while (!this.queue.isEmpty())
			{
				// Dequeue a message in the queue. 08/22/2014, Bing Li
				message = this.queue.poll();
				// The condition is added to avoid processing incoming messages after the server dispatcher is shut down. 11/17/2019, Bing Li
				if (!this.consumer.isDown())
				{
					// Process the message by the consumer, which is defined outside the class. 08/22/2014, Bing Li
					this.consumer.consume(message);
//					System.out.println("MessageProducer-run(): message received: type = " + message.getMessage().getType());
//					System.out.println("MessageProducer-run(): consumer-hashCode = " + this.consumer.hashCode());
					this.consumer.process(message);
				}
			}
			// If the message queue is empty, the thread needs to wait until messages are received. 08/22/2014, Bing Li
			this.collaborator.holdOn(ServerConfig.CHECK_MESSAGE_TIMEOUT);
		}
	}

	@Override
	public void dispose() throws InterruptedException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getWorkload()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
