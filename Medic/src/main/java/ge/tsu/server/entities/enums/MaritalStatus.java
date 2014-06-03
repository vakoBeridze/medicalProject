package ge.tsu.server.entities.enums;

public enum  MaritalStatus {

    MARRIED("დაოჯახებული"),
    SINGLE("დასაოჯახებელი"),
    DIVORCED("განქორწინებული"),
    WIDOWED("ქვრივი"),
    SEPARATED("გაყრილი");

	private final String name;

	private MaritalStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
