package com.simon.rulebook.examples;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.lang.RuleBookBuilder;
import com.deliveredtechnologies.rulebook.model.AuditableRule;
import com.deliveredtechnologies.rulebook.model.Rule;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import com.deliveredtechnologies.rulebook.model.runner.RuleAdapter;
import com.google.gson.Gson;

public class RulesByNameExecutor {

	private static final String RULES_PACKAGE = "com.simon.rulebook.examples.rules.";
	private boolean isAuditable = false;

	public static void main(String[] args) {
		RulesByNameExecutor e = new RulesByNameExecutor();
		e.execute();
	}

	private void execute() {
		try {
			List<String> ruleAndFactList = FileUtils.readLines(new File("src/main/resources/rules.txt"));
			System.out.println("rules=" + ruleAndFactList);
			for (String ruleAndFact : ruleAndFactList) {
				String rule = getRule(ruleAndFact);
				NameValueReferableMap factMap = getFactsMap(ruleAndFact);
				Class clazz = Class.forName(RULES_PACKAGE + rule);
				Rule ruleInstance = null;
				if (isAuditable) {
					ruleInstance = new AuditableRule(new RuleAdapter(clazz.newInstance()), clazz.getSimpleName());
				} else {
					ruleInstance = new RuleAdapter(clazz.newInstance());
				}
				RuleBook ruleBook = RuleBookBuilder.create().addRule(ruleInstance).build();
				ruleBook.run(factMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private NameValueReferableMap getFactsMap(String ruleAndFact) {
		Map<String, Object> map = parseFactsIntoMap(ruleAndFact);
		Set<Entry<String, Object>> entires = map.entrySet();
		NameValueReferableMap factMap = new FactMap();
		for (Entry<String, Object> e : entires) {
			factMap.setValue(e.getKey(), e.getValue());
			System.out.println("Adding fact :key=" + e.getKey() + ", value=" + e.getValue());
		}
		System.out.println("factMap=" + factMap);
		return factMap;
	}

	private Map<String, Object> parseFactsIntoMap(String ruleAndFact) {
		String facts = getFactsJson(ruleAndFact);
		Map<String, Object> map = new Gson().fromJson(facts, Map.class);
		System.out.println("map=" + map);
		return map;
	}

	private String getFactsJson(String ruleAndFact) {
		String json = ruleAndFact.substring(ruleAndFact.indexOf("(") + 1, ruleAndFact.indexOf(")"));
		System.out.println("factsJson=" + json);
		return json;
	}

	private String getRule(String ruleAndFact) {
		String rule = ruleAndFact.substring(0, ruleAndFact.indexOf("("));
		System.out.println("rule=" + rule);
		return rule;
	}
}
