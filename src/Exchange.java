package code;

import java.util.*;

import exceptions.EmptySetException;
import exceptions.LinkedListOutofBoundsException;

import java.io.*;

/////////FUNCTIONS///////////
//Exchange(int id, int level)
//Exchange(int id)
//Exchange()
//void setLevel(int)
//int getLevel()
//int returnId()
//createChildrenExchange(Exchange)
//createChildrenMobile(MobilePhone)
//parent()
//setParent(Exchange)
//removeFomExchange(MobilePhone)
//Object child()
//int numChildren
//Boolean isRoot()
/////////////////////////////

@SuppressWarnings("unchecked")

// class EmptySetException extends Exception {

// }
// class LinkedListOutofBoundsException extends Exception {

// }

public class Exchange {
	public int unique_id;
	public Myset next;
	public Exchange prev;
	public MobilePhoneSet mobiles = new MobilePhoneSet();
	public int level;
	public int cunt;
	public Exchange(int id, int level){
		this.unique_id = id;
		this.next = new Myset();
		this.level = level;
		this.prev = null;
	}
	public Exchange(int id){
		this.unique_id = id;
		this.next = new Myset();
		this.prev = null;
	}
	Exchange(){
		this.next = new Myset();
		this.prev = null;
	}
	public int returnId(){
		return this.unique_id;
	}
	public MobilePhoneSet returnMobileSet(){
		return this.mobiles;
	}
	public void setLevel(int level){
		this.level = level;
	}
	public int getLevel(){
		return this.level;
	}
	public void createChildrenExchange(Exchange o){ //object o should be having prev pointer :/
		this.next.Insert(o);
		//o.prev = this;
		o.setParent(this);
		o.setLevel(this.getLevel() + 1);
	}
	public void createChildrenMobile(MobilePhone a){//DANGER!!!!////
		this.mobiles.InsertMobile(a);
		a.prev = this;
		//a.setParent(this);
		cunt = cunt+1;
		RoutingMapTree.superSet.InsertMobile(a);///////////////////////////////
		//a.prev.mobiles.set.list = a.prev.mobiles.set.Union(a);
		//parent.mobiles.set.list = this.mobiles.set.Union(parent.mobiles.set);
	}
	public int returnCunt(){
		return this.cunt;
	}
	public Exchange parent(){
		return this.prev;
	}
	public void setParent(Exchange parent){
		prev = parent;
		parent.mobiles.set.list = this.mobiles.set.Union(parent.mobiles.set);//All above Exchanges
	}
	public int giveIdMobilePhone(int i){ ////////////////////////////////////////sbvvsvsgvsbrsbergvsergvsergvsrgvaaretaeaearehbharbhaatharthaqth
		return this.mobiles.returnSet().showElement(i).number();
	}
	public void removeFromExchange(MobilePhone a) throws LinkedListOutofBoundsException, EmptySetException {
		this.mobiles.showAll().remove(a);
		a.removeFromBaseStation();
		cunt = cunt - 1;
	}
	public Exchange child(int i){
		return this.next.showElement(i);
	}
	public int numChildren(){
		return this.next.showCount();
	}
	public Boolean isRoot(){
		if(this.parent() == null && this.next!=null){
			return true;
		} else return false;
	}
	// public int numMobiles(){
	// 	return this.mobiles.returnNoOfMobiles();
	// }
	public MobilePhoneSet residentSet(){
		//return this.mobiles.showSet();
		return this.mobiles;
	}
	public MobilePhone giveithMobilePhone(int i){
		return this.residentSet().returnSet().showElement(i);
	}
	public RoutingMapTree subtree(int level, int i){
		RoutingMapTree trek = new RoutingMapTree(level, (Exchange)this.next.showElement(i));
		return trek;
	}

}