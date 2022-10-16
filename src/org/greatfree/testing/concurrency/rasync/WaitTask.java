package org.greatfree.testing.concurrency.rasync;

import org.greatfree.concurrency.Reader;
import org.greatfree.concurrency.RunnerTask;

/**
 * 
 * @author libing
 * 
 * 08/01/2022
 *
 */
class WaitTask extends RunnerTask
{
	private Reader<Query, Answer> reader;
//	private Query query;
	private final String collaboratorKey;
	
//	public WaitTask(Reader<Query, Answer> reader, Query query)
	public WaitTask(Reader<Query, Answer> reader, String collaboratorKey)
	{
		this.reader = reader;
//		this.query = query;
		this.collaboratorKey = collaboratorKey;
	}

	@Override
	public void run()
	{
		System.out.println("WaitTask: waiting for the answer ...");
//		Answer answer = (Answer)this.reader.waitForResponse(this.query.getCollaboratorKey(), 6000);
		Answer answer = (Answer)this.reader.waitForResponse(this.collaboratorKey, 6000);
		if (answer != null)
		{
			System.out.println("WaitTask: response is " + answer.getAnswer());
		}
		else
		{
			System.out.println("response is null");
		}
	}

	@Override
	public int getWorkload()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void dispose() throws InterruptedException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose(long timeout) throws InterruptedException
	{
		// TODO Auto-generated method stub
		
	}
}
