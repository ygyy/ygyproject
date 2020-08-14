package com.glodon.spring.tx;

import java.util.List;

public interface Cashier {

	//买多本书
	public void checkout(String username, List<String> isbns);
	
}
