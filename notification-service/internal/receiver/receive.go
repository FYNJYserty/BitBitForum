package receiver

import (
	"encoding/json"
	"log"
	"os"

	"github.com/joho/godotenv"
	amqp "github.com/rabbitmq/amqp091-go"
)

// Get messages from queues
func ReceiveMessage(queue_name string, handler func(Message)) {
	// Getting .env file variables
	err := godotenv.Load()
	failOnError(err, "Error loading .env file in receiver")
	// Connect to RabbitMQ
	conn, err := amqp.Dial(os.Getenv("RABBITMQ_URL"))
	defer conn.Close()
	failOnError(err, "Failed to connect to RabbitMQ")
	// Connect to channel
	ch, err := conn.Channel()
	defer ch.Close()
	failOnError(err, "Failed to open a channel")

	// Consume new message
	msg, err := ch.Consume(
		queue_name,
		"",
		true,
		false,
		false,
		false,
		nil,
	)
	failOnError(err, "Failed to register a consumer")

	// Processing message
	forever := make(chan bool)
	go func() {
		for d := range msg {
			var msg Message
			if queue_name == os.Getenv("RABBITMQ_SUCCESS_QUEUE") {
				var successMessage SuccessMessage
				err := json.Unmarshal(d.Body, &successMessage)
				if err != nil {
					log.Printf("Error unmarshalling success message: %s", err)
				}
				msg = &successMessage
			} else {
				var helpMessage HelpMessage
				err := json.Unmarshal(d.Body, &helpMessage)
				if err != nil {
					log.Printf("Error unmarshalling success message: %s", err)
				}
				msg = &helpMessage
			}
			// Call handler function
			handler(msg)
		}
	}()
	<-forever
}

// Help message methods
func (hm *HelpMessage) MessageAttr() (string, string, string) {
	return hm.Email, hm.Title, hm.TextMessage
}

// Success message methods
func (sm *SuccessMessage) MessageAttr() (string, string, string) {
	return sm.Email, sm.Username, sm.TextMessage
}

// Support functions
func failOnError(err error, msg string) {
	if err != nil {
		log.Fatalf("%s: %s", msg, err)
	}
}
