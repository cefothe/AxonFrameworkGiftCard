package eu.stefanangelov.axon.giftcard;

import java.util.UUID;

import eu.stefanangelov.axon.giftcard.apicore.CardSummary;
import eu.stefanangelov.axon.giftcard.apicore.IssueCmd;
import eu.stefanangelov.axon.giftcard.apicore.RedeemCmd;
import eu.stefanangelov.axon.giftcard.apicore.RedeemedEvt;
import eu.stefanangelov.axon.giftcard.commands.GiftCard;
import eu.stefanangelov.axon.giftcard.queries.CardSummaryQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class GiftcardApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiftcardApplication.class, args);
	}

}

@Slf4j
@RequiredArgsConstructor
@Component
@Order(100)
class CommandRunner implements CommandLineRunner{

	private final QueryGateway queryGateway;
	private final CommandGateway commandGateway;

	@Override
	public void run(String... args) throws Exception {
		log.info("Send comand");
		String id = UUID.randomUUID().toString();
		commandGateway.sendAndWait(new IssueCmd(id, 100));

		log.info("Reeded comand");
		commandGateway.sendAndWait(new RedeemCmd(id, 100));

		Thread.sleep(1000);
		log.info("query");
		CardSummary cart = queryGateway.query(new CardSummaryQuery(id), ResponseTypes.instanceOf(CardSummary.class)).get();
		log.info(cart.toString());

	}
}