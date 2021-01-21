package code;

import java.io.*;
import java.util.*;

public class ExchangeList {
	public Myset setExchanges = new Myset();
	public void addToSetExchanges(Exchange a){
		setExchanges.Insert(a);
	}
	public LinkedList showAllExchanges(){
		return setExchanges.showAll();
	}
	public Exchange getithExchange(int i){
		return setExchanges.showElement(i);
	}
	public int totalElementsinExchangeList(){
		return this.setExchanges.showCount();
	}
	public int getithExchangeId(int i){
		return setExchanges.showElement(i).returnId();
	}
}