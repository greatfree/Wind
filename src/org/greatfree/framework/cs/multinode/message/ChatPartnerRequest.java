package org.greatfree.framework.cs.multinode.message;

import org.greatfree.chat.message.ChatMessageType;
import org.greatfree.message.ServerMessage;

/*
 * The request asks the chatting registry server to obtain the potential chatting partners. 04/16/2017, Bing Li
 */

// Created: 04/16/2017, Bing Li
public class ChatPartnerRequest extends ServerMessage
{
	private static final long serialVersionUID = -1587573982719535239L;
	
	private String userKey;

	public ChatPartnerRequest(String userKey)
	{
		super(ChatMessageType.CS_CHAT_PARTNER_REQUEST);
		this.userKey = userKey;
	}

	public String getUserKey()
	{
		return this.userKey;
	}
}
