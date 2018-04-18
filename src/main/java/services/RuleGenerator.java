package services;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.util.ArrayList;
import beans.Condition;
import beans.DiseasePojo;
import beans.Operator;
import beans.SymptomPojo;
import properties.Properties;

public class RuleGenerator {

	private static final String method="addDisease";
	
	
	/*
	public static void main(String []args) {
		//for testing only
		ArrayList<Condition> conditions=new ArrayList<>();
		Condition con1=new Condition();
		con1.setField("symptom");
		con1.setOperator(Operator.EQUAL_TO);
		con1.setValue("fever");
		conditions.add(con1);
		
		ArrayList<String> actionParams=new ArrayList<>();
		actionParams.add("food poisioning");
		actionParams.add("measles");
		actionParams.add("chicken pox");
		String rule=ruleGenerator("fever", SymptomPojo.class.getSimpleName(),conditions , actionParams);
		
		writeToDRL(rule,Properties.drlFileSympDis);
		System.out.println(rule);
	
		}
	*/
	
	public static String ruleGenerator(String attribute,String className,ArrayList<Condition> conditions,ArrayList<String> actionParams) {
		
		StringBuilder rule=new StringBuilder();
	
		rule.append("rule \""+attribute+"\"\n");
		rule.append("when\n");
		rule.append(className);
		rule.append("( "+conditionalGenerator(conditions)+" ");
		rule.append("\n");
		rule.append("then\n");
		rule.append(actionGenerator(actionParams));
		rule.append("end");

		return rule.toString();
	}
	
	private static String conditionalGenerator(ArrayList<Condition> conditions) {
		
		StringBuilder conditional=new StringBuilder();
		for(Condition c:conditions) {
			conditional.append(c.getField()+" "+c.getOperator().toString()+" \""+c.getValue()+"\" ");
			conditional.append('|');
		}
		conditional.deleteCharAt(conditional.length()-1);
		conditional.append(" )");
		return conditional.toString();
	}
	
	private static String actionGenerator(ArrayList<String> actionParams) {
		StringBuilder actions=new StringBuilder();
		for(String param:actionParams) {
			actions.append(method+"(\""+param+"\");\n");
		}
		return actions.toString();
	}
	
	
	public static void writeToDRL(String rule,String filename) {
		try{
			File file=new File(RuleGenerator.class.getResource(filename).getPath()); 
			FileWriter writer=new FileWriter(file,true);
			writer.append("\n"+rule+"\n");
			writer.close();
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	} 
	
	public static void generateDiseaseToSymtomRules() {
		DBOperations ops=new DBOperations();
		ArrayList<String> listDiseases=ops.getAllDiseases();
		ArrayList<String> listSymptoms;
		ArrayList<Condition> listConditions;
		String rule;
		for(String disease:listDiseases) {
			listSymptoms=ops.getRelatedSymptom(disease);
			
			Condition condition=new Condition();
			condition.setField("disease");
			condition.setOperator(Operator.EQUAL_TO);
			condition.setValue(disease);
			listConditions=new ArrayList<>();
			listConditions.add(condition);
			rule=ruleGenerator(disease, DiseasePojo.class.getName(), listConditions, listSymptoms);
		
			writeToDRL(rule, Properties.drlFileDisSymp);
		}
	}
	
	public static void generateSymtomToDiseaseRules() {
		DBOperations ops=new DBOperations();
		ArrayList<String> listSymptoms=ops.getAllSymptom();
		ArrayList<String> listDiseases;
		ArrayList<Condition> listConditions;
		String rule;
		
		for(String symptom:listSymptoms) {
			listDiseases=ops.getRelatedDisease(symptom);
			
			Condition condition=new Condition();
			condition.setField("symptom");
			condition.setOperator(Operator.EQUAL_TO);
			condition.setValue(symptom);
			
			listConditions=new ArrayList<>();
			listConditions.add(condition);
			
			rule=ruleGenerator(symptom, SymptomPojo.class.getName(), listConditions, listDiseases);
		
			writeToDRL(rule, Properties.drlFileSympDis);
		}
		
	}

}
