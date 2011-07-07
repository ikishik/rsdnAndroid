package net.ikishik.rsdnClient;

import com.neurospeech.wsclient.*;
import org.w3c.dom.*;

public class ChangeRequest extends WSObject
{
	
	private String _userName;
	public String getuserName(){
		return _userName;
	}
	public void setuserName(String value){
		_userName = value;
	}
	private String _password;
	public String getpassword(){
		return _password;
	}
	public void setpassword(String value){
		_password = value;
	}
	private java.util.Vector<RequestForumInfo> _subscribedForums = new java.util.Vector<RequestForumInfo>();
	public java.util.Vector<RequestForumInfo> getsubscribedForums(){
		return _subscribedForums;
	}
	public void setsubscribedForums(java.util.Vector<RequestForumInfo> value){
		_subscribedForums = value;
	}
	private String _ratingRowVersion;
	public String getratingRowVersion(){
		return _ratingRowVersion;
	}
	public void setratingRowVersion(String value){
		_ratingRowVersion = value;
	}
	private String _messageRowVersion;
	public String getmessageRowVersion(){
		return _messageRowVersion;
	}
	public void setmessageRowVersion(String value){
		_messageRowVersion = value;
	}
	private String _moderateRowVersion;
	public String getmoderateRowVersion(){
		return _moderateRowVersion;
	}
	public void setmoderateRowVersion(String value){
		_moderateRowVersion = value;
	}
	private java.util.Vector<Integer> _breakMsgIds = new java.util.Vector<Integer>();
	public java.util.Vector<Integer> getbreakMsgIds(){
		return _breakMsgIds;
	}
	public void setbreakMsgIds(java.util.Vector<Integer> value){
		_breakMsgIds = value;
	}
	private java.util.Vector<Integer> _breakTopicIds = new java.util.Vector<Integer>();
	public java.util.Vector<Integer> getbreakTopicIds(){
		return _breakTopicIds;
	}
	public void setbreakTopicIds(java.util.Vector<Integer> value){
		_breakTopicIds = value;
	}
	private Integer _maxOutput;
	public Integer getmaxOutput(){
		return _maxOutput;
	}
	public void setmaxOutput(Integer value){
		_maxOutput = value;
	}
	
	public static ChangeRequest loadFrom(Element root) throws Exception
	{
		if(root==null){
			return null;
		}
		ChangeRequest result = new ChangeRequest();
		result.load(root);
		return result;
	}
	
	
	protected void load(Element root) throws Exception
	{
		NodeList list;
		int i;
		this.setuserName(WSHelper.getString(root,"userName",false));
		this.setpassword(WSHelper.getString(root,"password",false));
		list = WSHelper.getElementChildren(root, "subscribedForums");
		if(list != null)
		{
			for(i=0;i<list.getLength();i++)
			{
				Element nc = (Element)list.item(i);
				_subscribedForums.addElement(RequestForumInfo.loadFrom(nc));
			}
		}
		this.setratingRowVersion(WSHelper.getString(root,"ratingRowVersion",false));
		this.setmessageRowVersion(WSHelper.getString(root,"messageRowVersion",false));
		this.setmoderateRowVersion(WSHelper.getString(root,"moderateRowVersion",false));
		list = WSHelper.getElementChildren(root, "breakMsgIds");
		if(list != null)
		{
			for(i=0;i<list.getLength();i++)
			{
				Element nc = (Element)list.item(i);
				_breakMsgIds.addElement(WSHelper.getInteger(nc,null,false));
			}
		}
		list = WSHelper.getElementChildren(root, "breakTopicIds");
		if(list != null)
		{
			for(i=0;i<list.getLength();i++)
			{
				Element nc = (Element)list.item(i);
				_breakTopicIds.addElement(WSHelper.getInteger(nc,null,false));
			}
		}
		this.setmaxOutput(WSHelper.getInteger(root,"maxOutput",false));
	}
	
	
	
	public Element toXMLElement(Element root)
	{
		Element e = root.getOwnerDocument().createElement("ChangeRequest");
		fillXML(e);
		return e;
	}
	
	public void fillXML(Element e)
	{
		if(_userName != null)
			WSHelper.addChild(e,"userName",String.valueOf(_userName),false);
		if(_password != null)
			WSHelper.addChild(e,"password",String.valueOf(_password),false);
		if(_subscribedForums != null)
			WSHelper.addChildArray(e,"subscribedForums",null,null, _subscribedForums);
		if(_ratingRowVersion != null)
			WSHelper.addChild(e,"ratingRowVersion",String.valueOf(_ratingRowVersion),false);
		if(_messageRowVersion != null)
			WSHelper.addChild(e,"messageRowVersion",String.valueOf(_messageRowVersion),false);
		if(_moderateRowVersion != null)
			WSHelper.addChild(e,"moderateRowVersion",String.valueOf(_moderateRowVersion),false);
		if(_breakMsgIds != null)
			WSHelper.addChildArray(e,null,"breakMsgIds","int",_breakMsgIds);
		if(_breakTopicIds != null)
			WSHelper.addChildArray(e,null,"breakTopicIds","int",_breakTopicIds);
		WSHelper.addChild(e,"maxOutput",String.valueOf(_maxOutput),false);
	}
	
}
