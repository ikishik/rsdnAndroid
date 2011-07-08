package net.ikishik.rsdnClient;

import com.neurospeech.wsclient.*;
import org.w3c.dom.*;

public class JanusAT extends Soap12WebService{
	
	
	public JanusAT(){
		//this.setUrl("/ws/janusAT.asmx");
		this.setUrl("http://rsdn.ru/ws/janusAT.asmx");
	}
	
	
	public TopicResponse GetTopicByMessage(TopicRequest topicRequest) throws Exception 
	{
		SoapRequest req;
		req = buildSoapRequest("GetTopicByMessage","http://rsdn.ru/Janus/","http://rsdn.ru/Janus/GetTopicByMessage",null);
		Element __request = req.method;
		WSHelper.addChildNode(__request,"topicRequest",null,topicRequest);
		SoapResponse sr = getSoapResponse(req);
		Element __response = (Element)sr.body.getFirstChild().getFirstChild();
		return TopicResponse.loadFrom(__response);
	}
	
	public ChangeResponse GetNewData(ChangeRequest changeRequest) throws Exception 
	{
		SoapRequest req;
		req = buildSoapRequest("GetNewData","http://rsdn.ru/Janus/","http://rsdn.ru/Janus/GetNewData",null);
		Element __request = req.method;
		WSHelper.addChildNode(__request,"changeRequest",null,changeRequest);
		SoapResponse sr = getSoapResponse(req);
		Element __response = (Element)sr.body.getFirstChild().getFirstChild();
		return ChangeResponse.loadFrom(__response);
	}
	
	public ForumResponse GetForumList(ForumRequest forumRequest) throws Exception 
	{
		SoapRequest req;
		req = buildSoapRequest("GetForumList","http://rsdn.ru/Janus/","http://rsdn.ru/Janus/GetForumList",null);
		Element __request = req.method;
		WSHelper.addChildNode(__request,"forumRequest",null,forumRequest);
		SoapResponse sr = getSoapResponse(req);
		Element __response = (Element)sr.body.getFirstChild().getFirstChild();
		return ForumResponse.loadFrom(__response);
	}
	
}
