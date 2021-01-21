package code;

import java.util.*;

import exceptions.MobileNotFoundException;

import java.io.*;
@SuppressWarnings("unchecked")

/////////////FUNCTION DESCRIPTION/////////////////
/*
void InsertMobilePhone(MobilePhone a)
LinkedList showSet()
getMobilePhone(int i)
Boolean containsMobile(MobilePhone a)
findIndexOfPhone(MobilePhone a)
*/


public class MobilePhoneSet extends Myset{
	public MobilePhoneList set = new MobilePhoneList(); //mAKE eXCHANGElIST CHANGE TO MobilePhoneList
	public int Count;

	public MobilePhoneSet(){
		Count = 0;
	}
	public int size(){
		return this.Count;
	}
	public void InsertMobile(MobilePhone a){
		set.Insert(a);//use c.Insert() instead->Done
		Count++; 
	}
	// public int returnNoOfMobiles(){
	// 	return this.Count;
	// } //Dont need this
	public MobilePhoneList returnSet(){
		return this.set;
	}
	public LinkedList showSet(){
		return set.showAll();
	}
	public void switchOffMobile(int i){
		this.set.showElement(i).switchedOff();
	}
	public void switchOnMobile(int i){
		this.set.showElement(i).switchOn();
	}
	public MobilePhone getMobilePhone(int i){
		return this.set.showElement(i);
	}
	public Boolean containsMobile(MobilePhone a){
		if(this.set.IsMember(a)){return true;}
		return false;
	}
	public int findIndexOfPhone(MobilePhone a) throws MobileNotFoundException{
		int temp=0;
		if(!this.set.IsMember(a)){throw new MobileNotFoundException("Mobile Not Found in this set");}
		for(int i=0;i<this.set.showCount();i++){
			if(this.set.showElement(i)==a){temp = i;break;}
		}
		return temp;
	}
}