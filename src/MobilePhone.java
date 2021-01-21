package code;

import java.util.*;

import exceptions.MobileSwitchedOffException;

import java.io.*;
@SuppressWarnings("unchecked")

///Function Description///

/*
MobilePhone(int number)
MobilePhone()
status() -->returns true if MobilePhone is switchedoff
switchOn() -->make it switch on
switchOff() -->make it switchoff
setBaseStation(Exchange a) -->sets the Base station for the MobilePhone
removeFromBaseStation() -->remove the MobilePhone from Base station
Exchange location() -->returns Base station to which its connected
*/


public class MobilePhone {
	
	public int number;
	public Boolean status;
	public Exchange prev;
	
	MobilePhone(int number){
		this.number = number;
		this.status = false;
	}
	MobilePhone(){
		this.status = false;
	}
	public int number(){
		return this.number;
	}
	public Boolean status(){
		//return true?(this.status==true):false;
		if(this.status == true){
			return true;
		}
		return false;
	}
	public void switchOn(){
		status = true;
	}
	public void switchedOff(){
		status = false;
	}
	public void setBaseStation(Exchange a) throws MobileSwitchedOffException{
		if(this.status){
			/*throw new MobileSwitchedOffException("Mobile Phone Switched Off");*/  //---------------->>>>>>>>
		} 
		else{
			this.prev = a;
		}
	}
	public void removeFromBaseStation(){
		this.prev = null;
		//return this.prev; //Here I might return the BaseStation too(But in the future :/)
	}
	public Exchange location()throws MobileSwitchedOffException{
		if(status == false){
			throw new MobileSwitchedOffException("Error - Mobile phone with identifier " + this.number() + " is currently switched off");
		}
		return this.prev;
	}
}