package code;

import java.util.*;

import exceptions.EmptySetException;
import exceptions.ExchangeNotFoundException;
import exceptions.LinkedListOutofBoundsException;
import exceptions.MobileNotFoundException;
import exceptions.MobileSwitchedOffException;
import exceptions.NotABaseStationException;
import exceptions.NotHavingChildrenExchangeException;

import java.io.*;
import java.lang.*;
@SuppressWarnings("unchecked")

//////////////FUNCTIONS////////////////
//RoutingMapTree(int level, Exchange e)
//RoutingMapTree()
//Exchange return_root()
//create_general_children
//MobilePhoneSet get_phone_set
//create_mobilePhone_children
//Boolean containsNode(Exchange)
//switchOn(Mobile, Exchange)
//switchOff(MobilePhone)
//Exchange findNodeWithId(int uid, RoutingMapTree tree)
//performAction(String message)
//addExchange(int a, int b)
///////////////////////////////////////

public class RoutingMapTree {
	public Exchange root = new Exchange(0,0);
	public int maxLevel;
	public static MobilePhoneSet superSet = new MobilePhoneSet();
	public RoutingMapTree(){  //Creating a new Routing Map Tree and initializing i with a root :/
		this.root.unique_id = 0; //For root unique_id is 0
		this.maxLevel = 0;
	}
	public RoutingMapTree(int level, Exchange e){
		this.root = e;
		this.root.setLevel(level);
	}
	// public void create_root_children(Exchange r){  //No Need of this function
	// 	this.root.createChildrenExchange(r);
	// 	r.setLevel(1);
	// 	this.maxLevel += 1;
	// 	//root.mobiles.set.list = root.mobiles.set.Union(r.mobiles.set);
	// }
	public Exchange return_root(){
		return this.root;
	}
	public void create_general_children(Exchange r, Exchange s){
		if(r.getLevel() == maxLevel){maxLevel += 1;}
		r.createChildrenExchange(s);
		//s.setLevel(r.getLevel() + 1); //No need
		//if(r.child(0).getLevel() == 
		// if(r.child(0) == s && r.child(0).returnId() !=9){ //When on a level first Exchange is appearing
		// 	maxLevel += 1;
		// }
		//if(this.ExchangeOnLeveli(r.getLevel()+1) == false){maxLevel += 1;}

	}
	public MobilePhoneSet get_phone_set(Exchange a){
		return a.residentSet();
	}
	public void create_MobilePhone_children(Exchange r, MobilePhone s){
		r.createChildrenMobile(s);
	}
	public Boolean containsNode(Exchange b){
		if(b.numChildren() == 0){return false;}
		else return true;
	}
	// A very Bad attemp to do whatever the hell i was trying to do 
	// public Boolean ExchangeOnLeveli(int i){ //Here I am assuming the pattern of introducing new Exchanges in a linear way on a level. So this is DANGEROUS
	// 	int r = 0;
	// 	//if(i == 0){return true;}
	// 	Exchange ere = this.root;
	// 	while(true){
	// 		if(ere.child(0) == null && ere.getLevel() != i){return false;}
	// 		if(ere.child(0).getLevel() == i){
	// 			//r=i;
	// 			return true;
	// 		} else {
	// 			ere = ere.child(0);
	// 		}
	// 	}
	// }

	public void switchOn(MobilePhone a, Exchange b) throws MobileSwitchedOffException {
		if(a.status() == false){throw new MobileSwitchedOffException("~Phone is still off~");} //---------------->>>>>>>
		else {
			a.switchOn();
			b.createChildrenMobile(a);		
		}
	}
	public void switchOff(MobilePhone a){
		if(a.status()){a.switchedOff();}
		else System.out.println("--Sorry, phone is already On--");

	}
/////////////////////////////////////////////////////////////////////////////////////////////////
	public Exchange findPhone(MobilePhone m) throws MobileSwitchedOffException{
		// if(m.status()==false){
		// 	throw new MobileSwitchedOffException("Error - Mobile with identifier " + m.number() + " is not reachable.");
		// }
		return m.location();//Error handled in MobilePhone location() function
	}
	public Exchange lowestRouter(Exchange a, Exchange b) throws ExchangeNotFoundException{
		// if(a.getLevel()!=this.maxLevel || b.getLevel()!=this.maxLevel){
		// 	throw new ExchangeNotFoundException("Either of them are not Level 0 Exchange");
		// }
		if(a.returnId() == b.returnId()){return a;}
		if(a.parent().returnId() == b.parent().returnId()){
			return a.parent();
		} else {return lowestRouter(a.parent(), b.parent());}
	}

