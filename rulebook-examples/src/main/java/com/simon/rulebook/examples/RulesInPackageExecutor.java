package com.simon.rulebook.examples;

import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import com.deliveredtechnologies.rulebook.model.runner.RuleBookRunner;

public class RulesInPackageExecutor {

	public static void main(String[] args) {
		RulesInPackageExecutor e = new RulesInPackageExecutor();
		e.execute();
	}

	private void execute() {
		
		NameValueReferableMap factMap = new FactMap();
		factMap.setValue("sound", "meow");
		factMap.setValue("action", "sleep");
		
		RuleBook ruleBook = new RuleBookRunner("com.simon.rulebook.examples.rules");
		ruleBook.run(factMap); //assume facts were previously defined
		
	}
}
