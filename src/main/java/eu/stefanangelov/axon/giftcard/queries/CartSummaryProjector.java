package eu.stefanangelov.axon.giftcard.queries;

import javax.persistence.EntityManager;

import eu.stefanangelov.axon.giftcard.apicore.CardSummary;
import eu.stefanangelov.axon.giftcard.apicore.IssuedEvt;
import eu.stefanangelov.axon.giftcard.apicore.RedeemedEvt;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;

import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CartSummaryProjector {

	private final EntityManager entityManager;

	@EventHandler
	public void on(IssuedEvt evt){
		entityManager.persist(new CardSummary(evt.getId(), evt.getAmount(), evt.getAmount()));
	}

	@EventHandler
	public void on(RedeemedEvt evt){
		entityManager.find(CardSummary.class, evt.getId()).reduce(evt.getAmount());
	}

	@QueryHandler
	public CardSummary handler(CardSummaryQuery cardSummaryQuery){
		return entityManager.find(CardSummary.class,cardSummaryQuery.getId());
	}
}
