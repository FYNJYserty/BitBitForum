package message_sender

import (
	"fmt"
	"log"
	"os"

	"github.com/joho/godotenv"
	"gopkg.in/gomail.v2"
	rec "notification-service.com/internal/receiver"
)

func SendEmail(type_queue string) {
	err := godotenv.Load()
	if err != nil {
		log.Fatal("Error loading .env file")
	}

	if type_queue == "help" {
		rec.ReceiveMessage(os.Getenv("RABBITMQ_HELP_QUEUE"), sendHelpMessage)
	} else {
		rec.ReceiveMessage(os.Getenv("RABBITMQ_SUCCESS_QUEUE"), sendSuccessMessage)
	}
}

// Help handler
func sendHelpMessage(msg rec.Message) {
	err := godotenv.Load()
	if err != nil {
		log.Fatal("Error loading .env file in sender")
	}

	var email, title, text = msg.MessageAttr()
	log.Printf("%s, %s, %s", email, title, text)
	m := gomail.NewMessage()

	m.SetHeader("From", email)
	m.SetHeader("To", os.Getenv("EMAIL_ADDRESS"))
	m.SetHeader("Subject", title)

	m.SetBody("text/plain", "From: "+email+"\n"+text)

	d := gomail.NewDialer("smtp.gmail.com", 587, os.Getenv("EMAIL_ADDRESS"), os.Getenv("EMAIL_PASSWORD"))

	if err := d.DialAndSend(m); err != nil {
		log.Fatalf("Error sending help email %s", err)
	}
}

// Success handler
func sendSuccessMessage(msg rec.Message) {
	err := godotenv.Load()
	if err != nil {
		log.Fatal("Error loading .env file in sender")
	}

	var email, username, text = msg.MessageAttr()
	log.Printf("%s, %s, %s", email, username, text)
	m := gomail.NewMessage()

	m.SetHeader("From", os.Getenv("EMAIL_ADDRESS"))
	m.SetHeader("To", email)
	m.SetHeader("Subject", "Successful registration")

	body := fmt.Sprintf(`
		<h1>Hello, %s!</h1>
		<p>%s</p>
	`, username, text)

	m.SetBody("text/html", body)

	d := gomail.NewDialer("smtp.gmail.com", 587, os.Getenv("EMAIL_ADDRESS"), os.Getenv("EMAIL_PASSWORD"))
	if err := d.DialAndSend(m); err != nil {
		log.Fatalf("Error sending success email %s", err)
	}
}
