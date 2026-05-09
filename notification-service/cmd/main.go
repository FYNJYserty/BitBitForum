package main

import (
	"log"
	"os"
	"os/signal"
	"syscall"

	"github.com/joho/godotenv"
	"notification-service.com/internal/message-sender"
)

func main() {
	// Load environment variables
	if err := godotenv.Load(); err != nil {
		log.Fatal("Error loading .env file")
	}

	log.Println("Starting mail sender service")

	// Start message handlers in goroutines
	go message_sender.SendEmail("help")    // Help queue
	go message_sender.SendEmail("success") // Success queue

	// Wait for interrupt signal to gracefully shutdown
	quit := make(chan os.Signal, 1)
	signal.Notify(quit, syscall.SIGINT, syscall.SIGTERM)
	<-quit

	log.Println("Shutting down mail sender service")
}
