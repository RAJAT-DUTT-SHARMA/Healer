package services;

import org.kie.api.KieServices;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
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
		System.out.println("runner called");
		int k = kSession.fireAllRules();
		System.out.println("number of facts"+kSession.getFactCount());
		kSession.addEventListener(new AgendaEventListener() {
			
			@Override
			public void matchCreated(MatchCreatedEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("rule fired"+arg0.getMatch().getRule().toString());
			}
			
			@Override
			public void matchCancelled(MatchCancelledEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeMatchFired(BeforeMatchFiredEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void agendaGroupPushed(AgendaGroupPushedEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void agendaGroupPopped(AgendaGroupPoppedEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterMatchFired(AfterMatchFiredEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("rule fired"+arg0.getMatch().getRule().toString());
			}
		});
		System.out.println("# rules: "+k);
		return k;
	}
	
	
	
}
