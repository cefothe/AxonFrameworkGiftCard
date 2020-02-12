package eu.stefanangelov.axon.giftcard.commands;

import eu.stefanangelov.axon.giftcard.apicore.IssueCmd;
import eu.stefanangelov.axon.giftcard.apicore.IssuedEvt;
import eu.stefanangelov.axon.giftcard.apicore.RedeemCmd;
import eu.stefanangelov.axon.giftcard.apicore.RedeemedEvt;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@NoArgsConstructor
@Slf4j
@Aggregate
public class GiftCard {

	@AggregateIdentifier
	private String id;
	private int remainingValue;

	@CommandHandler
	public GiftCard(IssueCmd cmd){
		log.info("Issues command ");
		if(cmd.getAmount() <= 0) throw new IllegalArgumentException("amount <= 0");
		apply(new IssuedEvt(cmd.getId(), cmd.getAmount()));
	}

	@CommandHandler
	public void on(RedeemCmd cmd){
		log.info("Redeem command");
		if(cmd.getAmount() <= 0) throw new IllegalArgumentException("amount <= 0");
		if(cmd.getAmount() > remainingValue) throw new IllegalStateException("amount > remaining value");
		apply(new RedeemedEvt(cmd.getId(), cmd.getAmount()));
	}

	@EventSourcingHandler
	public void hand(IssuedEvt evt){
		id = evt.getId();
		remainingValue = evt.getAmount();
	}

	@EventSourcingHandler
	public void on(RedeemedEvt evt) {
		log.debug("applying {}", evt);
		remainingValue -= evt.getAmount();
		log.debug("new remaining value: {}", remainingValue);
	}

}
