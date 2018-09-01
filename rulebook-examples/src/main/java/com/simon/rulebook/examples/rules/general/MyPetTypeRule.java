package com.simon.rulebook.examples.rules.general;

import com.deliveredtechnologies.rulebook.annotation.Given;
import com.deliveredtechnologies.rulebook.annotation.Result;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;

@Rule
public class MyPetTypeRule {

	@Given("sound")
	String sound;

	@Given("action")
	String action;

	@Result
	String petType;

	@Then
	public void then() {
		System.out.println(">>> MyPetTypeRule.then");
		if (sound.equals("meow") && action.equals("sleep")) {
			petType = "cat";
			System.out.println("We got a cat...");
		}
	}
}
