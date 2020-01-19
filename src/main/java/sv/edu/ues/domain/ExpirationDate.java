package sv.edu.ues.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ExpirationDate {
//@DateTimeFormat
private LocalDateTime date;
private short timezoneType;
private String timezone;
}
