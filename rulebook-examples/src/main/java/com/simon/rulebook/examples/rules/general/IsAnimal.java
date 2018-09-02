package com.simon.rulebook.examples.rules.general;

import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Given;
import com.deliveredtechnologies.rulebook.annotation.Result;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;

@Rule
public class IsAnimal {

	@Given("legs")
	int legs;

	@Result
	boolean isAnimal;
	
	@When
	public boolean when() {
		return legs > 2;
	}

	@Then
	public RuleState then() {
		isAnimal = true;
		System.out.println("We got an animal...");
		return RuleState.NEXT;
	}
}
