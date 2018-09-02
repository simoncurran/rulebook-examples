package com.simon.rulebook.examples.rules.general;

import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Given;
import com.deliveredtechnologies.rulebook.annotation.Result;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;

@Rule
public class IsCat {

	@Given("sound")
	String sound;

	@Given("action")
	String action;

	@Result
	boolean isCat;

	@When
	public boolean when() {
		return sound.equals("meow") && action.equals("sleep");
	}
	
	@Then
	public RuleState then() {
		isCat = true;
		System.out.println("We got an cat...");
		return RuleState.NEXT;
	}
}
