package services;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.util.ArrayList;
import beans.Condition;
import beans.DiseasePojo;
import beans.Operator;
import beans.SpecialistPojo;
import beans.SymptomPojo;
import beans.TreatmentPojo;
import properties.Properties;

public class RuleGenerator {

	private static final String method = "addDisease";
	private static final String method7 = "addSymptom";
	private static final String method2 = "addDepartmentForDisease";
	private static final String method3 = "addDiseaseForDepartment";
	private static final String method4 = "addTestForDisease";
	
	private static final String method5 = "addTreatmentForDisease";
	private static final String method6 = "addDiseaseForTreatment";

	/*
	 * public static void main(String []args) { //for testing only
	 * ArrayList<Condition> conditions=new ArrayList<>(); Condition con1=new
	 * Condition(); con1.setField("symptom"); con1.setOperator(Operator.EQUAL_TO);
	 * con1.setValue("fever"); conditions.add(con1);
	 * 
	 * ArrayList<String> actionParams=new ArrayList<>();
	 * actionParams.add("food poisioning"); actionParams.add("measles");
	 * actionParams.add("chicken pox"); String rule=ruleGenerator("fever",
	 * SymptomPojo.class.getSimpleName(),conditions , actionParams);
	 * 
	 * writeToDRL(rule,Properties.drlFileSympDis); System.out.println(rule);
	 * 
	 * }
	 */

	public static String ruleGenerator(String attribute, String className, ArrayList<Condition> conditions,
			ArrayList<String> actionParams, String method) {

		StringBuilder rule = new StringBuilder();

		rule.append("rule \"" + attribute + "\"\n");
		rule.append("when\n");
		rule.append(className);
		rule.append("( " + conditionalGenerator(conditions) + " ");
		rule.append("\n");
		rule.append("then\n");
		rule.append(actionGenerator(actionParams, method));
		rule.append("end");

		return rule.toString();
	}

	// ruleGenerator with 2 parameters
	public static String ruleGenerator2(String attribute, String className, ArrayList<Condition> conditions,
			ArrayList<String[]> actionParams, String method) {

		StringBuilder rule = new StringBuilder();

		rule.append("rule \"" + attribute + "\"\n");
		rule.append("when\n");
		rule.append(className);
		rule.append("( " + conditionalGenerator(conditions) + " ");
		rule.append("\n");
		rule.append("then\n");
		rule.append(actionGenerator2(actionParams, method));
		rule.append("end");

		return rule.toString();
	}

	// ActionGenerator with 2 Params
	private static String actionGenerator2(ArrayList<String[]> actionParams, String method) {
		StringBuilder actions = new StringBuilder();
		for (String[] param : actionParams) {
			actions.append(method + "(\"" + writeparams(param) + "\");\n");
		}
		return actions.toString();
	}

	public static String writeparams(String[] k) {
		String s = "";
		s += k[0];
		for (int i = 1; i < k.length; i++) {
			s += "\",\"" + k[i];
		}

		return s;
	}

	private static String conditionalGenerator(ArrayList<Condition> conditions) {

		StringBuilder conditional = new StringBuilder();
		for (Condition c : conditions) {
			conditional.append(c.getField() + " " + c.getOperator().toString() + " \"" + c.getValue() + "\" ");
			conditional.append('|');
		}
		conditional.deleteCharAt(conditional.length() - 1);
		conditional.append(" )");
		return conditional.toString();
	}

	private static String actionGenerator(ArrayList<String> actionParams, String method) {
		StringBuilder actions = new StringBuilder();
		for (String param : actionParams) {
			actions.append(method + "(\"" + param + "\");\n");
		}
		return actions.toString();
	}

