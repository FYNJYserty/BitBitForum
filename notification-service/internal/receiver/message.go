package receiver

// Interface
type Message interface {
	MessageAttr() (string, string, string)
}

// Struct of received help message
type HelpMessage struct {
	Email       string `json:"email"`       // Email
	Title       string `json:"title"`       // Title
	TextMessage string `json:"textMessage"` // Text message
}

// Struct of received success message
type SuccessMessage struct {
	Email       string `json:"email"`       // User's email
	Username    string `json:"username"`    // User's name
	TextMessage string `json:"textMessage"` // Text message
}
