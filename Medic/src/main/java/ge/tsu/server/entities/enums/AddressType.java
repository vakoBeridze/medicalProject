package ge.tsu.server.entities.enums;

public enum AddressType {

	COUNTRY("ქვეყანა"),
	REGION("რეგიონი"),
	RAION("რაიონი"),
	CITY("ქალაქი"),
	TOWN("დაბა"),
	VILLAGE("სოფელი"),
	CITY_RAION("ქალაქის რაიონი"),
	BLOCK("უბანი"),
	ADDRESS("მისამართი");

	private final String name;

	private AddressType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}