	public static void writeToDRL(String rule, String filename) {
		try {
			File file = new File(RuleGenerator.class.getResource(filename).getPath());
			FileWriter writer = new FileWriter(file, true);
			writer.append("\n" + rule + "\n");
			writer.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public static void generateDiseaseToSymtomRules() {
		DBOperations ops = new DBOperations();
		ArrayList<String> listDiseases = ops.getAllDiseases();
		ArrayList<String> listSymptoms;
		ArrayList<String[]> actionParams;
		ArrayList<Condition> listConditions;
		String rule;

		for (String disease : listDiseases) {
			actionParams = new ArrayList<>();
			listSymptoms = ops.getRelatedSymptom(disease);
			System.out.println(listSymptoms.toString());
			for (String i : listSymptoms) {
				String[] params = new String[2];
				params[0] = disease;
				params[1] = i;
				actionParams.add(params);
			}

			Condition condition = new Condition();
			condition.setField("disease");
			condition.setOperator(Operator.EQUAL_TO);
			condition.setValue(disease);

			listConditions = new ArrayList<>();
			listConditions.add(condition);

			rule = ruleGenerator2("ToSymptom"+disease, DiseasePojo.class.getName(), listConditions,
					actionParams, method7);

			writeToDRL(rule, Properties.drlFileDisSymp);
		}
	}

	public static void generateSymtomToDiseaseRules() {
		DBOperations ops = new DBOperations();
		ArrayList<String> listSymptoms = ops.getAllSymptom();
		ArrayList<String> listDiseases;
		ArrayList<Condition> listConditions;
		String rule;

		for (String symptom : listSymptoms) {
			listDiseases = ops.getRelatedDisease(symptom);

			Condition condition = new Condition();
			condition.setField("symptom");
			condition.setOperator(Operator.EQUAL_TO);
			condition.setValue(symptom);

			listConditions = new ArrayList<>();
			listConditions.add(condition);

			rule = ruleGenerator(symptom, SymptomPojo.class.getName(), listConditions, listDiseases, method);

			writeToDRL(rule, Properties.drlFileSympDis);
		}

	}

	public static void generateDiseaseToSpecialist() {
		DBOperations ops = new DBOperations();
		ArrayList<String> listDiseases = ops.getAllDiseases();
		ArrayList<String> listSpecialists;
		ArrayList<String[]> actionParams;
		ArrayList<Condition> listConditions;
		String rule;

		for (String disease : listDiseases) {
			actionParams = new ArrayList<>();
			listSpecialists = ops.getRelatedSpecialist(disease);
			for (String i : listSpecialists) {
				String[] params = new String[2];
				params[0] = disease;
				params[1] = i;
				actionParams.add(params);
			}

			Condition condition = new Condition();
			condition.setField("disease");
			condition.setOperator(Operator.EQUAL_TO);
			condition.setValue(disease);

			listConditions = new ArrayList<>();
			listConditions.add(condition);

			rule = ruleGenerator2("ToSpecialist" + disease, DiseasePojo.class.getName(), listConditions, actionParams,
					method2);

			writeToDRL(rule, Properties.drlFileDisDept);
		}
	}

	public static void generateSpecialistToDisease() {
		DBOperations ops = new DBOperations();
		ArrayList<String> listDiseases;
		ArrayList<String> listSpecialists = ops.getAllSpecialist();
		;
		ArrayList<String[]> actionParams;
		ArrayList<Condition> listConditions;
		String rule;

		for (String specialist : listSpecialists) {
			actionParams = new ArrayList<>();
			listDiseases = ops.getDiseaseForSpecialist(specialist);
			for (String i : listDiseases) {
				String[] params = new String[2];
				params[0] = specialist;
				params[1] = i;
				actionParams.add(params);
			}

			Condition condition = new Condition();
			condition.setField("specialist");
			condition.setOperator(Operator.EQUAL_TO);
			condition.setValue(specialist);

			listConditions = new ArrayList<>();
			listConditions.add(condition);

			rule = ruleGenerator2("ToDisease"+specialist, SpecialistPojo.class.getName(), listConditions,
					actionParams, method3);

			writeToDRL(rule, Properties.drlFileDeptDis);
		}
	}

	public static void generateDiseaseToTest() {
		DBOperations ops = new DBOperations();
		ArrayList<String> listDiseases = ops.getAllDiseases();
		ArrayList<String> listTests;
		ArrayList<String[]> actionParams;
		ArrayList<Condition> listConditions;
		String rule;

		for (String disease : listDiseases) {
			actionParams = new ArrayList<>();
			listTests = ops.getTestForDisease(disease);
			System.out.println(listTests.toString());
			for (String i : listTests) {
				String[] params = new String[2];
				params[0] = disease;
				params[1] = i;
				actionParams.add(params);
			}

			Condition condition = new Condition();
			condition.setField("disease");
			condition.setOperator(Operator.EQUAL_TO);
			condition.setValue(disease);

			listConditions = new ArrayList<>();
			listConditions.add(condition);

			rule = ruleGenerator2("ToTest"+disease, DiseasePojo.class.getName(), listConditions,
					actionParams, method4);

			writeToDRL(rule, Properties.drlFileDisTest);
		}
	}
	
	public static void generateDiseaseToTreatmentRules() {
		DBOperations ops = new DBOperations();
		ArrayList<String> listDiseases = ops.getAllDiseases();
		ArrayList<String> listTreatments;
		ArrayList<Condition> listConditions;
		ArrayList<String[]> actionParams;
		String rule;
		for (String disease : listDiseases) {
			actionParams = new ArrayList<>();
			listTreatments = ops.getTreatmentForDisease(disease);
			
			for (String treatment : listTreatments) {
				String[] params = new String[2];
				params[0] = disease;
				params[1] = treatment;
				actionParams.add(params);
			}
			
			Condition condition = new Condition();
			condition.setField("disease");
			condition.setOperator(Operator.EQUAL_TO);
			condition.setValue(disease);
			listConditions = new ArrayList<>();
			listConditions.add(condition);
			rule = ruleGenerator2(disease+"_DISEASE",
					              DiseasePojo.class.getName(),
					              listConditions, 
					              actionParams, 
					              method5);

			writeToDRL(rule, Properties.drlFileDisTrtmnt);
		}
	}
	
	public static void generateTreatmentToDiseaseRules() {
		DBOperations ops = new DBOperations();
		ArrayList<String> listTreatments = ops.getAllTreatments();
		ArrayList<String> listDiseases;
		ArrayList<Condition> listConditions;
		String rule;
		for (String treatment : listTreatments) {
			listDiseases = ops.getDiseaseForTreatment(treatment);
			
			Condition condition = new Condition();
			condition.setField("treatment");
			condition.setOperator(Operator.EQUAL_TO);
			condition.setValue(treatment);
			listConditions = new ArrayList<>();
			listConditions.add(condition);
			rule = ruleGenerator(treatment+"_TREATMENT",
					              TreatmentPojo.class.getName(),
					              listConditions, 
					              listDiseases, 
					              method6);

			writeToDRL(rule, Properties.drlFileTrtmntDis);
		}
	}
}
