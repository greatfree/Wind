package org.greatfree.testing.message;

import org.greatfree.message.ServerMessage;

/*
 * This is the notification for a crawler to unregister on the coordinator. 11/23/2014, Bing Li
 */

// Created: 11/23/2014, Bing Li
public class UnregisterCrawlServerNotification extends ServerMessage
{
	private static final long serialVersionUID = -2742181590283782035L;
	
	// The key of the crawler server. Here, DC stands for the term, distributed component. 11/23/2014, Bing Li
	private String dcKey;

	/*
	 * Initialize. 11/23/2014, Bing Li
	 */
	public UnregisterCrawlServerNotification(String dcKey)
	{
		super(MessageType.UNREGISTER_CRAWL_SERVER_NOTIFICATION);
		this.dcKey = dcKey;
	}

	/*
	 * Expose the key of the crawler. 11/23/2014, Bing Li
	 */
	public String getDCKey()
	{
		return this.dcKey;
	}
}
