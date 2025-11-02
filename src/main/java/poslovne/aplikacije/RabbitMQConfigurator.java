package poslovne.aplikacije;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfigurator {

	public static final String APPOINTMENTS_TOPIC_EXCHANGE_NAME = "appointments-events-exchange";

	public static final String APPOINTMENTS_SERVICE_QUEUE = "appointments-service-queue";

	public static final String NOTIFICATIONS_TOPIC_EXCHANGE_NAME = "appointments-notifications-exchange";

	public static final String NOTIFICATIONS_QUEUE = "appointments-notifications-queue";
	

		@Bean
		Queue appointmentsQueue() {
			return new Queue(APPOINTMENTS_SERVICE_QUEUE, false);
		}

		@Bean
		TopicExchange appointmentsExchange() {
			return new TopicExchange(APPOINTMENTS_TOPIC_EXCHANGE_NAME);
		}

		@Bean
		Binding appointmentsBinding(Queue appointmentsQueue, TopicExchange appointmentsExchange) {
			return BindingBuilder.bind(appointmentsQueue).to(appointmentsExchange).with("appointments.events.#");
		}

		@Bean
		SimpleMessageListenerContainer appointmentsContainer(ConnectionFactory connectionFactory,
				MessageListenerAdapter appointmentsListenerAdapter) {
			SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
			container.setConnectionFactory(connectionFactory);
			container.setQueueNames(APPOINTMENTS_SERVICE_QUEUE);
			container.setMessageListener(appointmentsListenerAdapter);
			return container;
		}

		@Bean
		MessageListenerAdapter appointmentsListenerAdapter(poslovne.aplikacije.messaging.AppointmentEventListener receiver) {
			return new MessageListenerAdapter(receiver, "handleAppointmentEvent");
		}

		@Bean
		Queue notificationsQueue() {
			return new Queue(NOTIFICATIONS_QUEUE, false);
		}

		@Bean
		TopicExchange notificationsExchange() {
			return new TopicExchange(NOTIFICATIONS_TOPIC_EXCHANGE_NAME);
		}

		@Bean
		Binding notificationsBinding(Queue notificationsQueue, TopicExchange notificationsExchange) {
			return BindingBuilder.bind(notificationsQueue).to(notificationsExchange).with("appointments.notifications.#");
		}

		@Bean
		SimpleMessageListenerContainer notificationsContainer(ConnectionFactory connectionFactory,
				MessageListenerAdapter notificationsListenerAdapter) {
			SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
			container.setConnectionFactory(connectionFactory);
			container.setQueueNames(NOTIFICATIONS_QUEUE);
			container.setMessageListener(notificationsListenerAdapter);
			return container;
		}

		@Bean
		MessageListenerAdapter notificationsListenerAdapter(poslovne.aplikacije.messaging.NotificationListener receiver) {
			return new MessageListenerAdapter(receiver, "receiveNotification");
		}
}
