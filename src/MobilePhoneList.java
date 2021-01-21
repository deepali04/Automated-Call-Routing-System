package code;

import java.util.*;
import java.lang.*;

@SuppressWarnings("unchecked")

public class MobilePhoneList {
	LinkedList<MobilePhone> list = new LinkedList<MobilePhone>();
	public int count = 0;
	public Boolean IsEmpty(){
		if(count==0){return true;}
		else return false;
	};
	
	public void setCount(int count){ //Should be used in emergency only /////DANGER/////
		this.count = count;
	}
	public Boolean IsMember(MobilePhone o){
		if(this.list.contains(o)){
			return true;
		} else return false;
	}
	
	public void Insert(MobilePhone o){
		this.count += 1;
		this.list.add(o);
	}
	
	public int showCount(){
		return this.count;
	}

	public MobilePhone showElement(int i){

		return this.list.get(i);
	}

	public LinkedList showAll(){
		return this.list;
	}

	public void Delete(MobilePhone o){
		try{
			this.list.remove(o);
		} catch(Exception e){ //Exception not working now
			System.out.println("NotFound");
		}
	}
	
	public LinkedList Union(MobilePhoneList a){ //Ideally according to assignment, the return type should be Myset
		MobilePhoneList temp1 = new MobilePhoneList();
		//Myset temp2 = new Myset();
		temp1.list = a.list;
		for(int i=0;i<this.list.size();i++){
			if(!temp1.list.contains(this.list.get(i))){
				temp1.list.add(this.list.get(i));
			}
		}
		return temp1.list;
	}
	
	public LinkedList Intersection(MobilePhoneList a){
		MobilePhoneList temp2 = new MobilePhoneList();
		temp2.list = a.list;
		MobilePhoneList temp3 = new MobilePhoneList();
		temp3.list = this.list;
		for(int j=0;j<temp3.list.size();j++){
			if(!temp2.list.contains(temp3.list.get(j))){
				//temp2.list.remove(this.list.get(i));//remove from Myset a
				temp3.list.remove(temp3.list.get(j));//remove fromm the copy of this
			}
		}
		for(int k=0;k<temp2.list.size();k++){
			if(!temp3.list.contains(temp2.list.get(k))){
				temp2.list.remove(temp2.list.get(k));
			}
		}
		return temp2.list;
	}
}