	public ExchangeList routeCall(MobilePhone a, MobilePhone b) throws ExchangeNotFoundException, MobileSwitchedOffException{
		Exchange ee = new Exchange();
		Exchange ee1 = new Exchange();
		ExchangeList l = new ExchangeList();
		ExchangeList l1 = new ExchangeList();
		ee = a.location();
		ee1 = b.location();
		if(a.location() == b.location()){l.addToSetExchanges(a.location());}
		else {
			Exchange bb = lowestRouter(a.location(), b.location());
			for(int i=0;i<maxLevel-bb.getLevel()+1;i++){
				l.addToSetExchanges(ee);
				ee = ee.parent();
			}
			//Exchange bb1 = lowestRouter(a.location(), b.location());
			for(int j=0;j<maxLevel-bb.getLevel();j++){
				l1.addToSetExchanges(ee1);
				ee1 = ee1.parent();
			}
			for(int k=0;k<maxLevel-bb.getLevel();k++){
				l.addToSetExchanges(l1.getithExchange(maxLevel - bb.getLevel() -k-1 ));
			}

		}
		
		return l;
	}



	public void movePhone(MobilePhone a, Exchange b) throws MobileSwitchedOffException, ExchangeNotFoundException, NotABaseStationException, LinkedListOutofBoundsException, EmptySetException{
		if(a.status()==null){throw new MobileSwitchedOffException("Error - Mobile with identifier " + a.number() + " is not reachable.");}
		if(b.getLevel()!=this.maxLevel){throw new NotABaseStationException("Exchange with identifier " + b.returnId() + " is not a Base station");}
		a.location().removeFromExchange(a); //remove from base station
		a.removeFromBaseStation();
		this.create_MobilePhone_children(b, a);
	}
	public String returnNew(String a){
		String b = Character.toString(a.charAt(0)).toUpperCase();
		return "query" + b + a.substring(1);
	}
///////////////////////////////////////////////////////////////////////////////////////////////
	public String performAction(String actionMessage){
		String out = "NothingHappened";
		try{
			String str[] = actionMessage.split(" ");
			String strA = str[0];
			String strB = str[1];
			String strC = null;

			if(strA != "addExchange" && strA != "queryNthChild" && strA != "switchOnMobile" && strA != "queryMobilePhoneSet"){
				out = "Wrong operation";
			}
			int a=Integer.parseInt(strB);
			int b=0;
			if(str.length==3){
				strC = str[2];
				b=Integer.valueOf(strC);
			}
			///////////ASSIGNMENT-1/////////////
			if(strA.equals("addExchange")){
				try{
					addExchange(a,b, actionMessage);
					//System.out.println(maxLevel);
				}catch(ExchangeNotFoundException e){
					System.out.println("No such exchange found");
				}
			}
			else if(strA.equals("switchOnMobile")){
				try{
					switchOnMobile(a,b);
				}
				catch(NotABaseStationException e){System.out.println(actionMessage + e);}
				//catch(MobileSwitchedOffException e){System.out.println("Error - MobiePhone " + a + "is already switched on");}
				//return ;
			}
			else if(strA.equals("switchOffMobile")){
				switchOffMobile(a);
				//return ;
			}
			else if(strA.equals("queryNthChild")){
				try{
					int id = queryNthChild(a,b,actionMessage);
					String hu = Integer.toString(id);
					out = actionMessage + ": " + hu;
					//System.out.println(out);

				}catch(NotHavingChildrenExchangeException e){System.out.println("The Exchange object doesn't have any child of type Exchange");}
			}
			else if(strA.equals("queryMobilePhoneSet")){
				try{
					String stre = queryMobilePhoneSet(a);
					out = actionMessage + ": " + stre;
					char ttr[] = new char[out.length()-2];
					for(int yr=0;yr<out.length()-2;yr++){
						ttr[yr] = out.charAt(yr);
					}
					String g = new String(ttr);
					out = g;
					//System.out.println(g);
				}catch(NotABaseStationException e){System.out.println("Error - Not a base station");}
			}
			///////////ASSIGNMENT-2/////////////
			else if(strA.equals("queryFindPhone") || strA.equals("findPhone")){
				if(RoutingMapTree.superSet.returnSet().showAll().size() == 0){throw new EmptySetException("Error - No mobile phone with identifier " + a + " found in the network");}
				for(int t=0;t<RoutingMapTree.superSet.returnSet().showAll().size();t++){
					if(RoutingMapTree.superSet.getMobilePhone(t).number() == a){
						int baseid = this.findPhone(RoutingMapTree.superSet.getMobilePhone(t)).returnId();
						String hii = Integer.toString(baseid);
						out = "queryFindPhone " + Integer.toString(a) + ": " + hii;
					}
				}
				//System.out.println(out);
			}
			else if(strA.equals("queryLowestRouter") || strA.equals("lowestRouter") ){
				//System.out.println(maxLevel);
				int commonExchangeId = lowestRouter(findNodeWithId(a,this), findNodeWithId(b,this)).returnId();
				out ="queryLowestRouter " + Integer.toString(a) + " " + Integer.toString(b) + ": " + Integer.toString(commonExchangeId);
				//System.out.println(out.length());
			}
			else if(strA.equals("movePhone")){
				for(int h=0;h<RoutingMapTree.superSet.returnSet().showAll().size();h++){
					if(RoutingMapTree.superSet.getMobilePhone(h).number() == a){
						//System.out.println(this.findNodeWithId(b,this).getLevel());
						this.movePhone(RoutingMapTree.superSet.getMobilePhone(h), this.findNodeWithId(b, this));
						break;
					}
				}
			}
			else if(strA.equals("queryFindCallPath") || strA.equals("findCallPath")){
				out = "queryFindCallPath " + Integer.toString(a) + " " +Integer.toString(b) + ": ";
				ExchangeList tt = new ExchangeList();
				for(int h=0;h<RoutingMapTree.superSet.returnSet().showAll().size();h++){
					for(int hh=0;hh<RoutingMapTree.superSet.returnSet().showAll().size();hh++){
						if(RoutingMapTree.superSet.getMobilePhone(h).number() == a && RoutingMapTree.superSet.getMobilePhone(hh).number() == b){
							//System.out.println(RoutingMapTree.superSet.getMobilePhone(h).status());
							tt = routeCall(RoutingMapTree.superSet.getMobilePhone(h), RoutingMapTree.superSet.getMobilePhone(hh));
						}
					}
				}
				for(int uy=0;uy<tt.totalElementsinExchangeList();uy++){
					out = out + Integer.toString(tt.getithExchangeId(uy)) + ", ";
				}
				out = out.substring(0,out.length()-2);
				//System.out.println(out);
			}
			else
			{
				System.out.println(actionMessage + ": Wrong Input");
				//return;
			}
			//return out;
		}
		catch(MobileNotFoundException e){out = (returnNew(actionMessage) + ": " + e).toString().replace(" MobileNotFoundException: ", " ");}
		catch(MobileSwitchedOffException e){out = (returnNew(actionMessage) + ": " + e).toString().replace(" MobileSwitchedOffException: ", " ");}
		catch(ExchangeNotFoundException e){out = (returnNew(actionMessage) + ": " + e).toString().replace(" ExchangeNotFoundException: ", " ");}
		catch(EmptySetException e){out = (returnNew(actionMessage) + ": " + e).toString().replace(" EmptySetException: ", " ");}
		catch(LinkedListOutofBoundsException e){out = (returnNew(actionMessage) + ": " + e).toString().replace(" LinkedListOutofBoundsException: ", " ");}
		catch(NotABaseStationException e){out = (returnNew(actionMessage) + ": " + e).toString().replace(" NotABaseStationException: ", " ");}
		return out;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////	
	public Exchange findNodeWithId(int uid, RoutingMapTree tree) throws ExchangeNotFoundException{
		if(tree.return_root().returnId() == uid){
			return tree.return_root();
		}
		if(tree.return_root().numChildren() == 0){
			return null;
		}

		Exchange temp = new Exchange();
		for(int i=0;i<tree.return_root().numChildren();i++){
			temp = findNodeWithId(uid,tree.return_root().subtree(tree.return_root().getLevel()+1, i)); //SEEEE THIIIS
			if(temp!=null){break;}
		}
		if(temp == null && tree.return_root().returnId() == 0){
			throw new ExchangeNotFoundException("Exchange Not found");
		}
		return temp;
	}

	public void addExchange(int a, int b, String s) throws ExchangeNotFoundException, LinkedListOutofBoundsException, EmptySetException/*, OnlyChildNoExchangeException*/{
		try{
			Exchange ann = findNodeWithId(a, this);
			//if(ann == null){throw new ExchangeNotFoundException("Error - No such Exchange is found");}
			Exchange enw = new Exchange(b);
			//ann.createChildrenExchange(enw);
			this.create_general_children(ann, enw);
		}
		catch(ExchangeNotFoundException e){
			System.out.println(s+": Error - Parent Exchange is not present");
		}
		catch(NullPointerException e){}
	}
	public void switchOnMobile(int a, int b)throws NotABaseStationException, ExchangeNotFoundException, LinkedListOutofBoundsException, EmptySetException, MobileNotFoundException{
		// int flag = 0;
		Exchange ex = findNodeWithId(b,this);
		int t = 1;
		MobilePhone hg = new MobilePhone(a);
		for(int u=0;u<RoutingMapTree.superSet.returnSet().showAll().size();u++){
			if(RoutingMapTree.superSet.getMobilePhone(u).number() == a){
				RoutingMapTree.superSet.getMobilePhone(u).switchOn();
				t = 0;
			}
		}
		if(t==1){
			hg.switchOn();
			ex.createChildrenMobile(hg);
		}
	}

	public void switchOffMobile(int a)throws MobileSwitchedOffException{
		int n=0;
		//System.out.println(RoutingMapTree.superSet.returnSet().showAll().size());
		for(int t=0;t<RoutingMapTree.superSet.returnSet().showAll().size();t++){
			if(RoutingMapTree.superSet.getMobilePhone(t).number() == a){
				RoutingMapTree.superSet.getMobilePhone(t).switchedOff();n=1;break;
			}
		}
		if(n==0){throw new MobileSwitchedOffException("Error - Mobile phone with identifier " + a + " is already switched off.");}

	}

	public int queryNthChild(int a, int b, String s) throws NotHavingChildrenExchangeException, ExchangeNotFoundException, IndexOutOfBoundsException{
		int iid = 0;
		try{
			Exchange aim = findNodeWithId(a,this);
			if(aim.numChildren() == 0){
				throw new NotHavingChildrenExchangeException("This Exchange doesn't have a child");
			}
			Exchange arr = (Exchange)aim.child(b);
			if(arr == null){throw new ExchangeNotFoundException("Error");}
			iid = arr.returnId();
		}
		catch(ExchangeNotFoundException e){
			System.out.println(s + ": Error - Either of the two exchanges doesn't exists");
		}	
		catch(IndexOutOfBoundsException e){
			System.out.println("Index is out of bounds");
		}
		return iid;
	}
	public String queryMobilePhoneSet(int a) throws ExchangeNotFoundException, IndexOutOfBoundsException, NotABaseStationException{
		String str = "";
		if(a==0){
			for(int y=0;y<RoutingMapTree.superSet.size();y++){
				if(RoutingMapTree.superSet.getMobilePhone(y).status()){
					str = str.concat(RoutingMapTree.superSet.getMobilePhone(y).number() + ", ");
				}
			}
			return str;
		}
		Exchange tee = findNodeWithId(a, this);
		int num = tee.returnCunt();
		if(num==0 && tee.numChildren()!= 0){ // Exchange but not a base station , still return mobiles under it
			for(int u=0;u<tee.numChildren();u++){
				str = str.concat(queryMobilePhoneSet(tee.child(u).returnId()));
			}
		}
		if(num!=0){
			for(int n=0;n<num;++n){
				if(tee.giveithMobilePhone(n).status()!=false/* && n<(num-1)*/){
					str = str.concat(Integer.toString(tee.giveIdMobilePhone(n)) + ", ");
				}
			}
		}
		return str;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////

	// public static void main(String[] args) {
	// 	RoutingMapTree tree = new RoutingMapTree();
	// 	tree.addExchange(0 ,1);
	// 	tree.addExchange (0 ,2);
	// 	tree.addExchange (0 ,3);
	// 	tree.lowestRouter (2 ,3);
	// 	tree.queryNthChild (0 ,0);
	// 	tree.queryNthChild (0 ,2);
	// 	tree.addExchange (1 ,4);
	// 	tree.addExchange (1 ,5);
	// 	tree.lowestRouter (4, 5);
	// 	tree.addExchange (2 ,6);
	// 	tree.addExchange (2 ,7);
	// 	tree.addExchange (2 ,8);
	// 	tree.addExchange (3 ,9);
	// 	tree.queryNthChild (2 ,0);
	// 	tree.queryNthChild (3 ,0);
	// 	tree.findPhone (989);
	// 	tree.switchOnMobile (989, 4);
	// 	tree.findPhone (989);
	// 	tree.switchOnMobile (876 ,4);
	// 	tree.queryMobilePhoneSet (4);
	// 	tree.queryMobilePhoneSet (1);
	// 	tree.switchOnMobile (656 ,5);
	// 	tree.switchOnMobile (54 ,5);
	// 	tree.queryMobilePhoneSet (1);
	// 	tree.switchOffMobile (656);
	// 	tree.queryMobilePhoneSet (1);
	// 	tree.switchOnMobile (213 ,6);
	// 	tree.switchOnMobile (568 ,7);
	// 	tree.switchOnMobile (897 ,8);
	// 	tree.switchOnMobile (295 ,8);
	// 	tree.switchOnMobile (346, 9);
	// 	tree.queryMobilePhoneSet (0);
	// 	tree.findCallPath (989, 876);
	// 	tree.movePhone (989 ,7);
	// 	tree.findCallPath (989 ,876);
	// 	tree.findCallPath (876 ,989);
	// 	tree.switchOffMobile (989);
	// 	tree.findCallPath (876 ,989);
	// 	tree.findCallPath (876, 346);
	// }
}
