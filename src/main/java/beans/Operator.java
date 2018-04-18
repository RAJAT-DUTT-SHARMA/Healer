package beans;

public enum Operator {
		EQUAL_TO("=="),NOT_EQUAL_TO("!=");
		private final String value;
		
		private Operator(String val) {
			this.value=val;
		}
		
		public String toString() {
			return value;
		}
}
