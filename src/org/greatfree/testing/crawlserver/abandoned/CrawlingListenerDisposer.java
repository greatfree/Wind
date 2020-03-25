package org.greatfree.testing.crawlserver.abandoned;

import org.greatfree.reuse.RunnerDisposable;
import org.greatfree.testing.crawlserver.CrawlingListener;

/*
 * The class is responsible for disposing the instance of CrawlingListener by invoking its method of shutdown(). It works with the thread container, Threader or Runner. 11/23/2014, Bing Li
 */

// Created: 11/11/2014, Bing Li
public class CrawlingListenerDisposer implements RunnerDisposable<CrawlingListener>
{
	/*
	 * Dispose the instance of CrawlingListener. 11/23/2014, Bing Li
	 */
	@Override
	public void dispose(CrawlingListener r) throws InterruptedException
	{
		r.dispose();
	}

	/*
	 * Dispose the instance of CrawlingListener. The method does not make sense to CrawlingListener. Just leave it here for the requirement of the interface, RunDisposable<CrawlingListener>. 11/23/2014, Bing Li
	 */
	@Override
	public void dispose(CrawlingListener r, long time) throws InterruptedException
	{
		r.dispose();
	}
}
