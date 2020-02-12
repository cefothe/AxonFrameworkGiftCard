package eu.stefanangelov.axon.giftcard.apicore;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Entity
@ToString
public class CardSummary {

	@Id
	private String id;

	private Integer initialValue;

	private Integer remainingValue;

	public  void reduce(Integer value){
		remainingValue-=value;
	}
}
