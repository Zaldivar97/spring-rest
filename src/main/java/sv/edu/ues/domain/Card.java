package sv.edu.ues.domain;

import lombok.Data;
@Data
public class Card {
	private String type;
	private String number;
	private ExpirationDate expirationDate;
}
