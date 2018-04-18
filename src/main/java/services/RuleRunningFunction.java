package services;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class RuleRunningFunction {
	static KieServices ks = KieServices.Factory.get();
	static KieContainer kContainer = ks.getKieClasspathContainer();
	static KieSession kSession = kContainer.newKieSession("ksession-rules");
	
	public static void insert(Object a) {
		kSession.insert(a);
	}
	
	public static int runner() {
		return kSession.fireAllRules();
	}
}
