package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// This class is Static ignore (You write ignore annotation it will block in all conditions
// Dynamic ignore means it will ignore BASED on certain conditions (Like in condition A it will block field1, but in condition B it will block field2)
// If there are more fields to block, it's recommended to use @JsonIgnoreProperties to block them on top of the class
@JsonIgnoreProperties(value = { "field1", "field2" })
public class SomeBean {
	private String field1;
	private String field2;
	// Lets say this field3 is very sensitive and private and I don't want the
	// server include this field when I sent back using this bean
	// Use @JsonIgnore to block fields
	// FYI, the instructor says he liked this method(@JsonIgnore) more because he
	// don't have to
	// think about changing the field names (have to change 2nd time on top)
	// @JsonIgnore
	private String field3;

	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

}
