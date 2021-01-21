package code;

import java.util.*;
import java.io.*;
import java.lang.*;

@SuppressWarnings("unchecked")

public class Myset {
	
	public int count = 0;
	public LinkedList<Exchange> list = new LinkedList<Exchange>(); //can use Myset<T>
	
	public Boolean IsEmpty(){
		if(count==0){
			return true;
		}
		else 
			return false;
	};
	
	public void setCount(int count){ //Should be used in emergency only /////DANGER/////
		this.count = count;
	}
	
	public Boolean IsMember(Exchange o){
		if(this.list.contains(o)){
			return true;
		} else
			return false;
	}
	
	public void Insert(Exchange o){
		this.count += 1;
		this.list.add(o);
	}
	
	public int showCount(){
		return this.count;
	}

	public Exchange showElement(int i){
		// MobilePhone g1 = new MobilePhone();
		// g1 = (MobilePhone)this.list.get(i);
		// System.out.println(g1.number() + " : " + g1.status());

		return this.list.get(i);
	}

	public LinkedList showAll(){
		return this.list;
	}

	public void Delete(Object o){
		try{
			this.list.remove(o);
		} catch(Exception e){ 
			System.out.println("NotFound");
		}
	}
	
	public LinkedList Union(Myset a){ //Ideally according to assignment, the return type should be Myset
		Myset temp1 = new Myset();
		//Myset temp2 = new Myset();
		temp1.list = a.list;
		for(int i=0;i<this.list.size();i++){
			if(!temp1.list.contains(this.list.get(i))){
				temp1.list.add(this.list.get(i));
			}
		}
		//b.addAll(this.list);
		//System.out.println(b.list);
		return temp1.list;
	}
	
	public LinkedList Intersection(Myset a){
		Myset temp2 = new Myset();
		temp2.list = a.list;
		Myset temp3 = new Myset();
		temp3.list = this.list;
		for(int j=0;j<temp3.list.size();j++){
			if(!temp2.list.contains(temp3.list.get(j))){
				//temp2.list.remove(this.list.get(i));//remove from Myset a
				temp3.list.remove(temp3.list.get(j));//remove from the copy of this
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