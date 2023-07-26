package vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PhonebookVO {
	private int idx;
	private String name;
	private String hp;
	private String company;
	private String email;
	private Date birthday;
	private String address;
	private String memo;
}
