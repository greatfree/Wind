package edu.greatfree.container.p2p.registry;

import java.io.IOException;
import java.util.Calendar;

import org.greatfree.data.ServerConfig;
import org.greatfree.dip.container.cs.multinode.message.ChatApplicationID;
import org.greatfree.dip.container.p2p.message.ClusterIPRequest;
import org.greatfree.dip.container.p2p.message.IsRootOnlineRequest;
import org.greatfree.dip.container.p2p.message.LeaveClusterNotification;
import org.greatfree.dip.container.p2p.message.P2PChatApplicationID;
import org.greatfree.dip.container.p2p.message.PeerAddressRequest;
import org.greatfree.dip.container.p2p.message.PortRequest;
import org.greatfree.dip.container.p2p.message.RegisterPeerRequest;
import org.greatfree.dip.container.p2p.message.UnregisterPeerRequest;
import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.message.ServerMessage;
import org.greatfree.message.container.Notification;
import org.greatfree.message.container.Request;
import org.greatfree.server.container.ServerTask;
import org.greatfree.util.UtilConfig;

import edu.greatfree.container.p2p.message.ChatNotification;
import edu.greatfree.container.p2p.message.ChatPartnerRequest;
import edu.greatfree.container.p2p.message.ChatRegistryRequest;
import edu.greatfree.container.pe.message.PEChatApplicationID;
import edu.greatfree.container.pe.message.PollChatRequest;
import edu.greatfree.container.pe.message.PollChatResponse;

// Created: 01/12/2019, Bing Li
class RegistryTask implements ServerTask
{

	@Override
	public void processNotification(Notification notification)
	{
		switch (notification.getApplicationID())
		{
			case P2PChatApplicationID.LEAVE_CLUSTER_NOTIFICATION:
				System.out.println("LEAVE_CLUSTER_NOTIFICATION received @" + Calendar.getInstance().getTime());
				Register.leaveCluster((LeaveClusterNotification)notification);
				break;
		
			case ChatApplicationID.SHUTDOWN_SERVER_NOTIFICATION:
				System.out.println("SHUTDOWN_SERVER_NOTIFICATION received @" + Calendar.getInstance().getTime());
				try
				{
					RegisterServer.CS().stop(ServerConfig.SERVER_SHUTDOWN_TIMEOUT);
				}
				catch (ClassNotFoundException | IOException | InterruptedException | RemoteReadException e)
				{
					e.printStackTrace();
				}
				break;

				/*
				 * Chatting messages on polling are retained here. 06/05/2020, Bing Li
				 */
			case P2PChatApplicationID.CHAT_NOTIFICATION:
				System.out.println("CHAT_NOTIFICATION received @" + Calendar.getInstance().getTime());
				ChatNotification cn = (ChatNotification)notification;
				ChatContainer.POLL().keepMessage(cn.getSenderKey(), cn.getMessage());
				break;
		}
	}

	@Override
	public ServerMessage processRequest(Request request)
	{
		switch (request.getApplicationID())
		{
			case P2PChatApplicationID.PEER_CHAT_REGISTRY_REQUEST:
				System.out.println("PEER_CHAT_REGISTRY_REQUEST received @" + Calendar.getInstance().getTime());
				return Register.registerChat((ChatRegistryRequest)request);
				
			case P2PChatApplicationID.PEER_CHAT_PARTNER_REQUEST:
				System.out.println("PEER_CHAT_PARTNER_REQUEST received @" + Calendar.getInstance().getTime());
				return Register.checkChatPartner((ChatPartnerRequest)request);
				
			case P2PChatApplicationID.REGISTER_PEER_REQUEST:
				System.out.println("REGISTER_PEER_REQUEST received @" + Calendar.getInstance().getTime());
				return Register.registerPeer((RegisterPeerRequest)request);
				
			case P2PChatApplicationID.PORT_REQUEST:
				System.out.println("PORT_REQUEST received @" + Calendar.getInstance().getTime());
				return Register.retrievePort((PortRequest)request);
				
			case P2PChatApplicationID.CLUSTER_IP_REQUEST:
				System.out.println("CLUSTER_IP_REQUEST received @" + Calendar.getInstance().getTime());
				return Register.retrieveClusterIP((ClusterIPRequest)request);
				
			case P2PChatApplicationID.PEER_ADDRESS_REQUEST:
				System.out.println("PEER_ADDRESS_REQUEST received @" + Calendar.getInstance().getTime());
				return Register.retrievePeerAddress((PeerAddressRequest)request);
				
			case P2PChatApplicationID.IS_ROOT_ONLINE_REQUEST:
				System.out.println("IS_ROOT_ONLINE_REQUEST received @" + Calendar.getInstance().getTime());
				return Register.isRootOnline((IsRootOnlineRequest)request);
				
			case P2PChatApplicationID.UNREGISTER_PEER_REQUEST:
				System.out.println("UNREGISTER_PEER_REQUEST received @" + Calendar.getInstance().getTime());
				return Register.unregisterPeer((UnregisterPeerRequest)request);
				
				/*
				 * Chatting messages on polling are returned here. 06/05/2020, Bing Li
				 */
			case PEChatApplicationID.POLL_CHAT_REQUEST:
				System.out.println("POLL_CHAT_REQUEST received @" + Calendar.getInstance().getTime());
				PollChatRequest pcr = (PollChatRequest)request;
				if (!ChatContainer.POLL().isEmpty(pcr.getPartnerKey()))
				{
					String msg = ChatContainer.POLL().getMessage(pcr.getPartnerKey());
					ChatContainer.POLL().clear(pcr.getPartnerKey());
					return new PollChatResponse(msg);
				}
				else
				{
					return new PollChatResponse(UtilConfig.EMPTY_STRING);
				}
		}
		return null;
	}
}
