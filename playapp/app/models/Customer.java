package models;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {

	private Long id;

	private String name;

	@JsonProperty("duetime")
	private String dueTime;

	@JsonProperty("jointime")
	private String joinTime;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDueTime() {
		return dueTime;
	}

	public void setDueTime(final String dueTime) {
		this.dueTime = dueTime;
	}

	public String getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(final String joinTime) {
		this.joinTime = joinTime;
	}

	@JsonIgnore
	public DateTime getDeserializedDueTime() {
		return DateTime.parse(dueTime);
	}

	@JsonIgnore
	public DateTime getDeserializedJoinTime() {
		return DateTime.parse(joinTime);
	}
}
